package com.example.DOTA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DotaAutoChessApplication {

	public static void main(String[] args) {
		SpringApplication.run(DotaAutoChessApplication.class, args);
	}

}
