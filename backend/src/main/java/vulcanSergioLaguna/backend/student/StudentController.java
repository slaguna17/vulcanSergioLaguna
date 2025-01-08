package vulcanSergioLaguna.backend.student;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedList;

public class StudentController {
    @GetMapping("/getAllStudents")
    public void getAllStudents(){
        LinkedList<Student> studentList = new LinkedList<Student>();
    }

    private int studentID = 0;
    @GetMapping("/student")
    public void getStudentByID(int id){

    }

    @PostMapping("createStudent")
    public void createStudent(){

    }

    //Update
    @PostMapping
    public void updateStudent(int id){

    }

    //Delete
    @DeleteMapping("/deleteStudent")
    public void deleteStudent(int id){

    }
}
