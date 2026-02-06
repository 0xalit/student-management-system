package com.practice.studentapis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentapisApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentapisApplication.class, args);
		System.out.println("Server is up");
	}
}
