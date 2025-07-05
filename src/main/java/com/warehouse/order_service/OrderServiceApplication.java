package com.warehouse.order_service;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*import java.util.Base64;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
*/
@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
		
		/*
		 * String key =
		 * Base64.getEncoder().encodeToString(Keys.secretKeyFor(SignatureAlgorithm.HS256
		 * ).getEncoded()); System.out.println("JWT Secret: " + key);
		 */
	}

}
