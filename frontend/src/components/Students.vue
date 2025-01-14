<template>
  <div>
    <h1>Gestión de Estudiantes</h1>
    <button @click="fetchAllStudents()">Ver todos los estudiantes</button>

    <h2>Crear estudiante</h2>
    <!-- Formulario -->
    <form @submit.prevent="saveStudent()">
      <input type="text" v-model="student.name" placeholder="Nombre del Estudiante" required />
      <input type="number" v-model="student.age" placeholder="Edad" required />
      <input type="text" v-model="student.sex" placeholder="Genero" required />
      <button type="submit">{{ editing ? 'Actualizar' : 'Crear' }}</button>
    </form>

    <p>Total de estudiantes {{students.length}}</p>
      <input type="text" v-model="byGender" placeholder="Ordenar por genero" required />
      <button @click="sortByGender()">Buscar</button>
      <button @click="fetchAllStudents()">Restaurar</button>

    <!-- Tabla de estudiantes -->
    <table v-if="students.length != null">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Edad</th>
          <th>Genero</th>
          <th>Clases</th>
          <th>Acciones</th>

        </tr>
      </thead>
      <tbody>
        <tr v-for="s in students" :key="s.id">
          <td>{{ s.id }}</td>
          <td>{{ s.name }}</td>
          <td>{{ s.age }}</td>
          <td>{{ s.sex }}</td>
          <td>
            <ul>
              <li v-for="classroom in classroomsByStudent[s.id]" :key="classroom.id">
                {{ classroom.name }}
              </li>
            </ul>
          </td>
          <td>
            <button @click="editStudent(s)">Editar</button>
            <button @click="deleteStudent(s.id)">Eliminar</button>
          </td>
        </tr>
      </tbody>
    </table>
    <p v-else>No hay estudiantes</p>
    <p v-if="numberOfStudentsInGender">Total de estudiantes {{ byGender }} {{students.length}}</p>
    <p v-else></p>
    
  </div>
</template>

<script>
import apiService from '@/api/apiService';

export default {
  data() {
    return {
      students: [],
      student: { name: '', age: null, sex: null},
      classroomsByStudent: {},
      editing: false,
      specificClicked: false,
      specific: null,
      byGender: null,
      numberOfStudentsInGender: false
    };
  },

  methods: {
    async fetchAllStudents() {
      try {
        const response = await apiService.findAllStudents();
        this.students = response.data;

        // Llamar a fetchAllClassroomsByStudent para cada estudiante
        for (const student of this.students) {
          await this.fetchAllClassroomsByStudent(student.id);
        }
      } catch (error) {
        console.error('Error al obtener estudiantes:', error);
      }
    },
    async fetchAllClassroomsByStudent(id) {
      try {
        const response = await apiService.getClassroomsByStudent(id);
        this.classroomsByStudent[id] = response.data;
      } catch (error) {
        console.error('Error al obtener estudiantes:', error);
      }
    },
    async saveStudent() {
      try {
        if (this.editing) {
          await apiService.updateStudent(this.student.id, this.student);
        } else {
          await apiService.createStudent(this.student);
        }
        this.resetForm();
        this.fetchAllStudents();
      } catch (error) {
        console.error('Error al guardar estudiante:', error);
      }
    },
    editStudent(student) {
      this.student = { ...student };
      this.editing = true;
    },
    async deleteStudent(id) {
      try {
        await apiService.deleteStudent(id);
        this.fetchAllStudents();
      } catch (error) {
        console.error('Error al eliminar estudiante:', error);
      }
    },
    async assignStudent(studentId, classroomId){
      try {
        await apiService.assignStudentToClassroom(studentId,classroomId)
      } catch (error) {
        console.error('Error al asignar aula al estudiante:', error);
      }
    },
    resetForm() {
      this.student = { name: '', age: null, sex: null };
      this.editing = false;
    },
    sortByGender(){
      this.sortBySex(this.byGender);
      this.numberOfStudentsInGender = true;
    },

    async sortBySex(sex){
      try {
        const response = await apiService.getStudentsBySex(sex);
        this.students = response.data;
      } catch (error) {
        console.error('Error al obtener estudiantes:', error);
      }
    },
  },
  created(){
    this.fetchAllStudents()
  },
};
</script>
