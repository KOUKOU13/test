<script setup lang="ts">
import {RouterView} from 'vue-router'
import {Close, Header, HeaderNav, HeaderNavItem, Toast, Toasts} from "agnostic-vue";
import {activeToasts} from "@/ts/toasts";
import 'agnostic-vue/dist/common.min.css';
import "agnostic-vue/dist/index.css";

let loggedIn = localStorage.getItem("userID") ? true : false
console.log(loggedIn)
let userFirstName = localStorage.getItem("userFirstName")

function logoutFunc() {
  localStorage.clear()
  console.log("logging out")
  location.reload()
}

</script>

<template>
<div :class="{ 'dark-mode': isDarkMode,'light-mode': !isDarkMode, }">
  <div id="app">
    <Header isHeaderContentStart>
      <template v-slot:headernav>
        <HeaderNav>
          <HeaderNavItem>
            <RouterLink to="/">Home</RouterLink>
          </HeaderNavItem>
          <HeaderNavItem>
            <RouterLink to="/rider">Rider</RouterLink>
          </HeaderNavItem>
          <HeaderNavItem>
            <RouterLink to="/driver">Driver</RouterLink>
          </HeaderNavItem>
          <HeaderNavItem>
            <RouterLink to="/registration">Register</RouterLink>
          </HeaderNavItem>
          <HeaderNavItem v-if="!loggedIn">
            <RouterLink to="/login">Login</RouterLink>
          </HeaderNavItem>
          <HeaderNavItem v-if="loggedIn">
            <!-- <RouterLink to="/login">Logout</RouterLink> -->
            <h4 to="/" @click="logoutFunc">Logout</h4>
          </HeaderNavItem>
          <HeaderNavItem>
            <RouterLink to="/about">About</RouterLink>
          </HeaderNavItem>
          <HeaderNavItem>
                <!-- Dark Mode Toggle Button -->
            <button @click="toggleDarkMode">Toggle Dark Mode</button>
          </HeaderNavItem>
          <HeaderNavItem>
            <RouterLink to="invite">Invite a friend</RouterLink>
          </HeaderNavItem>
          <HeaderNavItem>
            <RouterLink to="/profile" v-if="loggedIn" style="position: absolute;right: 0; padding-right: 3em;">Welcome {{ userFirstName }}. Click to edit profile.</RouterLink>
          </HeaderNavItem>
        </HeaderNav>
       </template>
     </Header>
    <div class="main">
      <RouterView/>
    </div>
  </div>

  <Toasts vertical-position="top" horizontal-position="end">
    <template v-for="toast of activeToasts" :key="toast.key">
      <Toast :type="toast.type" class="alert alert-border-left alert-info">
        <div class="flex-fill flex flex-column">
          <div class="flex">
            <h3 class="flex-fill">{{ toast.title }}</h3>
            <Close @click="toast.close()"/>
          </div>
          <div class="flex">
            <font-awesome-icon :icon="toast.icon" size="xl" class="mie8 pbs2"></font-awesome-icon>
            <div class="flex-fill">
              {{ toast.message }}
            </div>
          </div>
        </div>
      </Toast>
      <div class="mbe14"/>
    </template>
  </Toasts>


</div>
</template>



<style scoped>
.main {
  padding: 10px 20px;
}
</style>

<!-- App.vue -->
<script lang="ts">
export default {
  data() {
    return {
      isDarkMode: localStorage.getItem('darkMode') === 'true' || false,

    };
  },
  methods: {
    toggleDarkMode() {
      this.isDarkMode = !this.isDarkMode;
    console.log(this.isDarkMode);
    localStorage.setItem('darkMode', this.isDarkMode.toString());
    },
  },
};
</script>

<style scoped>
/* App.vue styles */
/* Import the style.css file from the assets folder */
@import './assets/style.css';
/* Set common styles for both dark and light modes */
body {
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  height: 100vh;
  margin: 0;
  display: flex;
  flex-direction: column;
}
/* Set background image for light mode */

.dark-mode {
  background-image: url('../images/background-image.jpg'); /* Set background image */
  background-size: cover; /* Ensure the background image covers the entire container */
  height: 100vh; /* Set the container height to the full viewport height */
  display: flex;
  flex-direction: column; 
  --background-color: #333; /* dark mode background color */
  --text-color: #fff; /* dark mode text color */
}
/* Set background image for light mode */
.light-mode{
  background-image: url('../images/background-image-light.jpg');
  background-size: cover; /* Ensure the background image covers the entire container */
  height: 100vh; /* Set the container height to the full viewport height */
  display: flex;
  flex-direction: column; 
  --background-color: #333; /* dark mode background color */
  --text-color: #fff; /* dark mode text color */
}
/* Styles for the title */
.title {
  font-family: 'My Font', sans-serif; 
  font-size: 24px; /* Adjust the font size as needed */
  font-weight: bold; /* Adjust the font weight */
}
</style>
