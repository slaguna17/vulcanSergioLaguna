import axios from 'axios';

export default {

  //Students CRUD
  findAllStudents() {
    return axios.get('http://localhost:8080/api/students'); // Ajusta la URL según tu backend
  },

  findStudentById(id){
    return axios.get('http://localhost:8080/api/students/${id}')
  },

  createStudent(student) {
    return axios.post('http://localhost:8080/api/createStudent', student);
  },

  updateStudent(id, student) {
    return axios.put(`http://localhost:8080/api/updateStudent/${id}`, student);
  },

  deleteStudent(id) {
    return axios.delete(`http://localhost:8080/api/updateStudent/${id}`);
  },

  //Classroom CRUD
  findAllClassrooms() {
    return axios.get('http://localhost:8080/api/classrooms'); // Ajusta la URL según tu backend
  },

  findClassroomId(id){
    return axios.get('http://localhost:8080/api/classrooms/${id}')
  },

  createClassroom(classroom) {
    return axios.post('http://localhost:8080/api/createClassroom', student);
  },

  updateClassroom(id, classroom) {
    return axios.put(`http://localhost:8080/api/updateClassroom/${id}`, student);
  },

  deleteClassroom(id) {
    return axios.delete(`http://localhost:8080/api/updateClassroom/${id}`);
  }

  //Specific API
}