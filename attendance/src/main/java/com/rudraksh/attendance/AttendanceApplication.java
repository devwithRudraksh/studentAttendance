package com.rudraksh.attendance;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class AttendanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceApplication.class, args);
		System.out.println("hell world");
	}
	@Bean
	CommandLineRunner testSQLConnection(DataSource dataSource) {
		return args -> {
			try (Connection conn = dataSource.getConnection()) {
				System.out.println("✅ Successfully connected to MySQL!");
			} catch (Exception e) {
				System.out.println("❌ SQL Connection failed:");
				e.printStackTrace();
			}
		};
	}
}
