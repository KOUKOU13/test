<script setup lang="ts">

import { ref } from 'vue'
import AvailableRidesList from './AvailableRidesList.vue'
import config from "@/config";

const fromLocation = ref('')
const toLocation = ref('')
const dateVal = ref('')
const keyUpdate = ref(0)


const addresses = ref([])

fetch(`${config.apiBaseUrl}/addresses`).then(res=>res.json()).then(data=>addresses.value=data).then(data=>console.log(addresses.value)).catch(err=>console.log(err))

function updateRides() {
  // force update but changing key val
  keyUpdate.value += 1
}

</script>

<template>
  <div class="view_rides">
    <form @submit.prevent="updateRides">
    <select v-model="fromLocation" name="fromAddress">
      <option v-for="address in addresses" :value="address.id">
      {{ address.city }}
      </option>
    </select>
    <select v-model="toLocation" name="toAddress">
      <option v-for="address in addresses" :value="address.id">
      {{ address.city }}
      </option>
    </select>
    <button>Update rides</button>
    <AvailableRidesList :key="keyUpdate" :from_location="fromLocation" :to_location="toLocation" date="2023/03/03" />
  </form>
  </div>
</template>

