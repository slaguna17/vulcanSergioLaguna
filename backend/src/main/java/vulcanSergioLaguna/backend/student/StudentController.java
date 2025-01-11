package vulcanSergioLaguna.backend.student;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    //Declare Repository and Constructor
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    //Get all Students
    @GetMapping("/api/students")
    List<Student> findAllStudents(){
        return studentRepository.findAllStudents();
    }

    //Get a Student by ID
    @GetMapping("/api/students/{id}")
    Student findStudentById(@PathVariable Integer id){
        Optional<Student> student = studentRepository.findStudentById(id);
        if (student.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return student.get();
    }

    //Create a Student (POST)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/createStudent")
    void createStudent(@RequestBody Student student){
        studentRepository.createStudent(student);
    }

    //Update a Student (PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/api/updateStudent/{id}")
    void updateStudent(@RequestBody Student student, @PathVariable Integer id){
        studentRepository.updateStudent(student,id);
    }

    //Delete a Student (DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/deleteStudent/{id}")
    void deleteStudent(@PathVariable Integer id){
        studentRepository.deleteStudent(id);
    }
}
