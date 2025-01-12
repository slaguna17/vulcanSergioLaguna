package vulcanSergioLaguna.backend.classroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        var updated = jdbcClient.sql("INSERT INTO Classroom(id, name, max_capacity) VALUES (?,?,?)")
                .params(List.of(classroom.getId(),classroom.getName(),classroom.getMax_capacity()))
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

    //Occupied percentage by Classroom
    public Double getPercentageOccupiedByClassroom(Integer classroomId){
        // Get the max capacity of the classroom
        Integer maxCapacity = jdbcClient.sql("SELECT max_capacity FROM classroom WHERE id = :classroomId")
                .param("classroomId", classroomId)
                .query((rs, rowNum) -> rs.getInt("max_capacity"))
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Classroom not found"));


        // Get the number of students in the classroom
        Integer studentCount = jdbcClient.sql("SELECT COUNT(*) AS count FROM student_classroom WHERE classroom_id = :classroomId")
                .param("classroomId", classroomId)
                .query((rs, rowNum) -> rs.getInt("count"))
                .stream()
                .findFirst()
                .orElse(0); // Default to 0 if no students are found

        // Calculate the occupancy percentage
        if (maxCapacity == 0) {
            return 0.0; // Avoid division by zero
        }

        double occupancyPercentage = ((double) studentCount / maxCapacity) * 100;
        return occupancyPercentage;
    }

}

