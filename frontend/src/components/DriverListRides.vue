<script setup lang="ts">

import { ref, toRefs } from 'vue'
import AvailableRidesList from './AvailableRidesList.vue'
import config from "@/config";
import { useRouter } from "vue-router"


const props = defineProps({
  driverId: {
    type: Number,
    required: true
  },
})

const router = useRouter()

const { driverId } = toRefs(props)
const rides = ref([])

function updateRides() {
  fetch(`${config.apiBaseUrl}/rides`)
    .then(res=>res.json()).then(data=>rides.value=filterRides(data)).then(data=>console.log(data)).catch(err=>console.log(err))

}

function goToRideEditView(rideId) {
  console.log(`RIDE ID IS: ${rideId}`)
  router.push({ name: 'rideView', params: {rideId: rideId }})
}

function filterRides(ridesArray) {
  var filteredRides = []
  for (let ride of ridesArray) {
    console.log(`${ride.driverId} compared with ${driverId.value}`)
    if (ride.driverId == driverId.value) {
      filteredRides.push(ride)
    }
  }
  console.log(filteredRides)
  return filteredRides
}

function removeRide(rideId) {
  // fetch(`${config.apiBaseUrl}/rides`)
  //     .then(res=>res.json()).then(data=>rides.value=filterRides(data)).then(data=>console.log(data)).catch(err=>console.log(err))

  fetch(`${config.apiBaseUrl}/rides/${rideId}`, { method: 'DELETE' })
    .then(res => console.log(`${rideId} deleted`))
    .then(res=>location.reload())
  updateRides() // doesn't work at the moment
}

updateRides()
</script>

<template>
  <div v-if="rides.length > 0">
    <h3>Upcoming rides for driver with ID: {{ driverId }}</h3>
    <li v-for="ride in rides">
      <h4>Ride ID: {{ ride.id }}</h4>
      <h4>Driver ID: {{ ride.driverId }}, Passenger Limit: {{ ride.passengerLimit }}</h4>
      <button @click="() => {removeRide(ride.id)}">Remove ride</button>
      <button @click="goToRideEditView(ride.id)">Edit ride details</button>
    </li>
  </div>
  <div v-else>
    <h2>You have no upcoming rides</h2>
  </div>
</template>

 
