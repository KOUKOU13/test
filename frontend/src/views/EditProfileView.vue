<script setup lang="ts">

  import { ref, computed } from 'vue';

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

  const passwordsMatch = computed(() => newPassword.value == oldPassword.value)

  function updatePassword() {
    console.log("running update password")
    if (passwordsMatch.value) {
      // update passwords
    }
  }

  function updateDetails() {
  //   const requestOptions = {
  //     method: 'PUT',
  //     headers: { 'Content-Type': 'application/json' },
  //     body: JSON.stringify({ title: 'Fetch PUT Request Example' })
  // };
  // fetch(`${config}/api/v1/users/${userID}`, requestOptions)
  //     .then(response => response.json())
  //     .then(data => element.innerHTML = data.updatedAt );
    }

</script>

<template>
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

</template>