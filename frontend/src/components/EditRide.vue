<script setup lang="ts">

import { ref, toRefs, reactive, computed } from 'vue';
import config from "@/config";
import {showToast, Toast} from "@/ts/toasts";

const props = defineProps({
  rideId: {
    type: Number,
    required: true
  },
})

const emit = defineEmits(["closeModal"])

const { rideId } = toRefs(props)

const userId = localStorage.getItem("userID")

const numberOfPassengers = ref()
const dateVal = ref()
const price = ref()
const description = ref('')
const animalsAllowed = ref(true)
const smokingAllowed = ref(false)

const fromLocation = ref('')
const toLocation = ref('')

const addresses = ref([])

fetch(`${config.apiBaseUrl}/addresses`).then(res=>res.json())
.then(data=>addresses.value=data)
.then(data=>console.log(addresses.value))
.catch(err=>console.log(err))


function getDetails() {
  fetch(`${config.apiBaseUrl}/rides/${rideId.value}`)
    .then(res=>res.json())
    .then(data=>{
      if (data.driverId != userId) {
        throw "Driver not owner"
      }
      return data
    })
    .then(ride=>{
      fromLocation.value = ride.startId
      toLocation.value = ride.destId
      numberOfPassengers.value = ride.passengerLimit
      dateVal.value = ride.startTimestamp
      price.value = ride.price
      description.value = ride.description
      animalsAllowed.value = ride.petTransportAllowed
      smokingAllowed.value = ride.smokingAllowed
    })
    .catch(err=>showToast(new Toast("Driver cannot edit this ride", "Please select a different ride")))
}

function updateData() {
  fetch(`${config.apiBaseUrl}/rides/${rideId.value}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      startId: fromLocation.value,
      destId: toLocation.value,
      driverId: userId,
      passengerLimit: numberOfPassengers.value,
      price: price.value,
      startTimestamp: Math.floor((+new Date(dateVal.value))/1000), // gives unix timestamp of date and time
      description: description.value,
      smokingAllowed: smokingAllowed.value,
      petTransportAllowed: animalsAllowed.value
     })
    })
    .then(res=>{
        console.log(res)
        emit("closeModal")
    })
    .catch(err=>console.log(err))
}

getDetails()

</script>


<template>
  <div>
    <h2>Edit rides</h2>
    <div>
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
      <input v-model="dateVal" type="datetime-local" placeholder="What date and time will you depart?">
      <br>
      <label>Description: <input type="textbox" v-model="description"></label>
      <label>Animals allowed: <input type="checkbox" v-model="animalsAllowed"></label>
      <label>Smoking allowed: <input type="checkbox" v-model="smokingAllowed"></label>
      <br>
      <button class="button text-center w-6/12" @click="updateData">Update</button>
    </div>
  </div>
  
</template>