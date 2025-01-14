package vulcanSergioLaguna.backend.student;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import vulcanSergioLaguna.backend.classroom.Classroom;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @NotEmpty
        private String name;
        @Positive
        private Integer age;
        private String sex;
        @ManyToMany
        @JoinTable(
                name="student_classroom",
                joinColumns = @JoinColumn (name = "student_id"),
                inverseJoinColumns = @JoinColumn (name = "classroom_id")
        )
        Set<Classroom> classrooms = new HashSet<>();

        public Student(Integer id, String name, Integer age, String sex) {
                this.id = id;
                this.name = name;
                this.age = age;
                this.sex = sex;
        }

        public Student() {
                // Constructor vacío necesario para Hibernate y mapeos
        }


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

        public @Positive Integer getAge() {
                return age;
        }

        public void setAge(@Positive Integer age) {
                this.age = age;
        }

        public String getSex() {
                return sex;
        }

        public void setSex(String sex) {
                this.sex = sex;
        }

        public Set<Classroom> getClassrooms() {
                return classrooms;
        }

        public void setClassrooms(Set<Classroom> classrooms) {
                this.classrooms = classrooms;
        }
}
