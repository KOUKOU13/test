<script setup lang="ts">

import { ref, toRefs } from 'vue'
import config from "@/config";

const userId = localStorage.getItem("userID")

const props = defineProps({
  filterOn: {
    type: Boolean,
    required: true
  },
  from_location: {
    type: String,
    required: false
  },
  to_location: {
    type: String,
    required: false
  },
  date: {
    type: Date,
    required: false
  }
})

const { filterOn, from_location, to_location, date } = toRefs(props)

console.log(filterOn.value)

const rides = ref([])

function filterRides(ridesArray) {
  if (filterOn.value) {
    console.log("RUNNING FILTER")
    console.log(from_location)
    var filteredRides = []
    for (let ride of ridesArray) {
      console.log("Printing ride")
      console.log(ride)
      if (ride.startId == from_location.value && ride.destId == to_location.value) {
        filteredRides.push(ride)
      }
    }
    console.log(filteredRides)
    return filteredRides
  }
  else {
    return ridesArray
  }
}

function registerUserForRide(rideId) {
  console.log(`register for ${rideId}`)
  fetch(`${config.apiBaseUrl}/userrides`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      userId: userId,
      rideId: rideId,
     })
    }).then(res=>console.log(res))
}

async function getUserCountForRide(rideId) {
  const response = await fetch(`${config.apiBaseUrl}/userrides/ride/${rideId}`)
  const json = await response.json()
  console.log("fooooo " + Object.keys(json).length)
  return Object.keys(json).length;
}

fetch(`${config.apiBaseUrl}/rides`)
      .then(res=>res.json()).then(data=>rides.value=filterRides(data)).then(data=>console.log(data)).catch(err=>console.log(err))

</script>

<!-- 
this.startId = startId;
this.destId = destId;
this.driverId = driverId;
this.passengerLimit = passengerLimit;
this.startTimestamp = startTimestamp;
this.isSmokingAllowed = isSmokingAllowed;
this.isPetTransportAllowed = isPetTransportAllowed;
this.description = description; -->

<template>
  <div>
    <h3>Viewing rides (filter: {{ filterOn }})</h3>
    <h5>From: {{ from_location }}</h5>
    <li v-for="ride in rides">
      <h4>Driver ID: {{ ride.driverId }}, Passenger Limit: {{ ride.passengerLimit }}</h4>
      <h4>{{ ride.startId }}->{{ ride.destId }}</h4>
      <h5>Driver: {{ ride.driverId }}, Passengers: {{ getUserCountForRide(ride.id).then( value => { return value } ) }}/{{ ride.passengerLimit }}</h5>
      <h5>Start datetime: {{ ride.startTimestamp }}, Smoke symbol{{ ride.isSmokingAllowed }}, pet symbol{{ ride.isPetTransportAllowed }}</h5>
      <h5>Description {{ ride.description }}</h5>
      <button @click="()=>registerUserForRide(ride.id)" :disabled="userId === 0">Register for this ride</button>
    </li>
  </div>
</template>
