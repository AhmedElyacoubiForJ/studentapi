package edu.yacoubi.studentapi.servcie;

import edu.yacoubi.studentapi.exception.StudentAlreadyExistsException;
import edu.yacoubi.studentapi.exception.StudentNotFoundException;
import edu.yacoubi.studentapi.model.Student;
import edu.yacoubi.studentapi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        if (isStudentAlreadyExists(student.getEmail())) {
            throw new StudentAlreadyExistsException(student.getEmail() + " already exists!");
        }

        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        return studentRepository.findById(id).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            return studentRepository.save(st);
        }).orElseThrow(() -> new StudentNotFoundException("Student could not be found"));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("No Student found with the Id : " + id));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }
        studentRepository.deleteById(id);
    }

    private boolean isStudentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }
}
