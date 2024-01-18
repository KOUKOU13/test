<script setup lang="ts">

import { ref, toRefs, reactive, computed, watch } from 'vue'
import config from "@/config";
import EditRide from './EditRide.vue';
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

import type { Ride } from "../interface/Ride.vue"
import type { UserRide } from "../interface/UserRide.vue"

const userId = localStorage.getItem("userID")
let loggedIn = localStorage.getItem("userID") ? true : false

const props = defineProps({
  ridesShown: {
    type: String,
    required: true
  },
  isDriver: {
    type: Boolean,
    required: true
  }
})

const { ridesShown, isDriver } = toRefs(props)

const emit = defineEmits(["updateRides"])

// console.log(`showRegistered: ${showRidesUserRegisteredFor.value}`)

const rides = ref<Ride[]>([])
// const userrides = ref<UserRide[]>([])
const userapplications = ref<UserRide[]>([])
const addresses = ref([])
const users = ref([])
const modalOpen = ref<boolean>()
const modalRide = ref<Ride>()
const editModalShowing = ref<boolean>()
const editModalRide = ref<Ride>()
// when rides is fetched increment, same with userrides, once this variable==2, we can filter rides
const ridesAndApplicationsFetchedIncrement = ref(0)

function filterRides(ridesArray: Ride[]) {
  console.log("FILTERING")
  const currentTimestamp = Math.floor((+ new Date())/1000)
  if (isDriver.value == true) {
    if (ridesShown.value == "upcoming") {
      return ridesArray.filter((ride: Ride)=>(ride.driverId==parseInt(userId!) && ride.startTimestamp >= currentTimestamp))
    } 
    else if (ridesShown.value == "past") {
      return ridesArray.filter((ride: Ride)=>(ride.driverId==parseInt(userId!) && ride.startTimestamp < currentTimestamp))
    }
    else {
      return ridesArray
    }
  }
  // rider
  else {
    if (ridesShown.value == "upcoming") {
      return ridesArray.filter((ride: Ride)=>{
        for (const application of userapplications.value) {
          if (application.rideId == ride.id && ride.startTimestamp >= currentTimestamp) {
            return true
          }
        }
      })
    }
    else if (ridesShown.value == "past") {
      return ridesArray.filter((ride: Ride)=>{
        for (const application of userapplications.value) {
          if (application.rideId == ride.id && ride.startTimestamp < currentTimestamp) {
            return true
          }
        }
      })
    }
    else if (ridesShown.value == "past") {
      return ridesArray.filter((ride: Ride)=>(ride.driverId==parseInt(userId!) && ride.startTimestamp < currentTimestamp))
    }
    else {
      return []
    }
    // check all userapplications
    // console.log("checking userapplications")
    // console.log(userapplications.value)
    // for (let userRi of userapplications.value) {
    //   console.log(userRi)
    // }
  }
  
}

function deleteRide(rideId: number) {
  fetch(`${config.apiBaseUrl}/rides/${rideId}`, { method: 'DELETE' })
    .then(res=>{
      console.log(res)
      return res.json()
    })
    .then(data=>console.log(data))
    // .then(data=>location.reload())
    .then(data=>{
      emit("updateRides")
    })
    // .then(data => fetchAllTodos())
    .catch(err => console.log(err))
}

