<template>
  <div>
    <h1>Gestión de Cursos</h1>
    <button @click="fetchAllClassrooms()">Ver todos los cursos</button>

    <h2>Crear curso</h2>
    <!-- Formulario -->
    <form @submit.prevent="saveClassroom()">
      <input type="text" v-model="classroom.name" placeholder="Nombre del Curso" required />
      <input type="number" v-model="classroom.max_capacity" placeholder="Capacidad Maxima de estudiantes" required />
      <button type="submit">{{ editing ? 'Actualizar' : 'Crear' }}</button>
    </form>

    <p>Total de cursos {{classrooms.length}}</p>
      <!-- <input type="text" v-model="byGender" placeholder="Ordenar por genero" required />
      <button @click="sortByGender()">Buscar</button>
      <button @click="fetchAllStudents()">Restaurar</button> -->
      <table class="data-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Capacidad Máxima</th>
          <th>Alumnos</th>
          <th>Número de Alumnos</th>
          <th>Número por Género</th>
          <th>Porcentaje por Género</th>
          <th>Capacidad Utilizada (%)</th>
          <th>Espacio Libre (%)</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="classroom in classrooms" :key="classroom.id">
          <td>{{ classroom.id }}</td>
          <td>{{ classroom.name }}</td>
          <td>{{ classroom.max_capacity }}</td>
          <td>
            <ul>
              <li v-for="student in classroom.students" :key="student.id">
                {{ student.name }}
              </li>
            </ul>
          </td>
          <td>{{ classroom.students.length }}</td>
          <td>
            Hombres: {{ classroom.maleCount }} / Mujeres: {{ classroom.femaleCount }}
          </td>
          <td>
            Hombres: {{ classroom.malePercentage }}% / Mujeres: {{ classroom.femalePercentage }}%
          </td>
          <td>{{ classroom.occupancyPercentage }}%</td>
          <td>{{ classroom.freeSpacePercentage }}%</td>
        </tr>
      </tbody>
    </table>
    
  </div>
</template>

<script>
import apiService from '@/api/apiService';

export default {
  data() {
    return {
      classrooms: [],
      classroom: { name: '', max_capacity: null},
      studentsByClassroom: {},
      occupancyByClassroom:{},
      averageBySex: {},
      editing: false,
    };
  },
  methods: {
    async fetchAllClassrooms() {
      try {
        // Obtiene todas las aulas.
        const { data: classrooms } = await apiService.findAllClassrooms();

        // Enriquecer las aulas con datos adicionales.
        const filledClassrooms = await Promise.all(
          classrooms.map(async (classroom) => {
            // Obtener alumnos asignados.
            const { data: students } = await apiService.getStudentsByClassroom(classroom.id);
            // Cálculos adicionales.
            const maleCount = students.filter((s) => s.sex === "Masculino").length;
            const femaleCount = students.filter((s) => s.sex === "Femenino").length;
            const totalStudents = students.length;
            const malePercentage =
              totalStudents > 0 ? ((maleCount / totalStudents) * 100).toFixed(2) : 0;
            const femalePercentage =
              totalStudents > 0 ? ((femaleCount / totalStudents) * 100).toFixed(2) : 0;
            const occupancyPercentage = (
              (totalStudents / classroom.max_capacity) *
              100
            ).toFixed(2);
            const freeSpacePercentage = (
              100 - occupancyPercentage
            ).toFixed(2);

            return {
              ...classroom,
              students,
              maleCount,
              femaleCount,
              malePercentage,
              femalePercentage,
              occupancyPercentage,
              freeSpacePercentage,
            };
          })
        );

        this.classrooms = filledClassrooms;
      } catch (error) {
        console.error("Error al extraer cursos:", error);
      }
  },
    async saveClassroom() {
      try {
        if (this.editing) {
          await apiService.updateClassroom(this.classroom.id, this.classroom);
        } else {
          await apiService.createClassroom(this.classroom);
        }
        this.resetForm();
        this.fetchAllClassrooms();
      } catch (error) {
        console.error('Error al guardar curso:', error);
      }
    },
    editClassroom(classroom) {
      this.classroom = { ...classroom };
      this.editing = true;
    },
    async deleteClassroom(id) {
      try {
        await apiService.deleteClassroom(id);
        this.fetchAllClassrooms();
      } catch (error) {
        console.error('Error al eliminar curso:', error);
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
      this.classroom = { name: '', age: null, sex: null };
      this.editing = false;
    },
    async getAverageSexByClassroom(id){
      try {
        const result = await apiService.getAverageSexByClassroom(id);
        this.averageBySex[id] = {
          male: result.data.male || 0,
          female: result.data.female || 0,
          malePercentage: result.data.malePercentage || 0,
          femalePercentage: result.data.femalePercentage || 0,
        }
      } catch (error) {
        console.error(`Error al conseguir data de genero por curso${id}:`, error);
      }
      
      this.averageBySex[id] = result.data
    },
    async getPercentOccupiedInClassroom(id){
      try {
        const response = await apiService.getPercentOccupiedInClassroom(id);
        this.occupancyByClassroom[id] = {
          usedPercentage: response.data.usedPercentage || 0,
          freeSpace: response.data.freeSpace || 0,
        }
      } catch (error) {
        console.error('Error al obtener porcentajes de cursos:', error);
      }
    }
  },
  created(){
    this.fetchAllClassrooms()
  },

};
</script>
