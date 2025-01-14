<template>
  <header>
    <router-link to="/">
      <img src="../assets/logo.jpeg" alt="Logo de tu sitio" /> 
    </router-link>
    <nav>
      <ul>
        <li><router-link to="/students">Alumnos</router-link></li>
        <li><router-link to="/classrooms">Aulas</router-link></li>
      </ul>
    </nav>
  </header>
</template>
  
<script>
  export default {
    name: 'Header'
  }
</script>
  
<style scoped>
  header {
    background-color: #b31f25;
    padding: 20px;
  }
</style>