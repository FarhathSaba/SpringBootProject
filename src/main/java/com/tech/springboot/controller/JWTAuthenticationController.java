package com.tech.springboot.controller;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tech.springboot.pojos.JwtRequest;
import com.tech.springboot.pojos.JwtResponse;
import com.tech.springboot.security.JwtTokenUtil;
import com.tech.springboot.services.JwtUserDetailsService;

@RestController
@CrossOrigin
@RequestMapping({ "/api" })
public class JWTAuthenticationController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;


	@Value("${privatekey}")
	private String privatekey;

	@RequestMapping(value = "/getAccessToken", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		LOGGER.info("Entering into createAuthenticationToken at {}",System.currentTimeMillis());
		try {
				
		authenticate(authenticationRequest.getUsername(), getDecrypted(authenticationRequest.getPassWord()));

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
		}catch(Exception e) {
			LOGGER.error("Exception in createAuthenticationToken method : {} ,{}",e.getMessage(),e);
			//return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
			throw e;
		}finally {
			LOGGER.info("Exiting from createAuthenticationToken at {}",System.currentTimeMillis());
		}
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	public String getDecrypted(String data) throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//		byte[] privKeyBytes = new byte[(int)data.getBytes().length];
//		PKCS8EncodedKeySpec keySpec =
//			    new PKCS8EncodedKeySpec(DatatypeConverter.parseBase64Binary(Base64.getDecoder().decode(this.privatekey.getBytes())));
//		privKeyBytes= Base64.getDecoder().decode(this.privatekey.getBytes());
//		PrivateKey pk = KeyFactory.getInstance("RSA")
//				.generatePrivate(new PKCS8EncodedKeySpec(privKeyBytes));
		byte[] privateBytes = Base64.getDecoder().decode(privatekey);
		PKCS8EncodedKeySpec keySpec1 = new PKCS8EncodedKeySpec(privateBytes);
		KeyFactory keyFactory1 = KeyFactory.getInstance("RSA");
		PrivateKey private_key = keyFactory1.generatePrivate(keySpec1);
		
		cipher.init(Cipher.DECRYPT_MODE, private_key);
		byte[] encryptedbytes = cipher.doFinal(Base64.getDecoder().decode(data.getBytes()));
		return new String(encryptedbytes);
	}

}