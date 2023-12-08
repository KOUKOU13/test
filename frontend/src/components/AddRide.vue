<script setup>

import { ref } from 'vue'
import config from "@/config";

const fromLocation = ref('')
const toLocation = ref('')
const numberOfPassengers = ref(0)
const dateVal = ref()

const addresses = ref([])

fetch(`${config.apiBaseUrl}/addresses`)
.then(res=>res.json())
.then(data=>addresses.value=data)
.then(data=>console.log(addresses.value))
.catch(err=>console.log(err))

function addRide() {
  fetch(`${config.apiBaseUrl}/rides`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      startId: fromLocation.value,
      destId: toLocation.value,
      driverId: 0,
      passengerLimit: numberOfPassengers.value,
      date: +new Date(dateVal.value) // gives unix timestamp of date and time
     })
  }).then(res=>console.log(res))
}

</script>


<template>
  <div class="add_ride">
    <form @submit.prevent="addRide">

    <input v-model="fromLocation" placeholder="Add departure location" />
    <input v-model="toLocation" placeholder="Add arrival location" />
    <input v-model="numberOfPassengers" type="number" placeholder="Add max number of passengers" />
    <input v-model="dateVal" type="datetime-local" placeholder="What date and time will you depart?" />
    <button>Add ride</button>

  </form>
  </div>
</template>
