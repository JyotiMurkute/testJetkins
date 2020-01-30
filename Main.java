package com.lti.ui;

import java.io.Console;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.lti.model.Student;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// We need to provide Persistence unit name from the xml file.
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_PU");
		EntityManager entityManager = factory.createEntityManager();
		
		int id, choice;
		String name;
		double score;
		Student stu = null;
		
		while(true){
			System.out.println("\n1. Insert \n2. Search \n3. Remove \n4. Exit");
			System.out.print("\nEnter Choice: ");
			choice = sc.nextInt();
			
			switch(choice){
				case 1:
					System.out.print("Enter ID: ");
					id = sc.nextInt();
					
					System.out.print("Enter Name: ");
					name = sc.next();
					
					System.out.print("Enter Score: ");
					score = sc.nextDouble();
					
					Student student = new Student(id, name, score);		
					
					// DML statements are required to be enclosed within a transaction block.
					entityManager.getTransaction().begin();
					entityManager.persist(student);
					entityManager.getTransaction().commit();
					break;
				
				case 2:
					System.out.print("\nEnter Roll number: ");
					id = sc.nextInt();
					stu = entityManager.find(Student.class, id);
					System.out.println("\nName: "+stu.getName()+"\nScore: "+stu.getScore());
					
					break;
				
				case 3:
					System.out.print("\nEnter Roll number to delete: ");
					id = sc.nextInt();
					stu = entityManager.find(Student.class, id);
					entityManager.getTransaction().begin();
					entityManager.remove(stu);
					entityManager.getTransaction().commit();
					break;
					
				case 4:
					sc.close();
					System.exit(0);
					
				default:
					System.out.println("Invalid Choice");
		}
		}
		
	}
}