function getUserCountForRide(rideId : number) {
  let count = 0
  for (let entry of userapplications.value) {
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
  for (let entry of userapplications.value) {
    if (entry.rideId == rideId) {
      if (entry.userId == parseInt(userId!)) {
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
      .then(data=>{
        rides.value=data
        ridesAndApplicationsFetchedIncrement.value++
        return rides.value
      })
      // .then(data=>console.log("Rides: " + JSON.stringify(data)))
      .catch(err=>console.log(err))

fetch(`${config.apiBaseUrl}/userapplications`)
      .then((res) => {
        console.log(res)
        return res.json()
      })
      .then((data) => {
        console.log("usserrides data thing")
        console.log(data)
        userapplications.value = data
        ridesAndApplicationsFetchedIncrement.value++
        return data
      })
      .catch(err=>console.log(err))

fetch(`${config.apiBaseUrl}/addresses`)
      .then(res=>res.json())
      .then(data=>addresses.value=data)
      // .then(data=>console.log("Addresses: " + JSON.stringify(data)))
      .catch(err=>console.log("Error fetching addresses: " + err))
      
fetch(`${config.apiBaseUrl}/users`)
      .then(res=>res.json())
      .then(data=>users.value=data)
      // .then(data=>console.log("Users: " + JSON.stringify(data)))
      .catch(err=>console.log("Error fetching users: " + err))

watch(ridesAndApplicationsFetchedIncrement, () => {
  if (ridesAndApplicationsFetchedIncrement.value == 2) {
    console.log("READY TO FILTER")
    console.log(ridesAndApplicationsFetchedIncrement.value)
    rides.value = filterRides(rides.value)
  }
})

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
          <th class="table-header"></th>
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

          <td class="text-center"><FontAwesomeIcon :class="{'iconEnabled': ride.smokingAllowed, 'iconDisabled': !ride.smokingAllowed}" icon="smoking" /></td>
          <td class="text-center"><FontAwesomeIcon :class="{'iconEnabled': ride.petTransportAllowed, 'iconDisabled': !ride.petTransportAllowed}" icon="dog" /></td>

          <td class="text-center">
            <button class="button-no-bg text-center w-full"
              @click="modalOpen=true; modalRide=ride;">
              Additional Info
            </button> </td>
          <td class="text-center">

            <button v-if="isDriver==true && ridesShown=='upcoming'" class="button text-center w-full" @click="editModalShowing=true; editModalRide=ride;">
              Edit
            </button>
            <div v-if="isDriver==true && ridesShown=='past'">

            </div>

            <div v-if="isDriver==false && ridesShown=='upcoming'"></div>

            <div v-if="isDriver==false && ridesShown=='past'" class="stars">
              <td class="text-center"><FontAwesomeIcon :icon="['fas', 'star']" /></td>
              <td class="text-center"><FontAwesomeIcon :icon="['fas', 'star']" /></td>
              <td class="text-center"><FontAwesomeIcon :icon="['fas', 'star']" /></td>
              <td class="text-center"><FontAwesomeIcon :icon="['fas', 'star']" /></td>
              <td class="text-center"><FontAwesomeIcon :icon="['fas', 'star']" /></td>
            </div>              

          </td>
          <td class="text-center">
            <button class="button text-center" @click="deleteRide(ride.id)">Cancel</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <teleport to="body">
      <div class="modal" v-if="modalOpen" @click="modalOpen=false">
          <div @click.stop="">
            <h1 class="font-bold w-full text-center">Description</h1>
            {{ modalRide?.description }}
            {{ modalRide?.smokingAllowed ? "foo" : "bar" }}
            <button class="button text-center w-full"
              @click="modalOpen=false;">
              close
            </button>
          </div>
      </div>
  </teleport>

  <teleport to="body">
      <div class="modal" v-if="editModalShowing" @click="editModalShowing=false">
          <div @click.stop="">
            <EditRide :rideId="editModalRide!.id" @closeModal="editModalShowing=false" />
            <!-- <h1 class="font-bold w-full text-center">Edit Ride</h1>
            <label>Description: <input type="text"></label>
            <br>
            <label>more field, need to figure out how to v-model data <input type="text"></label>
            <h1 class="font-bold w-full">{{ modalRide?.description }}</h1>
            <h1 class="font-bold w-full text-center">{{ modalRide?.isSmokingAllowed ? "foo" : "bar" }}</h1>
             -->
            <button class="button text-center w-full"
              @click="editModalShowing=false;">
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

.iconEnabled {
  color: white;
}

.iconDisabled {
  color: rgb(69, 69, 69);
}

</style>
