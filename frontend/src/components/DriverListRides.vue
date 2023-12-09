<script setup lang="ts">

import { ref, toRefs } from 'vue'
import AvailableRidesList from './AvailableRidesList.vue'
import config from "@/config";

const props = defineProps({
  driverId: {
    type: Number,
    required: true
  },
})

const { driverId } = toRefs(props)
const rides = ref([])

function filterRides(ridesArray) {
  var filteredRides = []
  for (let ride of ridesArray) {
    if (ride.driverId == driverId.value) {
      filteredRides.push(ride)
    }
  }
  console.log(filteredRides)
  return filteredRides
}

fetch(`${config.apiBaseUrl}/rides`)
      .then(res=>res.json()).then(data=>rides.value=filterRides(data)).then(data=>console.log(data)).catch(err=>console.log(err))
</script>

<template>
  <div class="availableCarRides">
    <h3>Displaying rides for driver with ID: {{ driverId }}</h3>
    <li v-for="ride in rides">
      <h4>Driver ID: {{ ride.driverId }}, Passenger Limit: {{ ride.passengerLimit }}</h4>
    </li>
  </div>
</template>

 
