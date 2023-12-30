// Write your code here

package com.example.school.repository;

import com.example.school.model.*;
import java.util.*;

public interface StudentRepository {

    ArrayList<Student> getAllStudents();

    Student addStudents(Student student);

    String addBulkStudents(ArrayList<Student> students);

    Student getById(int studentId);

    Student updatStudent(int studentId, Student student);

    void deleteStudent(int studentId);

}