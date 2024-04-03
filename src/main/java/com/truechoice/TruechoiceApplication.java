package com.truechoice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.truechoice")
@EnableAutoConfiguration
public class TruechoiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TruechoiceApplication.class, args);
		
//		String str = "2000-01-01 12:30:44";
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		LocalDate dateTime = LocalDate.parse(str, formatter);
//		
//		LocalDate currentDate = LocalDate.now();
//		Period.between(dateTime, currentDate).getYears();
//		System.out.println("Age : " + Period.between(dateTime, currentDate).getYears());
	}
}
