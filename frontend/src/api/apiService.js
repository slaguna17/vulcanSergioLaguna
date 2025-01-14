import axios from 'axios';
import axiosClient from './axiosClient';

export default {
  //Students CRUD
  findAllStudents() {
    return axiosClient.get('http://localhost:8080/api/students'); 
  },

  findStudentById(id){
    return axiosClient.get(`http://localhost:8080/api/students/${id}`)
  },

  createStudent(student) {
    return axiosClient.post('http://localhost:8080/api/createStudent', student);
  },

  updateStudent(id, student) {
    return axiosClient.put(`http://localhost:8080/api/updateStudent/${id}`, student);
  },

  deleteStudent(id) {
    return axiosClient.delete(`http://localhost:8080/api/deleteStudent/${id}`);
  },

  //Classroom CRUD
  findAllClassrooms() {
    return axiosClient.get('http://localhost:8080/api/classrooms');
  },

  findClassroomById(id){
    return axiosClient.get(`http://localhost:8080/api/classrooms/${id}`)
  },

  createClassroom(classroom) {
    return axiosClient.post('http://localhost:8080/api/createClassroom', classroom);
  },

  updateClassroom(id, classroom) {
    return axiosClient.put(`http://localhost:8080/api/updateClassroom/${id}`, classroom);
  },

  deleteClassroom(id) {
    return axiosClient.delete(`http://localhost:8080/api/deleteClassroom/${id}`);
  },

  //Specific API
  assignStudentToClassroom(studentId,classroomId) {
    const params = new URLSearchParams();
    params.append('studentId', studentId);
    params.append('classroomId', classroomId);
    return axiosClient.post(`http://localhost:8080/api/assignments`,params);
  },

  getPercentOccupiedInClassroom(id){
    return axiosClient.get(`http://localhost:8080/api/classroom/${id}/occupancy`);
  },

  getStudentsByClassroom(id){
    return axiosClient.get(`http://localhost:8080/api/classroom/${id}/students`);
  },

  getStudentsBySex(sex){
    return axiosClient.get(`http://localhost:8080/api/students/sex/${sex}`);
  },

  getAverageSexByClassroom(id){
    return axiosClient.get(`http://localhost:8080/api/classroom/${id}/averageStudentPerSex`);
  },

  getClassroomsByStudent(id){
    return axiosClient.get(`http://localhost:8080/api/students/${id}/classrooms`);
  }

  
}