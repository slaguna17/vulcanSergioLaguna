package vulcanSergioLaguna.backend.student;

import vulcanSergioLaguna.backend.classroom.Classroom;

public record Student(
        Integer id,
        String name,
        Integer age,
        String sex,
        Classroom assignedClassroom
) {
}
