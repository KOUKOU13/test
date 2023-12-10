<script setup>

import { ref } from 'vue'
import config from "@/config";

const fromLocation = ref('')
const toLocation = ref('')
const numberOfPassengers = ref(2)
const dateVal = ref()
const price = ref(0)
const description = ref('')
const animalsAllowed = ref(true)
const smokingAllowed = ref(false)

function addRide() {

  fetch(`${config.apiBaseUrl}/rides`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      startId: fromLocation.value,
      destId: toLocation.value,
      driverId: 0,
      passengerLimit: numberOfPassengers.value,
      startTimestamp: +new Date(dateVal.value), // gives unix timestamp of date and time
      description: description.value
     })
    }).then(res=>console.log(res))
    .then(res=>location.reload())
  }
  

</script>


<template>
  <div class="add_ride">
    <form @submit.prevent="addRide">

      <input v-model="fromLocation" placeholder="Add departure location">
      <input v-model="toLocation" placeholder="Add arrival location">
      <label>Max number of passengers: <input v-model="numberOfPassengers" type="number"></label>
      <input v-model="dateVal" type="datetime-local" placeholder="What date and time will you depart?">
      <label>Price: <input v-model="price" type="number"></label>
      <input v-model="description" type="textbox" placeholder="Add description">
      <label><input type="checkbox" v-model="smokingAllowed">Smoking allowed?</label>
      <label><input type="checkbox" v-model="animalsAllowed">Animals allowed?</label>
      <button>Add ride</button>

  </form>
  </div>
</template>
