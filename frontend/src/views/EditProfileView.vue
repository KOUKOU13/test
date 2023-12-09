<script setup lang="ts">

  import { ref, computed } from 'vue';
  import config from "@/config";

  const userID = localStorage.getItem("userID")
  const userfirstName = localStorage.getItem("userFirstName")
  const userlastName = localStorage.getItem("userLastName")    
  const userEmail = localStorage.getItem("userEmail")

  // ref values
  const firstName = ref(userfirstName)
  const lastName = ref(userlastName)  
  const email = ref(userEmail)
  const showChangePassword = ref(false)
  const oldPassword = ref('')
  const newPassword = ref('')
  const newPasswordConfirmation = ref('')

  function reloadPage() {
    location.reload()
  }

  const passwordsMatch = computed(() => newPassword.value == newPasswordConfirmation.value)

  function updatePassword() {
    console.log("running update password")
    if (passwordsMatch.value) {
      const requestOptions = {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        oldPassword: oldPassword.value,
        newPassword: newPassword.value,
      })}

    fetch(`${config.apiBaseUrl}/users/${userID}/changepassword`, requestOptions)
        .then(response => response.json())
        .then(data=>console.log(data))
    }
  }

  function updateDetails() {
    const requestOptions = {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        firstName: firstName.value,
        lastName: lastName.value,
        email: email.value,
        description: "ahhhh",
        password: "" // blank value, handled on backend
      })}

    fetch(`${config.apiBaseUrl}/users/${userID}`, requestOptions)
        .then(response => response.json())
        .then(data=>console.log(data))

        // update localStorage
        // reload page
      
  }

</script>

<template>
  <div>
    <h1>Profile details</h1>
    <form @submit.prevent="updateDetails">
      
      <label>First name: <input v-model="firstName"></label>
      <br>
      <label>Last name: <input v-model="lastName"></label>
      <br>
      <label>Email: <input v-model="email"></label>
      <br>
      <button v-if="!showChangePassword" @click="showChangePassword=true">Change password</button>
      <button @click="reloadPage">Reset</button>
      <div v-if="showChangePassword" id="changePasswordDiv">
        <input v-model="oldPassword" placeholder="Current password">
        <input v-model="newPassword" placeholder="Enter new password">
        <input v-model="newPasswordConfirmation" placeholder="Confirm new password">
        <h5 v-if="!passwordsMatch" style="color:red;">Passwords don't match</h5>
        <button @click="updatePassword">Update password</button>
      </div>
      <button>Update details</button>
    </form>
  </div>
</template>