<script setup lang="ts">

import { ref, toRefs, reactive, computed } from 'vue';
import config from "@/config";
import {showToast, Toast} from "@/ts/toasts";

// const props = defineProps({
//   rideId: {
//     type: Number,
//     required: true
//   },
// })

const userId = localStorage.getItem("userID")

// const { rideId } = toRefs(props)
const rideFetched = ref(false)
const rideID = ref('')

const numberOfPassengers = ref()
const dateVal = ref()
const price = ref()
const description = ref('')
const animalsAllowed = ref(true)
const smokingAllowed = ref(false)

const fromLocation = ref('')
const toLocation = ref('')

const addresses = ref([])

fetch(`${config.apiBaseUrl}/addresses`).then(res=>res.json()).then(data=>addresses.value=data).then(data=>console.log(addresses.value)).catch(err=>console.log(err))


function getDetails() {
  fetch(`${config.apiBaseUrl}/rides/${rideID.value}`)
    .then(res=>res.json())
    .then(data=>{
      if (data.driverId != userId) {
        // console.log("here")
        throw "Driver not owner"
      }
      return data
    })
    .then(data=>{
      fromLocation.value = data.startId,
      toLocation.value = data.destId,
      rideFetched.value = true
      console.log(data)
      numberOfPassengers.value = data.passengerLimit
      dateVal.value = data.startTimestamp
      // price.value = data
      description.value = data.description
      animalsAllowed.value = data.petTransportAllowed
      smokingAllowed.value = data.smokingAllowed
    })
    .catch(err=>showToast(new Toast("Driver cannot edit this ride", "Please select a different ride")))
}

function updateData() {
  fetch(`${config.apiBaseUrl}/rides/${rideID.value}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      // startId: fromLocation.value,
      // destId: toLocation.value,
      // driverId: 0,
      // passengerLimit: numberOfPassengers.value,
      // startTimestamp: +new Date(dateVal.value), // gives unix timestamp of date and time
      // description: description.value

      startId: fromLocation.value,
      destId: toLocation.value,

      passengerLimit: numberOfPassengers.value,
      // startTimestamp: +new Date(dateVal.value), // gives unix timestamp of date and time
      description: description.value,
      smokingAllowed: smokingAllowed.value,
      petTransportAllowed: animalsAllowed.value
     })
    }).then(res=>console.log(res))
    // .then(res=>location.reload())
}

</script>


<template>
  <div>
    <h2>Edit rides</h2>
    <div v-if="!rideFetched">
      <input v-model="rideID" type="number" placeholder="Enter ride ID">
      <button @click="getDetails">Search</button>
    </div>
    <div v-else>
      <select v-model="fromLocation" name="fromAddress">
          <option disabled value="">Select a location</option>
          <option v-for="address in addresses" :value="address.id">
          {{ address.city }}, {{ address.district }}
          </option>
        </select>
        <select v-model="toLocation" name="toAddress">
        <option disabled value="">Select a location</option>
          <option v-for="address in addresses" :value="address.id">
          {{ address.city }}, {{ address.district }}
          </option>
        </select>
      <label>Number of passengers: <input type="number" v-model="numberOfPassengers"></label>
      <label>Date: <input type="number" v-model="dateVal"></label>
      <label>Price: <input type="number" v-model="price"></label>
      <br>
      <label>Description: <input type="textbox" v-model="description"></label>
      <label>Animals allowed: <input type="checkbox" v-model="animalsAllowed"></label>
      <label>Smoking allowed: <input type="checkbox" v-model="smokingAllowed"></label>
      <br>
      <button @click="updateData">Update</button>
    </div>
  </div>
  
</template>