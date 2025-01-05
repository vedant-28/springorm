package com.springcore.orm.dao;

import java.util.List;

import com.springcore.orm.entities.Student;

public interface StudentDao {
	public void insert(Student student);
	public Student getStudent(int StudentId);
	public List<Student> getAllStudents();
	public void update(Student student);
	public void delete(int StudentId);
}
