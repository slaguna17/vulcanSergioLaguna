package vulcanSergioLaguna.backend.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import vulcanSergioLaguna.backend.classroom.Classroom;
import vulcanSergioLaguna.backend.classroom.ClassroomRepository;
import vulcanSergioLaguna.backend.student.Student;
import vulcanSergioLaguna.backend.student.StudentRepository;

@Repository
public class AssignmentService {

    public AssignmentService(StudentRepository studentRepository, ClassroomRepository classroomRepository) {
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
    }

    private StudentRepository studentRepository;

    private ClassroomRepository classroomRepository;

    public void assignStudentToClassroom(Integer studentId, Integer classroomId) {
        Student student = studentRepository.findStudentById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Classroom classroom = classroomRepository.findClassroomById(classroomId)
                .orElseThrow(() -> new RuntimeException("Classroom not found"));

        // Add the classroom to the student's set of classrooms
        student.getClassrooms().add(classroom);
        // Add the student to the classroom's set of students
        classroom.getStudents().add(student);

        // Assign the student to the classroom
        studentRepository.assignStudentToClassroom(studentId, classroomId);
    }
}