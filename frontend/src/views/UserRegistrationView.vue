<script setup lang="ts">

import { ref } from 'vue';
import config from "@/config";
import { useRouter } from "vue-router"
import {showToast, Toast} from "@/ts/toasts";

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
  if (password.value != passwordConfirmation.value) {
    showToast(new Toast("Registration failed", "Ensure all fields are filled in correctly"))
    return false
  }

    fetch(`${config.apiBaseUrl}/users`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        firstName: firstName.value,
        lastName: lastName.value,
        email: email.value, // temporary
        password: password.value,
        description: "default description",
        carDescription: "default car description",
      })
    })
    .then(res=>{
        if (res.status != 201) {throw "Registration failed"}
      })
    .then(res=>router.push({ path: '/' }))
    .catch(err=>showToast(new Toast("Registration failed", "Ensure all fields are filled in correctly")))

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
