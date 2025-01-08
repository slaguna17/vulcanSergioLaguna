# Prueba Técnica

Este repositorio contiene la implementación de la prueba técnica que incluye un backend desarrollado en Spring Boot con Java y un frontend desarrollado en Vue.js 3. El proyecto cumple con los requisitos especificados en la prueba.

## Tecnologías utilizadas

### Backend
- **Lenguaje**: Java
- **Framework**: Spring Boot
- **Autenticación**: Simple (por ejemplo, token basado en cabecera HTTP)
- **Base de datos**: PostgreSQL

### Frontend
- **Framework**: Vue.js 3
- **Visualización de datos**: 


## Requisitos implementados

### Backend
1. **Autenticación simple**: Implementado un endpoint de autenticación con verificación básica.
2. **CRUD Alumno**:
   - Operaciones para gestionar alumnos (nombre, edad, género, curso asignado).
3. **CRUD Curso**:
   - Operaciones para gestionar cursos (nombre, cupo máximo, alumnos inscritos).
4. **Consultas adicionales**:
   - Obtener el total de cursos, total de alumnos, promedio de alumnos por género/curso.
   - Promedio de capacidad de los cursos (ocupación en porcentaje).
   - Listado de cursos con la cantidad de alumnos por curso.
   - Total de alumnos por género por curso.

### Frontend
1. **Consumo del backend**:
   - Los servicios REST desarrollados en el backend se consumen desde el frontend.
2. **Gráfica interactiva**:
   - Visualización clara e interactiva de los datos obtenidos del backend.
   - Tipo de gráfica y librería a elección.


## Estructura del repositorio

```
/vulcanSergioLaguna
│
├── /frontend
│   ├── src/
│   ├── public/
│   ├── package.json
│   └── README.md
│
├── /backend
│   ├── src/
│   ├── pom.xml
│   ├── application.properties
│   └── README.md
│
├── .gitignore
├── README.md
└── LICENSE (opcional)
```


## Instrucciones de configuración

### Configuración del Backend

1. Navega al directorio `backend`:
   ```bash
   cd backend
   ```

2. Configura la base de datos en el archivo `application.properties`.

3. Construye y ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

4. Los servicios REST estarán disponibles en `http://localhost:8080`.

### Configuración del Frontend

1. Navega al directorio `frontend`:
   ```bash
   cd frontend
   ```

2. Instala las dependencias:
   ```bash
   npm install
   ```

3. Inicia el servidor de desarrollo:
   ```bash
   npm run dev
   ```

4. La aplicación frontend estará disponible en `http://localhost:3000`.


## Despliegue en ambiente público (Bonus)

1. **Backend**:
   - Despliega en un servicio como AWS, Heroku, Railway o cualquier servidor de tu elección.
2. **Frontend**:
   - Despliega en Netlify, Vercel o cualquier hosting de tu elección.
3. Proporciona las URLs públicas para evaluación.


## Contacto

**Nombre**: Sergio Laguna  
**Email**: [slaguna17@gmail.com](mailto:slaguna17@gmail.com)  
**GitHub**: [slaguna17](https://github.com/slaguna17)  
**LinkedIn**: [Sergio Laguna](www.linkedin.com/in/sergio-laguna)

