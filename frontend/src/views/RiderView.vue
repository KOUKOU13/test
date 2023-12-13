<script setup lang="ts">
import {Button} from "agnostic-vue";
import ViewRides from "../components/ViewRides.vue";
import { ref } from 'vue';
import config from "@/config";

let name = "";
let loggedIn = localStorage.getItem("userID") ? true : false

const filterOnVal = ref(false)
const showRidesRegisteredFor = ref(true)

const fromLocation = ref('')
const toLocation = ref('')
const dateVal = ref('')
const keyUpdate = ref(0)


const addresses = ref([])

fetch(`${config.apiBaseUrl}/addresses`).then(res=>res.json()).then(data=>addresses.value=data).then(data=>console.log(addresses.value)).catch(err=>console.log(err))



function submit() {
//   showToast(new Toast("!", `Hello ${name}! Nice to meet you!`));
}
</script>

<template>
  <main>
    <!--h3>Your registered rides:</h3-->
    
    <div class="px-20">
      <table fixed-header='true' class="w-full">
        <thead class="bg-dark-400">
          <tr>
            <th class="table-header">Start</th>
            <th class="table-header">Destination</th>
            <th class="table-header">Driver</th>
            <th class="table-header">Passengers</th>
            <th class="table-header">Smoking Allowed</th>
            <th class="table-header">Pets Allowed</th>
            <th class="table-header">Price</th>
            <th class="table-header"></th>
            <th class="table-header"></th>
          </tr>
        </thead>
        <tbody class="divide-y divide-dark-400">
          <ViewRides/>
        </tbody>
      </table>
    </div>

    <!--ViewRides :showRidesUserRegisteredFor="showRidesRegisteredFor" /-->
    <h1>Available rides:</h1>
    <label>Filter rides<input type="checkbox" v-model="filterOnVal"></label>
      <div id="showingFilters" v-if="filterOnVal">
      <form @submit.prevent="updateRides"> 
        <select v-model="fromLocation" name="fromAddress">
          <option disabled value="">Select a location</option>
          <option v-for="address in addresses" :value="address.id">
          {{ address.city }}, {{ address.district }}
          </option>
        </select>
        <select v-model="toLocation" name="toAddress">
        <option disabled value="">Select a location</option>
          <option v-for="address in addresses" :value="address.id">
          {{ address.city }}, {{ address.district }}
          </option>
        </select>
        <button>Update rides</button>
        <AvailableRidesList :key="keyUpdate" :from_location="fromLocation" :to_location="toLocation" date="2023/03/03" />
      </form>
    </div>
    <!--ViewRides :showRidesUserRegisteredFor="!showRidesRegisteredFor" :filterOn="filterOnVal" :key="filterOnVal" :from_location="fromLocation" :to_location="toLocation" /-->
  </main>
</template>
