package vulcanSergioLaguna.backend.classroom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
public class ClassroomController {

//    CRUD Curso (nombre, cupo máximo, alumnos )
//    Get Cursos
//    Respuesta general: total cursos, total alumnos, promedio de alumnos por género/curso, promedio de capacidad de cursos (que tan llenos estan)
//    Respuesta en array
//    listados cursos, mostrar cantidad de alumnos por curso
//    cantidad de alumnos por género
//    Total alumnos por género por curso
//    Mostrar cupo (porcentaje de capacidad de cada curso
//    *Bonus despliegue en ambiente público

    //Declare Repository and Constructor
    private final ClassroomRepository classroomRepository;

    public ClassroomController(ClassroomRepository classroomRepository){
        this.classroomRepository = classroomRepository;
    }

    //Get all Classrooms
    @GetMapping("/api/classrooms")
    List<Classroom> findAllClassrooms(){
        return classroomRepository.findAllClassrooms();
    }

    //Get a Classroom by ID
    @GetMapping("/api/classrooms/{id}")
    Classroom findClassroomById(@PathVariable Integer id){
        Optional<Classroom> classroom = classroomRepository.findClassroomById(id);
        if (classroom.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return classroom.get();
    }

    //Create a Classroom (POST)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/createClassroom")
    void createClassroom(@RequestBody Classroom classroom){
        classroomRepository.createClassroom(classroom);
    }

    //Update a Classroom (PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/api/updateClassroom/{id}")
    void updateClassroom(@RequestBody Classroom classroom, @PathVariable Integer id){
        classroomRepository.updateClassroom(classroom,id);
    }

    //Delete a Run (DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/deleteClassroom/{id}")
    void deleteClassroom(@PathVariable Integer id){
        classroomRepository.deleteClassroom(id);
    }
}
