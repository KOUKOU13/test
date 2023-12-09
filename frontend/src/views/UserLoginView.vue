<script setup lang="ts">
import { ref } from 'vue'
import config from '@/config'
import {showToast, Toast} from "@/ts/toasts";

// const username = ref('')
// const password = ref('')
// const isDriver = ref(false)
// const licensePlateNum = ref('')
let loggedIn = localStorage.getItem("userID") ? true : false
const email = ref('')

// localStorage.setItem("user", "testing")

function loginUser() {

  fetch(`${config.apiBaseUrl}/users/login/${email.value}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
     })
  })
  .then(res=>res.json())
  .then(data=>{
    if (!data.id) {
      throw "Login failed"
    }
    localStorage.setItem("userID", data.id)
    localStorage.setItem("userFirstName", data.firstName)
    localStorage.setItem("userLastName", data.lastName)    
    localStorage.setItem("userEmail", data.email)
  })
  .then(data=>console.log(data))
  .then(data=>location.reload())
  .catch(err=>showToast(new Toast("Login failed", "Please change username or password"))
)
}
</script>
<template>
  <div class="login">
    <div class="not_logged_in" v-if="!loggedIn">
      <h1>Login</h1>
      <form @submit.prevent="loginUser">
        <input v-model="email" type="text" placeholder="Enter email (apparently no password)" />
        <button>Log in</button>
      </form>
    </div>
    <div class="logged_in" v-if="loggedIn">
      <h1>You are logged in</h1>
    </div>
  </div>
</template>
