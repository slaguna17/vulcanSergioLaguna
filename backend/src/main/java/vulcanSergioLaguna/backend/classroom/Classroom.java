package vulcanSergioLaguna.backend.classroom;

import vulcanSergioLaguna.backend.student.Student;

import java.util.List;

public record Classroom(
        Integer id,
        String name,
        Integer maxCapacity,
        List<Student> studentsAssigned
) {
}
