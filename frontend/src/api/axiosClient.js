import axios from 'axios';

const axiosClient = axios.create({
  baseURL: 'http://localhost:8080/api', // Cambia la URL según tu backend
  headers: {
    'Content-Type': 'application/json',
  },
});

export default axiosClient;