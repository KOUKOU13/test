<script setup lang="ts">

import { ref, toRefs, reactive, computed } from 'vue'
import config from "@/config";

const userId = localStorage.getItem("userID")

const props = defineProps({
  filterOn: {
    type: Boolean,
    required: false
  },
  showRidesUserRegisteredFor: {
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
  },
})

const { filterOn, from_location, to_location, date, showRidesUserRegisteredFor } = toRefs(props)

console.log(`showRegistered: ${showRidesUserRegisteredFor.value}`)

const rides = ref([])
const userrides = ref([])

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
    }).then(res=>console.log(`${res} printin`))
}

function deregisterUserFromRide(rideId) {
  console.log(`deregister for ${rideId}`)

  for (let entry of userrides.value) {
    if (entry.rideId == rideId) {
      if (entry.userId == userId) {
        console.log(`ID to delete: ${entry.id}`)
        fetch(`${config.apiBaseUrl}/userrides/${entry.id}`, { method: 'DELETE' })
          .then(res=>{
            console.log(res)
            return res.json()
          })
          .then(data=>console.log(data))
          // .then(res=>location.reload())
      }
    }
  }
}

function getUserCountForRide(rideId) {
  let count = 0
  for (let entry of userrides.value) {
    if (entry.rideId == rideId) {
      count++
    }
  }
  return count
}

function isUserRegisteredForRide(rideId) {
  for (let entry of userrides.value) {
    if (entry.rideId == rideId) {
      if (entry.userId == userId) {
        return true
      }
    }
  }
  return false
}

fetch(`${config.apiBaseUrl}/rides`)
      .then(res=>res.json())
      .then(data=>rides.value=filterRides(data))
      .then(data=>console.log(data))
      .catch(err=>console.log(err))

fetch(`${config.apiBaseUrl}/userrides`)
      .then((res) => {
        console.log("userrides thing res")
        console.log(res)
        return res.json()
      })
      // .then(data=>rides.value=filterRides(data))
      // .then(data=>console.log(data))
      .then((data) => {
        console.log("usserrides data thing")
        console.log(data)
        userrides.value = data
        return data
      })
      .catch(err=>console.log(err))


  const filteredByRegisteredRides = computed(()=>rides.value.filter(ride => showRidesUserRegisteredFor.value == isUserRegisteredForRide(ride.id)))

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
    <li v-for="ride in filteredByRegisteredRides">
      <!-- <div v-if="showRidesUserRegisteredFor == isUserRegisteredForRide(ride.id)"> -->
        <h4>Driver ID: {{ ride.driverId }}, Passenger Limit: {{ ride.passengerLimit }}</h4>
        <h4>{{ ride.startId }}->{{ ride.destId }}</h4>
        <h5>Driver: {{ ride.driverId }}, Passengers: {{ getUserCountForRide(ride.id) }}/{{ ride.passengerLimit }}</h5>
        <h5>Start datetime: {{ ride.startTimestamp }}, Smoke symbol{{ ride.isSmokingAllowed }}, pet symbol{{ ride.isPetTransportAllowed }}</h5>
        <h5>Description {{ ride.description }}</h5>
        <button v-if="!isUserRegisteredForRide(ride.id)" @click="registerUserForRide(ride.id)">Register for this ride</button>
        <button v-else @click="deregisterUserFromRide(ride.id)">Deregister from ride</button>
      <!-- </div> -->
    </li>
  </div>
</template>
