<script setup lang="ts">

import { ref } from 'vue';
import config from "@/config";

const username = ref('')
const password = ref('')
const isDriver = ref(false)
const licensePlateNum = ref('')


function registerUser() {
  if (isDriver) {
    fetch(`${config.apiBaseUrl}/addDriver`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        username: username.value,
        password: password.value,
        licensePlateNum: licensePlateNum.value
      })
    }).then(res=>console.log(res))
  }
  else {
    fetch(`${config.apiBaseUrl}/addRider`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        username: username.value,
        password: password.value,
      })
    }).then(res=>console.log(res))
  }
  
}

</script>

<template>
  <main>
    <h1>User registration</h1>
    <form @submit.prevent="registerUser">
    <input v-model="username" placeholder="Enter username">
    <input v-model="password" placeholder="Enter password" type="password">
    <br>
    <label><input type="checkbox" v-model="isDriver">Register as driver?</label>
    <br>
    <input v-if="isDriver" v-model="licensePlateNum" placeholder="License plate number">
    <br>
    <button>Register</button>
  </form>
  </main>
</template>
