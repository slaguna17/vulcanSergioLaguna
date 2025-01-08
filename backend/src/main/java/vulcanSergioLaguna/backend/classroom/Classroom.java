package vulcanSergioLaguna.backend.classroom;

import vulcanSergioLaguna.backend.student.Student;

public record Classroom(
        Integer id,
        String name,
        Integer maxCapacity,
        Student[] studentsAsigned
) {
}
