package vulcanSergioLaguna.backend.student;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import vulcanSergioLaguna.backend.classroom.Classroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {
    private List<Student> students = new ArrayList<>();

    //Get all Students
    List<Student> findAllStudents(){
        return students;
    }

    //Get Student by ID
    Optional<Student> findStudentById(Integer id){
        return students.stream()
                .filter(student -> student.id() == id)
                .findFirst();
    }

    //Create Student
    void createStudent(Student student){
        students.add(student);
    }

    //Update Student
    void updateStudent(Student student, Integer id){
        Optional<Student> existingStudent = findStudentById(id);
        if (existingStudent.isPresent()){
            students.set(students.indexOf(existingStudent.get()),student);
        }
    }

    //Delete Student
    void deleteStudent(Integer id){
        students.removeIf(student -> student.id().equals(id));
    }

    @PostConstruct
    private void init (){

        List<Classroom> classrooms = new ArrayList<>();

        students.add(new Student(
                1,
                "Sergio",
                25,
                "Masculino"
        ));
        students.add(new Student(
                2,
                "Andrea",
                24,
                "Femenino"
        ));

        classrooms.add(new Classroom(
                1,
                "Historia",
                20,
                students
        ));
        classrooms.add(new Classroom(
                2,
                "Religion",
                40,
                students
        ));
    }
}
