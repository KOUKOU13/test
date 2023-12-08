<script setup lang="ts">
import { ref } from 'vue'
import config from '@/config'

const username = ref('')
const password = ref('')
const isDriver = ref(false)
const licensePlateNum = ref('')

function loginUser() {
  let body = {
    username: username.value,
    password: password.value
  }
}
</script>
<template>
  <h1>Login</h1>
  <div class="login">
    <input type="text" v-model="username" placeholder="Ente Username" />
    <input type="text" v-model="password" placeholder="Enter Password" />
    <button v-on:click="loginIn">Login</button>
  </div>
</template>
