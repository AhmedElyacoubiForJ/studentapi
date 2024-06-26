package edu.yacoubi.studentapi.servcie;

import edu.yacoubi.studentapi.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getStudents();
    Student addStudent(Student student);
    Student updateStudent(Student student, Long id);
    Student getStudentById(Long id);
    void deleteStudent(Long id);
}
