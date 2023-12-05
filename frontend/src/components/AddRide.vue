<script setup>

import { ref } from 'vue'
import config from "@/config";

const fromLocation = ref('')
const toLocation = ref('')
const dateVal = ref('')

const addresses = ref([])

fetch(`${config.apiBaseUrl}/addresses`).then(res=>res.json()).then(data=>addresses.value=data).then(data=>console.log(addresses.value)).catch(err=>console.log(err))


function addRide() {
  fetch(`${config.apiBaseUrl}/rides`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      fromLocation: fromLocation.value,
      toLocation: toLocation.value,
      driverId: 0, // temporary
      passangerLimit: 3 // also temporary, should add date later
     })
  }).then(res=>console.log(res))
}
// Ride Ride = new Ride(requestBody.getStartId(), requestBody.getDestId(),
//             requestBody.getDriverId(), requestBody.getPassengerLimit())
</script>


<template>
  <div class="add_ride">
    <form @submit.prevent="addRide">
    
    <select v-model="fromLocation" name="fromAddress">
      <option disabled value="">Select a location</option>
      <option v-for="address in addresses" :value="address.id">
      {{ address.city }}
      </option>
    </select>
    <select v-model="toLocation" name="toAddress">
      <option disabled value="">Select a location</option>
      <option v-for="address in addresses" :value="address.id">
      {{ address.city }}
      </option>
    </select>
    <button>Add ride</button>
    <AvailableRidesList :key="keyUpdate" :from_location="fromLocation" :to_location="toLocation" date="2023/03/03" />
  </form>
  </div>
</template>
