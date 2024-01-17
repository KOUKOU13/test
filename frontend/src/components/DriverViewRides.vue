<script setup lang="ts">

import { ref, toRefs, reactive, computed } from 'vue'
import config from "@/config";

import type { Ride } from "../interface/Ride.vue"
import type { UserRide } from "../interface/UserRide.vue"

const userId = localStorage.getItem("userID")
let loggedIn = localStorage.getItem("userID") ? true : false

const props = defineProps({
  ridesShown: {
    type: String,
    required: true
  },
})

const { ridesShown } = toRefs(props)

// console.log(`showRegistered: ${showRidesUserRegisteredFor.value}`)

const rides = ref<Ride[]>([])
const userrides = ref<UserRide[]>([])
const addresses = ref([])
const users = ref([])
const modalOpen = ref<boolean>()
const modalRide = ref<Ride>()
const selectedTab = ref("upcoming")

function filterRides(ridesArray: Ride[]) {
  const currentTimestamp = Math.floor((+ new Date())/1000)
  if (ridesShown.value == "upcoming") {
    return ridesArray.filter((ride: Ride)=>(ride.driverId==parseInt(userId!) && ride.startTimestamp >= currentTimestamp))
  } 
  else if (ridesShown.value == "past") {
    return ridesArray.filter((ride: Ride)=>(ride.driverId==parseInt(userId!) && ride.startTimestamp < currentTimestamp))
  }
  else {
    return ridesArray
  }
  
  // if (filterOn.value) {
  //   console.log("RUNNING FILTER")
  //   console.log(from_location)
  //   var filteredRides = []
  //   for (let ride of ridesArray) {
  //     console.log("Printing ride")
  //     console.log(ride)
  //     if (ride.startId == from_location.value && ride.destId == to_location.value) {
  //       filteredRides.push(ride)
  //     }
  //   }
  //   console.log(filteredRides)
  //   return filteredRides
  // }
  // else {
  //   return ridesArray
  // }
}

function getUserCountForRide(rideId : number) {
  let count = 0
  for (let entry of userrides.value) {
    if (entry.rideId == rideId) {
      count++
    }
  }
  return count
}

function getAddressStringFromId(addressId : number) {
  let count = 0
  for (let entry of addresses.value) {
    if (entry.id == addressId) {
      return entry.city + ", " + entry.district
    }
  }
  return "Unknown"
}

function getUserStringFromId(userId : number) {
  let count = 0
  for (let entry of users.value) {
    if (entry.id == userId) {
      return entry.firstName + " " + entry.lastName
    }
  }
  return "Unknown"
}

function isUserRegisteredForRide(rideId : number) {
  for (let entry of userrides.value) {
    if (entry.rideId == rideId) {
      if (entry.userId == userId) {
        return true
      }
    }
  }
  return false
}

function getDateFromUnixTimestamp(timestamp : number) {
  var date = new Date(timestamp * 1000)
  return date.toUTCString()
}

fetch(`${config.apiBaseUrl}/rides`)
      .then(res=>res.json())
      .then(data=>rides.value=filterRides(data))
      .then(data=>console.log("Rides: " + JSON.stringify(data)))
      .catch(err=>console.log(err))

fetch(`${config.apiBaseUrl}/userrides`)
      .then((res) => {
        console.log("userrides thing res")
        console.log(res)
        return res.json()
      })
      .then((data) => {
        console.log("usserrides data thing")
        console.log(data)
        userrides.value = data
        return data
      })
      .catch(err=>console.log(err))

fetch(`${config.apiBaseUrl}/addresses`)
      .then(res=>res.json())
      .then(data=>addresses.value=data)
      .then(data=>console.log("Addresses: " + JSON.stringify(data)))
      .catch(err=>console.log("Error fetching addresses: " + err))
      
fetch(`${config.apiBaseUrl}/users`)
      .then(res=>res.json())
      .then(data=>users.value=data)
      .then(data=>console.log("Users: " + JSON.stringify(data)))
      .catch(err=>console.log("Error fetching users: " + err))

</script>

<template>

  <div class="overflow-auto">
    <table fixed-header='true' class="w-full">
      <thead class="bg-dark-400">
        <tr>
          <th class="table-header">Start</th>
          <th class="table-header">Destination</th>
          <th class="table-header">Driver</th>
          <th class="table-header">Passengers</th>
          <th class="table-header">Date</th>
          <th class="table-header">Price</th>
          <th class="table-header"></th>
          <th class="table-header"></th>
        </tr>
      </thead>
      <tbody class="divide-y divide-dark-400">
        <tr v-for="ride in rides" class="odd:bg-dark-100 even:bg-dark-200 hover:bg-dark-600 hover:text-dark-100">
          <td class="text-center"> {{ getAddressStringFromId(ride.startId) }} </td>
          <td class="text-center"> {{ getAddressStringFromId(ride.destId) }} </td>
          <td class="text-center"> {{ getUserStringFromId(ride.driverId) }} </td>
          <td class="text-center"> {{ getUserCountForRide(ride.id) }} / {{ ride.passengerLimit }} </td>
          <td class="text-center"> {{ getDateFromUnixTimestamp(ride.startTimestamp) }} </td>
          <td class="text-center"> {{ ride.price }}€ </td>
          <td class="text-center">
            <button class="button-no-bg text-center w-full"
              @click="modalOpen=true; modalRide=ride;">
              Additional Info
            </button> </td>
          <td class="text-center">
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <teleport to="body">
      <div class="modal" v-if="modalOpen">
          <div>
            <h1 class="font-bold w-full text-center">Description</h1>
            {{ modalRide?.description }}
            {{ modalRide?.isSmokingAllowed ? "foo" : "bar" }}
            <button class="button text-center w-full"
              @click="modalOpen=false;">
              close
            </button>
          </div>
      </div>
  </teleport>
</template>

<style>
.root {
  @apply relative;
}

.modal {
  @apply fixed top-0 left-0 bg-black-alpha-900 w-screen h-screen flex justify-center align-middle items-center backdrop-blur-sm;
}

.modal > div {
  @apply bg-dark-200 p-4 border-2 rounded-3xl w-[720px] h-[480px];
}

.text-center {
  color: white;
}

</style>
