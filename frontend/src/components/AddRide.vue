<script setup>

import { ref } from 'vue'

const fromLocation = ref('')
const toLocation = ref('')
const dateVal = ref('')

function addRide() {
  fetch(`${config.apiBaseUrl}/addRide`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      fromLocation: fromLocation.value,
      toLocation: toLocation.value,
      dateVal: dateVal.value
     })
  }).then(res=>console.log(res))
}

</script>


<template>
  <div class="add_ride">
    <form @submit.prevent="addRide">
    <input v-model="fromLocation">
    <input v-model="toLocation">
    <input v-model="dateVal">
    <button>Add ride</button>
    <AvailableRidesList :key="keyUpdate" :from_location="fromLocation" :to_location="toLocation" date="2023/03/03" />
  </form>
  </div>
</template>
