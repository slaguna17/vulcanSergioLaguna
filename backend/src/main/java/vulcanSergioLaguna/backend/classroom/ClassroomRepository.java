package vulcanSergioLaguna.backend.classroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import vulcanSergioLaguna.backend.student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClassroomRepository {
    //In memory repository
    private List<Classroom> classrooms = new ArrayList<>();

    //JDBC
    private static final Logger log = LoggerFactory.getLogger(vulcanSergioLaguna.backend.classroom.ClassroomRepository.class);
    private final JdbcClient jdbcClient;

    public ClassroomRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    //Get all Classrooms
    public List<Classroom> findAllClassrooms(){
        return jdbcClient.sql("select * from classroom").query(Classroom.class).list();
    }

    //Get Classroom by ID
    public Optional<Classroom> findClassroomById(Integer id){
        return jdbcClient.sql("SELECT id, name, max_capacity FROM Classroom WHERE id = :id")
                .param("id",id)
                .query(Classroom.class)
                .optional();
    }

    //Create Classroom
    public void createClassroom(Classroom classroom){
        var updated = jdbcClient.sql("INSERT INTO Classroom(name, max_capacity) VALUES (?,?)")
                .params(List.of(classroom.getName(),classroom.getMax_capacity()))
                .update();
        Assert.state(updated == 1, "Failed to create classroom " + classroom.getName());
    }

    //Update Classroom
    public void updateClassroom(Classroom classroom, Integer id){
        var updated = jdbcClient.sql("update Classroom set name = ?, max_capacity = ? where id = ?")
                .params(List.of(classroom.getName(),classroom.getMax_capacity(),id))
                .update();
        Assert.state(updated == 1, "Failed to update classroom " + classroom.getName());
    }

    //Delete Classroom
    public void deleteClassroom(Integer id){
        var updated = jdbcClient.sql("delete from Classroom where id = :id")
                .param("id",id)
                .update();
        Assert.state(updated == 1, "Failed to delete classroom " + id);
    }
    // Mapeo de ResultSet a objetos Student
    private RowMapper<Classroom> classroomRowMapper() {
        return (rs, rowNum) -> new Classroom(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("max_capacity")
        );
    }

    // Método para obtener las aulas asignadas a un estudiante
    public List<Classroom> getClassroomsByStudent(Integer studentId) {
        String query = """
                SELECT c.id, c.name, c.max_capacity
                FROM classroom c
                JOIN student_classroom sc ON c.id = sc.classroom_id
                WHERE sc.student_id = :studentId
                """;
        List<Classroom> classrooms = new ArrayList<>();

        return jdbcClient.sql(query)
                .param("studentId", studentId)
                .query(classroomRowMapper())
                .list();
    }

    //Occupied percentage by Classroom
    public Double getPercentageOccupiedByClassroom(Integer classroomId){
        String query = "SELECT c.max_capacity, COUNT(sc.student_id) AS student_count " +
                "FROM classroom c " +
                "LEFT JOIN student_classroom sc ON c.id = sc.classroom_id " +
                "WHERE c.id = :classroomId " +
                "GROUP BY c.max_capacity";

        return jdbcClient.sql(query)
                .param("classroomId", classroomId)
                .query((rs, rowNum) -> {
                    Integer maxCapacity = rs.getInt("max_capacity");
                    Integer studentCount = rs.getInt("student_count");

                    if (maxCapacity == 0) {
                        return 0.0; // Avoid division by zero
                    }

                    return ((double) studentCount / maxCapacity) * 100;
                })
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Classroom not found"));
    }

}

