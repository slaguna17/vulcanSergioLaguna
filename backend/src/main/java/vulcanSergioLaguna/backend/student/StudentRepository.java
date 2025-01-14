package vulcanSergioLaguna.backend.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import vulcanSergioLaguna.backend.classroom.Classroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class StudentRepository {
    //JDBC
    private static final Logger log = LoggerFactory.getLogger(StudentRepository.class);
    private final JdbcClient jdbcClient;

    public StudentRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    //Get all Students
    public List<Student> findAllStudents(){
        return jdbcClient.sql("select * from student").query(Student.class).list();
    }

    //Get Student by ID
    public Optional<Student> findStudentById(Integer id){
        return jdbcClient.sql("SELECT id, name, age, sex FROM Student WHERE id = :id")
                .param("id",id)
                .query(Student.class)
                .optional();
    }

    //Create Student
    public void createStudent(Student student) {
        var updated = jdbcClient.sql("INSERT INTO Student (name, age, sex) VALUES (?, ?, ?)")
                .params(List.of(student.getName(),student.getAge(),student.getSex()))
                .update();
        Assert.state(updated == 1, "Failed to update student " + student.getName());
    }

    //Update Student
    public void updateStudent(Student student, Integer id){
        var updated = jdbcClient.sql("update Student set name = ?, age = ?, sex = ? where id = ?")
                .params(List.of(student.getName(),student.getAge(),student.getSex(),id))
                .update();
        Assert.state(updated == 1, "Failed to update student " + student.getName());
    }

    //Delete Student
    public void deleteStudent(Integer id){
        var updated = jdbcClient.sql("delete from Student where id = :id")
                .param("id",id)
                .update();
        Assert.state(updated == 1, "Failed to delete student " + id);
    }

    //Asignar cursos a Students
    public void assignStudentToClassroom(Integer studentId, Integer classroomId) {
        var updated = jdbcClient.sql("INSERT INTO student_classroom (student_id, classroom_id) VALUES (?, ?)")
                .params(List.of(studentId, classroomId))
                .update();
        Assert.state(updated == 1, "Failed to assign student " + studentId + " to classroom " + classroomId);
    }

    // Mapeo de ResultSet a objetos Student
    private RowMapper<Student> studentRowMapper() {
        return (rs, rowNum) -> new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("sex")
        );
    }

    //Mostrar el numero de alumnos por curso
    public List<Student> getStudentsByClassroom(Integer classroomId){
        String query = """
            SELECT s.id, s.name, s.age, s.sex
            FROM Student s
            INNER JOIN Student_Classroom sc ON s.id = sc.student_id
            WHERE sc.classroom_id = :classroomId;
        """;

        return jdbcClient.sql(query)
                .param("classroomId", classroomId)
                .query(studentRowMapper())
                .list();
    }

    //Promedio de alumnos por genero en un curso
    public String getSexPercentageByClassroom(Integer classroomId) {
        String query = "SELECT " +
                "SUM(CASE WHEN sex = 'Masculino' THEN 1 ELSE 0 END) AS male_count, " +
                "SUM(CASE WHEN sex = 'Femenino' THEN 1 ELSE 0 END) AS female_count, " +
                "COUNT(*) AS total_count " +
                "FROM student_classroom sc " +
                "JOIN student s ON sc.student_id = s.id " +
                "WHERE sc.classroom_id = :classroomId";

        return jdbcClient.sql(query)
                .param("classroomId", classroomId)
                .query((rs, rowNum) -> {
                    int maleCount = rs.getInt("male_count");
                    int femaleCount = rs.getInt("female_count");
                    int totalCount = rs.getInt("total_count");

                    double malePercentage = totalCount > 0 ? ((double) maleCount / totalCount) * 100 : 0;
                    double femalePercentage = totalCount > 0 ? ((double) femaleCount / totalCount) * 100 : 0;

                    return String.format("Hombres: %.2f%%, Mujeres: %.2f%%",
                            malePercentage, femalePercentage);
                })
                .stream()
                .findFirst()
                .orElse("Curso no encontrado o sin estudiantes.");
    }

    //Numero de alumnos por genero
    public List<Student> getStudentsBySex(String sex){
        String query = """
            SELECT *
            FROM Student
            WHERE sex = :sex;
            """;

        return jdbcClient.sql(query)
                .param("sex", sex)
                .query(Student.class)
                .list();
    }

}