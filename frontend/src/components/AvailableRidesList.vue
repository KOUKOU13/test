<script setup>

import { ref, toRefs } from 'vue'

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

const { from_location, to_location, date } = toRefs(props)

const rides = ref([])

fetch("http://localhost:5000/getAvailableRides?" + new URLSearchParams({
          "from_location": from_location.value,
          "to_location": to_location.value,
          "date": date.value
        }))
      .then(res=>res.json()).then(data=>rides.value=data["rides"]).then(data=>console.log(data)).catch(err=>console.log(err))
</script>

<template>
  <div class="availableCarRides">
    <h3>{{ from_location }} to {{ to_location }}</h3>
    <li v-for="ride in rides">
      <h2>{{ ride.from }}->{{ ride.to }}</h2>
      <h4>{{ ride.date }}</h4>
    </li>
  </div>
</template>
