<script setup lang="ts">

import { ref } from 'vue'
import AvailableRidesList from './AvailableRidesList.vue'

defineProps({
})

const fromLocation = ref('')
const toLocation = ref('')
const dateVal = ref('')
const keyUpdate = ref(0)

function updateRides() {
  // force update but changing key val
  keyUpdate.value += 1
}


</script>

<template>
  <div class="view_rides">
    <form @submit.prevent="updateRides">
    <input v-model="fromLocation">
    <input v-model="toLocation">
    <input v-model="dateVal">
    <button>Update rides</button>
    <AvailableRidesList :key="keyUpdate" :from_location="fromLocation" :to_location="toLocation" date="2023/03/03" />
  </form>
  </div>
</template>

