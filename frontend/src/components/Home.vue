<template>
    <div>
        <h1>Pagina de inicio </h1>
        <p>Esta es la pagina para la prueba tecnica para Fullstack developer en Vulcan por parte del candidato Sergio Laguna</p>
        <h2>Requisitos de la prueba tecnica</h2>
        <h3>Prueba</h3>
        <p>Servicio Rest - 4 servicios REST JAVA.</p>
          <ul>
            <h4>Backend</h4>
            <li>Autenticación (simple)</li>
            <li>CRUD Alumno (nombre, edad, género, curso asignado)</li>
            <li>CRUD Curso (nombre, cupo máximo, alumnos)</li>
            <li>Mostrar total de cursos</li>
            <li>Mostrar total de alumnos</li>
            <li>Mostrar promedio de alumnos por género/curso</li>
            <li>Mostrar promedio de capacidad de cursos (que tan llenos estan)</li>
            <li>Mostrar cantidad de alumnos por curso</li>
            <li>Mostrar cantidad de alumnos por género</li>
            <li>Mostrar Total alumnos por género por curso</li>
            <li>Mostrar cupo (porcentaje de capacidad de cada curso)</li>
            <li>*Bonus despliegue en ambiente público</li>
            <h4>Frontend</h4>
            <li>Consumir el backend desarrollado.</li>
          </ul>

        <h3>Tecnologias</h3>
        <ul>
          <h4>Backend</h4>
          <li>Springboot3</li>
          <li>Java</li>
          <li>Postman</li>
          <li>Axios</li>
        </ul>
        <ul>
          <h4>Frontend</h4>
          <li>Vuejs 3</li>
        </ul>
        <ul>
          <h4>Otros</h4>
          <li>Git</li>
          <li>Github</li>
        </ul>

    </div>
</template>
  
  <script>
  export default {
    name: 'Home'
  }
  </script>
  
  <style scoped>
  header {
    background-color: #f0f0f0;
    padding: 20px;
  }
  </style>