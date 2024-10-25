package com.quality_air.quality_air_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Quality Air Backend application.
 * 
 * This class is responsible for starting the Spring Boot application.
 * It also provides the URL for the Swagger documentation in the console output.
 */
@SpringBootApplication
public class QualityAirBackendApplication {

    /**
     * Main method to run the Quality Air Backend application.
     * 
     * @param args command-line arguments passed during the application startup.
     */
    public static void main(String[] args) {
        SpringApplication.run(QualityAirBackendApplication.class, args);
        
        // Show the SWAGGER documentation url in the console
        System.out.println("---------- SWAGGER Documentation ------------");
        System.out.println("http://[ip_server]:8080/swagger-ui/index.html");
        System.out.println("---------------------------------------------");
    }
}
