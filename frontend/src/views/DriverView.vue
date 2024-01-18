<script setup lang="ts">

import AddRide from '../components/AddRide.vue';
// import DriverListRides from '../components/DriverListRides.vue';
import DriverViewRides from '@/components/DriverViewRides.vue';

import { ref, watch } from 'vue'

const addRideShowing = ref(false)
const selectedTab = ref("upcoming")
const updateViewRidesKey = ref(0)

watch(selectedTab, ()=>updateViewRidesKey.value++)

let loggedIn = localStorage.getItem("userID") ? true : false
const userId = localStorage.getItem("userID")

</script>

<template>
    <main>
        <h1>Ride Management</h1>
        <div v-if="loggedIn">
            <!-- <DriverListRides :driverId="userId" /> -->
            <button v-if="!addRideShowing" @click="addRideShowing=!addRideShowing" class="button">Add new ride</button>
            <button v-if="addRideShowing" @click="addRideShowing=!addRideShowing" class="button">Hide</button>
            <div class="w-full">
                <nav class="border-b text-sm flex justify-center">
                    <a class="inline-block px-4 py-2 text-gray-700 hover:text-black" :class="{'border-b-2 border-indigo-600 text-indigo-600 font-semibold': selectedTab=='upcoming'}" @click="selectedTab='upcoming'" href="#">Upcoming rides</a>
                    <a class="inline-block px-4 py-2" :class="{'border-b-2 border-indigo-600 text-indigo-600 font-semibold': selectedTab=='past'}" @click="selectedTab='past'" href="#">Past rides</a>
                </nav>
            </div>
            <AddRide v-if="addRideShowing" />
        </div>
        <div v-else>
            <h3>You are not logged in</h3>
        </div>
        <DriverViewRides :key="updateViewRidesKey" :ridesShown="selectedTab" @updateRides="updateViewRidesKey++" />
    </main>
</template>
