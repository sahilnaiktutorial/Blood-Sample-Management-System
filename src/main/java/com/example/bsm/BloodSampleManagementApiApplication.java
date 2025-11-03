package com.example.bsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BloodSampleManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodSampleManagementApiApplication.class, args);
	}

}
//  mail:
//    host: smtp.gmail.com
//    port: 587
//    username: naiksahil478@gmail.com 
//    password: 
//    properties:
//      mail:
//        smtp:
//          starttls:
//            enable: true
//            required: true
