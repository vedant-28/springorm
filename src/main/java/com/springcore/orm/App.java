package com.springcore.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springcore.orm.config.HibernateConfig;
import com.springcore.orm.dao.StudentDao;
import com.springcore.orm.entities.Student;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
    	StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
    	
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	boolean flag = true;
    	
    	while(flag) {
    		
    		System.out.println("1) Add new student");
    		System.out.println("2) Display all students");
    		System.out.println("3) Get student details by id");
    		System.out.println("4) Delete student");
    		System.out.println("5) Update student details");
    		System.out.println("6) Exit");
    		System.out.println("Your choice: ");
    		
    		try {
				int input = Integer.parseInt(reader.readLine());
				switch (input) {
				case 1:
					// 1) Add new student
					System.out.println("Enter id: ");
					int id = Integer.parseInt(reader.readLine());
					
					System.out.println("Enter name: ");
					String name = reader.readLine();
					
					System.out.println("enter city: ");
					String city = reader.readLine();
					
					Student student = new Student();
					student.setId(id);
					student.setName(name);
					student.setCity(city);
					
					studentDao.insert(student);
					System.out.println("Student inserted...");
					System.out.println("----------------------------------------------");
					
					break;
				
				case 2:
					// 2) Display all student
					List<Student> students = studentDao.getAllStudents();
					for(Student s : students) {
						System.out.println("-----------------------------------------------");
						System.out.println("Id: " + s.getId());
						System.out.println("Name: " + s.getName());
						System.out.println("City: " + s.getCity());
						System.out.println("-----------------------------------------------");
					}
					break;
				
				case 3:
					// 3) Get student details by id
					System.out.println("Enter id: ");
					int getId = Integer.parseInt(reader.readLine());
					Student student1 = studentDao.getStudent(getId);
					
					System.out.println("-----------------------------------------------");
					System.out.println("Id: " + student1.getId());
					System.out.println("Name: " + student1.getName());
					System.out.println("City: " + student1.getCity());
					System.out.println("-----------------------------------------------");
					
					break;
				
				case 4:
					// 4) Delete student
					System.out.println("Enter id: ");
					int deleteId = Integer.parseInt(reader.readLine());
					Student student2 = studentDao.getStudent(deleteId);
					studentDao.delete(deleteId);
					System.out.println("Student with id " + deleteId + " deleted...");
					System.out.println("-----------------------------------------------");
					
					break;
					
				case 5:
					// 5) Update student details
					try {
						System.out.println("Enter id: ");
					    int updateId = Integer.parseInt(reader.readLine());
					    Student existingStudent = studentDao.getStudent(updateId);
					    
					    if (existingStudent == null) {
					        System.out.println("No student found with id: " + updateId);
					    } else {
					    	System.out.println("Enter name: ");
					        String updateName = reader.readLine();

					        System.out.println("Enter city: ");
					        String updateCity = reader.readLine();
					        
					        existingStudent.setName(updateName);
					        existingStudent.setCity(updateCity);
					        studentDao.update(existingStudent);
					        
					        System.out.println("Updated student details with id: " + updateId);
					    }
					} catch (Exception e) {
						System.out.println("An exception occured while updating student!");
					}
					
					break;
				
				case 6:
					// 6) Exit
					flag = false;
					break;
					
				default:
					System.out.println("Invalid choice!!");
					break;
				}    			
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    	}
    }
}
