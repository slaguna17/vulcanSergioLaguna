import { createRouter, createWebHashHistory } from "vue-router";
import Home from "../components/Home.vue";
import Students from "../components/Students.vue";
import Classrooms from "../components/Classrooms.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: Home,
  },
  {
    path: "/students",
    name: "students",
    component: Students,
  },
  {
    path: "/classrooms",
    name: "classrooms",
    component: Classrooms,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
