<script setup lang="ts">

import { ref, toRefs } from 'vue';
import config from "@/config";

const props = defineProps({
  from_location: {
    type: String,
    required: true
  },
  to_location: {
    type: String,
    required: true
  },
  date: {
    type: Date,
    required: true
  }
})

const addresses = ref([])

fetch(`${config.apiBaseUrl}/addresses`).then(res=>res.json()).then(data=>addresses.value=data).then(data=>console.log(addresses.value)).catch(err=>console.log(err))

const { from_location, to_location, date } = toRefs(props)

const rides = ref([])

function filterRides(ridesArray) {
  console.log("RUNNING FILTER")
  var filteredRides = []
  for (let ride of ridesArray) {
    if (ride.startId == from_location && ride.destId == to_location) {
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
    <h3>{{ from_location }} to {{ to_location }}</h3>
    <li v-for="ride in rides">
      <h2>{{ addresses.find((e)=>e.id==ride.startId).city }}->{{ addresses.find((e)=>e.id==ride.destId).city }}</h2>
      <h4>Driver ID: {{ ride.driverId }}, Passenger Limit: {{ ride.passengerLimit }}</h4>
    </li>
  </div>
</template>
