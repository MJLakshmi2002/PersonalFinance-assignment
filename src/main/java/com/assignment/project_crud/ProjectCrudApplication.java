package com.assignment.project_crud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectCrudApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProjectCrudApplication.class, args);
		String url = "jdbc:mysql://localhost:3306/finance";
		String user = "root";
		String password = "Lalaji@1998";

		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			if (connection != null) {
				System.out.println(" Connected to the database successfully!");
			} else {
				System.out.println(" Failed to connect to the database.");
			}
		} catch (SQLException e) {
			System.out.println(" Database connection error: " + e.getMessage());
		}
	}

}
