<script setup lang="ts">

import AddRide from '../components/AddRide.vue';
import DriverListRides from '../components/DriverListRides.vue';

import { ref } from 'vue'

const addRideShowing = ref(false)

</script>

<template>
    <h1>Driver View</h1>
    <DriverListRides driverId="0" />
    <button v-if="!addRideShowing" @click="addRideShowing=!addRideShowing">Add new ride</button>
    <button v-if="addRideShowing" @click="addRideShowing=!addRideShowing">Hide</button>
    <AddRide v-if="addRideShowing" />
</template> 
