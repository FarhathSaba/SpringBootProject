package com.tech.springboot.Assignment;


import java.sql.SQLException;

import org.h2.tools.Server;
 
public class Starters {
 
//    private static final Logger logger = LoggerFactory.getLogger(Starters.class);
 
    public static void startH2Server() {
        try {
            Server h2Server = Server.createTcpServer().start(); // key code
            if (h2Server.isRunning(true)) {
//                logger.info("H2 server was started and is running.");
            } else {
                throw new RuntimeException("Could not start H2 server.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to start H2 server: ", e);
        }
    }
}