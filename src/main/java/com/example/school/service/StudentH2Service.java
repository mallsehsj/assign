/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 *
 */

// Write your code here
package com.example.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.school.model.*;

import com.example.school.repository.StudentRepository;

@Service
public class StudentH2Service implements StudentRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Student> getAllStudents() {
        List<Student> list = db.query("select * from STUDENT ", new StudentRowMapper());
        ArrayList<Student> arraylist = new ArrayList<>(list);
        return arraylist;
    }

    @Override
    public Student getById(int studentId) {
        Student student1 = db.queryForObject("select * from STUDENT where=?", new StudentRowMapper(), studentId);
        if (student1 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return student1;
    }

    @Override
    public Student addStudents(Student student) {
        db.update("insert into STUDENT(studentName, gender, standard) values(?,?,?)", student.getStudentName(),
                student.getGender(), student.getStandard());
        return getById(student.getStudentId());

    }

    @Override
    public String addBulkStudents(ArrayList<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            db.update("insert into STUDENT(studentName, gender, standard) values(?,?,?)",
                    students.get(i).getStudentName(),
                    students.get(i).getGender(), students.get(i).getStandard());
        }
        return "Successfully added 4 students";

    }

    @Override
    public Student updatStudent(int studentId, Student student) {
        if (student.getStudentName() != null) {
            db.update("update STUDENT set studentName=? where studentId=?", student.getStudentName(), studentId);
        }
        if (student.getGender() != null) {
            db.update("update STUDENT set gender=? where studentId=?", student.getStudentName(), studentId);
        }
        if (student.getStandard() != 0) {
            db.update("update STUDENT set standard=? where studentId=?", student.getStandard(), studentId);
        }
        return getById(studentId);

    }

    @Override
    public void deleteStudent(int studentId) {
        db.update("delete from STUDENT where studentId=?", studentId);
    }

}
