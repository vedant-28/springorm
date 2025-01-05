package com.springcore.orm.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springcore.orm.dao.StudentDao;
import com.springcore.orm.entities.Student;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insert(Student student) {
		sessionFactory.getCurrentSession().persist(student);
	}

	// Get single student
	@Override
	public Student getStudent(int StudentId) {
		Student student = sessionFactory.getCurrentSession().get(Student.class, StudentId);
		return student;
	}

	// Get all students
	@Override
	public List<Student> getAllStudents() {
		List<Student> studentList = sessionFactory.getCurrentSession().createQuery("FROM Student", Student.class).getResultList();
		return studentList;
	}
	
	@Override
	public void update(Student student) {
		Session session = sessionFactory.getCurrentSession();
	    Student existingStudent = session.get(Student.class, student.getId());
	    if (existingStudent != null) {
	        existingStudent.setName(student.getName());
	        existingStudent.setCity(student.getCity());
	        session.merge(existingStudent);
	    } else {
	        throw new RuntimeException("Student not found with ID: " + student.getId());
	    }
	}

	@Override
	public void delete(int StudentId) {
		Student student = sessionFactory.getCurrentSession().get(Student.class, StudentId);
		sessionFactory.getCurrentSession().remove(student);
	}
}
