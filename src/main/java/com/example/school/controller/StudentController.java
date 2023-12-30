/*
 *
 * You can use the following import statemets
 *
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */
package com.example.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.school.model.*;
import com.example.school.service.*;

@RestController
public class StudentController {
    @Autowired
    public StudentH2Service studentService;

    @GetMapping("/students")
    public ArrayList<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/students")
    public Student addStudents(@RequestBody Student student) {
        return studentService.addStudents(student);
    }

    @PostMapping("/students/bulks")
    public String addBulkStudents(@RequestBody ArrayList<Student> students) {
        return studentService.addBulkStudents(students);

    }

    @GetMapping("/students/{studentId}")
    public Student getById(@PathVariable("studentId") int studentId) {
        return studentService.getById(studentId);
    }

    @PutMapping("/students/{studentId}")
    public Student updatStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        return studentService.updatStudent(studentId, student);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        studentService.deleteStudent(studentId);

    }

}
