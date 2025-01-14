package vulcanSergioLaguna.backend.student;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //Get Students by Classroom
    @GetMapping("/api/classroom/{classroomId}/students")
    List<Student> getStudentsByClassroom(@PathVariable Integer classroomId){
        return studentRepository.getStudentsByClassroom(classroomId);
    }

    //Get Students Average by Sex by Classroom
    @GetMapping("/api/classroom/{classroomId}/averageStudentPerSex")
    String getSexPercentageByClassroom(@PathVariable Integer classroomId){
        String percentage = studentRepository.getSexPercentageByClassroom(classroomId);
        String message = "El promedio por genero es de: " + percentage;
        return percentage;
    }

    //Get Students by Sex
    @GetMapping("/api/students/sex/{sex}")
    List<Student> getStudentsBySex(@PathVariable String sex){
        return studentRepository.getStudentsBySex(sex);
    }

}
