package vulcanSergioLaguna.backend.student;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import vulcanSergioLaguna.backend.classroom.Classroom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {
    //In memory repository
    private List<Student> students = new ArrayList<>();

    //JDBC
    private static final Logger log = LoggerFactory.getLogger(StudentRepository.class);
    private final JdbcClient jdbcClient;

    public StudentRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    //Get all Students
    List<Student> findAllStudents(){
        return jdbcClient.sql("select * from student").query(Student.class).list();
    }

    //Get Student by ID
    Optional<Student> findStudentById(Integer id){
        return jdbcClient.sql("SELECT id, name, age, sex FROM Student WHERE id = :id")
                .param("id",id)
                .query(Student.class)
                .optional();
    }

    //Create Student
    void createStudent(Student student){
        var updated = jdbcClient.sql("INSERT INTO Student(id, name, age, sex) VALUES (?,?,?,?)")
                .params(List.of(student.id(),student.name(),student.age(),student.sex()))
                .update();
        Assert.state(updated == 1, "Failed to create student " + student.name());
    }

    //Update Student
    void updateStudent(Student student, Integer id){
        var updated = jdbcClient.sql("update Student set name = ?, age = ?, sex = ? where id = ?")
                .params(List.of(student.name(),student.age(),student.sex(),id))
                .update();
        Assert.state(updated == 1, "Failed to update student " + student.name());
    }

    //Delete Student
    void deleteStudent(Integer id){
        var updated = jdbcClient.sql("delete from Student where id = :id")
                .param("id",id)
                .update();
        Assert.state(updated == 1, "Failed to delete student " + id);
    }

}