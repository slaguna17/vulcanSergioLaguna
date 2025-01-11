-- Crear tabla para los estudiantes
CREATE TABLE IF NOT EXISTS Student (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    sex VARCHAR(10)
);

-- Crear tabla para las aulas
CREATE TABLE IF NOT EXISTS Classroom (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    maxCapacity INT NOT NULL
);

-- Crear tabla intermedia para la relación muchos a muchos
CREATE TABLE IF NOT EXISTS Student_Classroom (
    student_id INT NOT NULL,
    classroom_id INT NOT NULL,
    PRIMARY KEY (student_id, classroom_id),
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES Student (id) ON DELETE CASCADE,
    CONSTRAINT fk_classroom FOREIGN KEY (classroom_id) REFERENCES Classroom (id) ON DELETE CASCADE
);