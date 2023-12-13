<script setup lang="ts">

import AddRide from '../components/AddRide.vue';
import DriverListRides from '../components/DriverListRides.vue';

import { ref } from 'vue'

const addRideShowing = ref(false)

let loggedIn = localStorage.getItem("userID") ? true : false
const userId = localStorage.getItem("userID")

</script>

<template>
    <div v-if="loggedIn">
        <h1>Driver View</h1>
        <DriverListRides :driverId="userId" />
        <button v-if="!addRideShowing" @click="addRideShowing=!addRideShowing" class="button">Add new ride</button>
        <button v-if="addRideShowing" @click="addRideShowing=!addRideShowing" class="button">Hide</button>
        <AddRide v-if="addRideShowing" />
    </div>
    <div v-else>
        <h3>You are not logged in</h3>
    </div>
</template>
