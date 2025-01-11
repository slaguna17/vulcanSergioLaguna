package vulcanSergioLaguna.backend.classroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
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
    List<Classroom> findAllClassrooms(){
        return jdbcClient.sql("select * from classroom").query(Classroom.class).list();
    }

    //Get Classroom by ID
    Optional<Classroom> findClassroomById(Integer id){
        return jdbcClient.sql("SELECT id, name, maxCapacity FROM Classroom WHERE id = :id")
                .param("id",id)
                .query(Classroom.class)
                .optional();
    }

    //Create Classroom
    void createClassroom(Classroom classroom){
        var updated = jdbcClient.sql("INSERT INTO Classroom(id, name, maxCapacity) VALUES (?,?,?)")
                .params(List.of(classroom.id(),classroom.name(),classroom.maxCapacity()))
                .update();
        Assert.state(updated == 1, "Failed to create classroom " + classroom.name());
    }

    //Update Classroom
    void updateClassroom(Classroom classroom, Integer id){
        var updated = jdbcClient.sql("update Classroom set name = ?, maxCapacity = ? where id = ?")
                .params(List.of(classroom.name(),classroom.maxCapacity(),id))
                .update();
        Assert.state(updated == 1, "Failed to update classroom " + classroom.name());
    }

    //Delete Classroom
    void deleteClassroom(Integer id){
        var updated = jdbcClient.sql("delete from Classroom where id = :id")
                .param("id",id)
                .update();
        Assert.state(updated == 1, "Failed to delete classroom " + id);
    }
}

