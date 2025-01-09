package vulcanSergioLaguna.backend.classroom;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import vulcanSergioLaguna.backend.student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClassroomRepository {
    private List<Classroom> classrooms = new ArrayList<>();

    //Get all Classrooms
    List<Classroom> findAllClassrooms(){
        return classrooms;
    }

    //Get Classroom by ID
    Optional<Classroom> findClassroomById(Integer id){
        return classrooms.stream()
                .filter(classroom -> classroom.id() == id)
                .findFirst();
    }

    //Create Classroom
    void createClassroom(Classroom classroom){
        classrooms.add(classroom);
    }

    //Update Classroom
    void updateClassroom(Classroom classroom, Integer id){
        Optional<Classroom> existingClassroom = findClassroomById(id);
        if (existingClassroom.isPresent()){
            classrooms.set(classrooms.indexOf(existingClassroom.get()),classroom);
        }
    }

    //Delete Classroom
    void deleteClassroom(Integer id){
        classrooms.removeIf(classroom -> classroom.id().equals(id));
    }

    @PostConstruct
    private void init (){

        List<Student> students = new ArrayList<>();

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
