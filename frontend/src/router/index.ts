import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from "@/views/AboutView.vue";
import CatsView from "@/views/CatsView.vue";
import RiderView from "@/views/RiderView.vue";
import DriverView from "@/views/DriverView.vue";
import UserRegistrationView from "@/views/UserRegistrationView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView
    },
    {
      path: '/rider',
      name: 'rider',
      component: RiderView
    },
    {
      path: '/driver',
      name: 'driver',
      component: DriverView
    },
    {
      path: '/registration',
      name: 'registerUser',
      component: UserRegistrationView
    }
  ]
})

export default router
