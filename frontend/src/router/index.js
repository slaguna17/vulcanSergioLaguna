import { createRouter, createWebHashHistory } from "vue-router";
import Home from "../components/Home.vue";
import Requirements from "../components/Requirements.vue";
import Students from "../components/Students.vue";
import Classrooms from "../components/Classrooms.vue";
import Extras from "../components/Extras.vue";


const routes = [
  {
    path: "/",
    name: "home",
    component: Home,
  },
  {
    path: "/requirements",
    name: "requirements",
    component: Requirements,
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
  {
    path: "/extras",
    name: "extras",
    component: Extras,
  },

];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
