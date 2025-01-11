package vulcanSergioLaguna.backend.student;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import vulcanSergioLaguna.backend.classroom.Classroom;

public record Student(
        @Positive
        Integer id,
        @NotEmpty
        String name,
        @Positive
        Integer age,
        String sex
) {
}
