<script setup>

import { ref } from 'vue'
import config from "@/config";

// const fromLocation = ref('')
// const toLocation = ref('')

const fromLocationCity = ref('')
const fromLocationPost = ref(0)
const fromLocationDistrict = ref('')

const toLocationCity = ref('')
const toLocationPost = ref(0)
const toLocationDistrict = ref('')

const numberOfPassengers = ref(2)
const dateVal = ref()
const price = ref(0)
const description = ref('')
const animalsAllowed = ref(true)
const smokingAllowed = ref(false)

const userId = localStorage.getItem("userID")

function addRide() {

  // fetch(`${config.apiBaseUrl}/rides`, {
  //   method: "POST",
  //   headers: { "Content-Type": "application/json" },
  //   body: JSON.stringify({
  //     startId: fromLocation.value,
  //     destId: toLocation.value,
  //     driverId: 0,
  //     passengerLimit: numberOfPassengers.value,
  //     startTimestamp: +new Date(dateVal.value), // gives unix timestamp of date and time
  //     description: description.value
  //    })
  //   }).then(res=>console.log(res))
  //   .then(res=>location.reload())


  fetch(`${config.apiBaseUrl}/rides/new`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      // startId: fromLocation.value,
      // destId: toLocation.value,
      // driverId: 0,
      // passengerLimit: numberOfPassengers.value,
      // startTimestamp: +new Date(dateVal.value), // gives unix timestamp of date and time
      // description: description.value

      startCity: fromLocationCity.value,
      startPostCode: fromLocationPost.value,
      startDistrict: fromLocationDistrict.value,
      dstCity: toLocationCity.value,
      dstPostCode: toLocationPost.value,
      dstDistrict: toLocationDistrict.value,
      driverId: userId,
      passengerLimit: numberOfPassengers.value,
      startTimestamp: Math.floor((+new Date(dateVal.value))/1000), // gives unix timestamp of date and time
      description: description.value,
      smokingAllowed: smokingAllowed.value,
      petTransportAllowed: animalsAllowed.value,
      price: price.value,
     })
    }).then(res=>console.log(res))
    .then(res=>location.reload())
  }
  

</script>


<template>
  <div class="add_ride">
    <form @submit.prevent="addRide">

      <input v-model="fromLocationCity" placeholder="Add departure city">
      <input v-model="fromLocationDistrict" placeholder="Add departure district">
      <input v-model="fromLocationPost" type="number" placeholder="Add departure postal code">

      <br>

      <input v-model="toLocationCity" placeholder="Add arrival city">
      <input v-model="toLocationDistrict" placeholder="Add arrival district">
      <input v-model="toLocationPost" type="number" placeholder="Add arrival postal code">

      <br>

      <!-- <input v-model="toLocation" placeholder="Add arrival location"> -->
      <label>Max number of passengers: <input v-model="numberOfPassengers" type="number"></label>
      <input v-model="dateVal" type="datetime-local" placeholder="What date and time will you depart?">
      <label>Price: <input v-model="price" type="number"></label>
      <input v-model="description" type="textbox" placeholder="Add description">
      <label><input type="checkbox" v-model="smokingAllowed">Smoking allowed?</label>
      <label><input type="checkbox" v-model="animalsAllowed">Animals allowed?</label>
      <button class="button">Add ride</button>

  </form>
  </div>
</template>
