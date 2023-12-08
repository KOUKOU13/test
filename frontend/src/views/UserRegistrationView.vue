<script setup lang="ts">

import { ref } from 'vue';
import config from "@/config";
import { useRouter } from "vue-router"

const firstName = ref('')
const lastName = ref('')
const email = ref('')
const username = ref('')
const password = ref('')
const passwordConfirmation = ref('')
const isDriver = ref(false)

const router = useRouter()
// const licensePlateNum = ref('')


function registerUser() {

    fetch(`${config.apiBaseUrl}/users`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      firstName: firstName.value,
      lastName: lastName.value,
      email: email.value, // temporary
     })
  })
  .then(res=>console.log(res))
  .then(res=>router.push({ path: '/' }))

  
}

</script>

<template>
  <main>
    <h1>User registration</h1>
    <form @submit.prevent="registerUser">
    <input v-model="firstName" placeholder="Enter first name">
    <input v-model="lastName" placeholder="Enter last name">
    <input v-model="email" placeholder="Enter email">
    <input v-model="username" placeholder="Enter username">
    <input v-model="password" placeholder="Enter password" type="password">
    <input v-if="password.length > 0" v-model="passwordConfirmation" placeholder="Confirm password" type="password">
    <br>
    <label><input type="checkbox" v-model="isDriver">Register as driver?</label>
    <br>
    <!-- <input v-if="isDriver" v-model="licensePlateNum" placeholder="License plate number"> -->
    <br>
    <!-- <button :disabled="password != confirmPassword">Register</button> -->
    <button>Register</button>
  </form>
  </main>
</template>
