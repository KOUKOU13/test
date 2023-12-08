<script setup lang="ts">
import {Button} from "agnostic-vue";
import {showToast, Toast} from "@/ts/toasts";

let name = "";

function submit() {
  showToast(new Toast("Welcome!", `Hello ${name}! Nice to meet you!`));
}

</script>
<template>

  <h1>Welcome to e.mundo's ride & share </h1>
  <link rel="stylesheet" href="assets/style.css">
  <div>
      <h2>Pick your identity</h2>

      <!-- Buttons to navigate to driver and rider pages -->
      <router-link to="/driver">
        <button>Driver</button>
      </router-link>

      <router-link to="/rider">
        <button>Rider</button>
      </router-link>
    </div>
    <div>
      <h2>Not yet a member of e.mundo's ride & share ?</h2>
      <h3>Register here</h3>
      <!-- Buttons to navigate to driver and rider pages -->
      <router-link to="/registration">
        <button>Register</button>
      </router-link>
    </div>
    <div>
      <h2>You are a part of the family?</h2>
      <h3>Login here</h3>
      <!-- Buttons to navigate to driver and rider pages -->
      <router-link to="/Login">
        <button>Login</button>
      </router-link>
    </div>
    

 </template>
 <style scoped>
 /* Add styles to override button styles */
 button {
   color: white/* Your desired text color */;
   background-color:#333 /* Your desired background color */;
 }

 .dark-mode {
  --background-color: #333; /* dark mode background color */
  --text-color: #fff;
}
</style>






