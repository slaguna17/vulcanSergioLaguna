package vulcanSergioLaguna.backend.classroom;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record Classroom(
        @Positive
        Integer id,
        @NotEmpty
        String name,
        @Positive
        Integer maxCapacity
) {
}
