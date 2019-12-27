package com.springboot.currentAccountVip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioAccountCurrentPersonalVipApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioAccountCurrentPersonalVipApplication.class, args);
	}

}
