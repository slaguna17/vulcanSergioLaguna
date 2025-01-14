package vulcanSergioLaguna.backend.classroom;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import vulcanSergioLaguna.backend.student.Student;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "classroom")
public class Classroom{
        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @NotEmpty
        private String name;
        @Column(name = "max_capacity")
        @Positive
        private Integer max_capacity;

        @ManyToMany(mappedBy = "classrooms")
        private Set<Student> students = new HashSet<>();

        public Classroom() {
        }

        public Classroom(Integer id, String name, Integer max_capacity) {
                this.id = id;
                this.name = name;
                this.max_capacity = max_capacity;
        }

        //Getters and Setters
        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public @NotEmpty String getName() {
                return name;
        }

        public void setName(@NotEmpty String name) {
                this.name = name;
        }

        public @Positive Integer getMax_capacity() {
                return max_capacity;
        }

        public void setMax_capacity(@Positive Integer max_capacity) {
                this.max_capacity = max_capacity;
        }

        public Set<Student> getStudents() {
                return students;
        }

        public void setStudents(Set<Student> students) {
                this.students = students;
        }
}
