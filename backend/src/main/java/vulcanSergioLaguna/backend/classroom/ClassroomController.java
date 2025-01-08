package vulcanSergioLaguna.backend.classroom;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.LinkedList;

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

    @GetMapping("/getAllClassrooms")
    public void getAllClassrooms(){
        LinkedList<Classroom> classroomList = new LinkedList<Classroom>();
    }

    private int classroomID = 0;
    @GetMapping("/classroom")
    public void getClassroomByID(int id){

    }

    @PostMapping("/createClassroom")
    public void createClassroom(){

    }

    //Update
    @PostMapping("/updateClassroom")
    public void updateClassroom(int id){

    }

    //Delete
    @DeleteMapping("/deleteClassroom")
    public void deleteClassroom(int id){

    }
}
