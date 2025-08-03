package com.practice.studentapis;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentapisApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentapisApplication.class, args);

		System.out.println("🎉 Server is up! Let's have some fun with students 🚀");
	}

//	@Bean
//	CommandLineRunner run(StudentRepository repository) {
//		return (args) -> {
//			Student ali = new Student("Ali Medhat", "ali@example.com");
//			repository.save(ali);
//			System.out.println("✅ Added Ali Medhat to DB");
//		};
//	}
//
//	@Bean
//	CommandLineRunner printStudents(StudentRepository repository) {
//		return (args) -> {
//			List<Student> students = repository.findAll();
//			students.forEach(System.out::println);
//		};
//	}



}
