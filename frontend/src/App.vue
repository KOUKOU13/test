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
<div :class="{ 'dark-mode': isDarkMode }">
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
<<<<<<< HEAD
          <HeaderNavItem>
            <button @click="toggleDarkMode">Toggle Dark Mode</button>
          </HeaderNavItem>
=======
          <!-- <HeaderNavItem>
            <h4>Login?</h4>
          </HeaderNavItem> -->
          <h4 v-if="loggedIn" style="position: absolute;right: 0;padding: 3em;">Welcome {{ userFirstName }}</h4>
>>>>>>> 6e6891445ced48a6f82f804f2518645e4c0f7c0e
        </HeaderNav>
   

      </template>
    </Header>
    <div class="main">
      <RouterView/>
    </div>
  </div>
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
      isDarkMode: false,
    };
  },
  methods: {
    toggleDarkMode() {
      this.isDarkMode = !this.isDarkMode;
    },
  },
};
</script>

<style scoped>
/* App.vue styles */
.dark-mode {
  --background-color: #333; /* dark mode background color */
  --text-color: #fff; /* dark mode text color */
}
</style>
