<script setup lang="ts">

import { ref, computed } from 'vue';
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
const passwordsMatch = computed(() => password.value == passwordConfirmation.value)
const carDescription = ref('')
const userDescription = ref('')


function registerUser() {
  if (!passwordsMatch.value) {
    showToast(new Toast("Registration failed", "Ensure all fields are filled in correctly"))
    return false
  }

    fetch(`${config.apiBaseUrl}/users`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        firstName: firstName.value,
        lastName: lastName.value,
        email: email.value.toLowerCase(), // temporary
        password: password.value,
        description: userDescription.value,
        carDescription: carDescription.value,
      })
    })
    .then(res=>{
        if (res.status != 201) {throw "Registration failed"}
      })
    .then(res=>router.push({ path: '/login' }))
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
    <h5 v-if="!passwordsMatch" style="color:red;">Passwords don't match</h5>
    <br>
    <input v-model="userDescription" placeholder="Enter user description">
    <br>
    <label><input type="checkbox" v-model="isDriver">Register as driver?</label>
    <br>
    <div v-if="isDriver">
    <input v-if="isDriver" v-model="carDescription" placeholder="Enter car description">
    </div>
    <br>
    <!-- <button :disabled="password != confirmPassword">Register</button> -->
    <button>Register</button>
  </form>
  </main>
</template>
