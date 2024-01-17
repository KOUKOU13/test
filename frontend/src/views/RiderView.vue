<script setup lang="ts">
import {Button} from "agnostic-vue";
import ViewRides from "../components/ViewRides.vue";
import { ref, watch } from 'vue';
import config from "@/config";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

let name = "";
let loggedIn = localStorage.getItem("userID") ? true : false

const filterOnVal = ref(false)
const showRidesRegisteredFor = ref(true)

const fromLocation = ref('')
const toLocation = ref('')
const dateVal = ref('')
const keyUpdate = ref(0)


const addresses = ref([])
const selectedSortOption = ref('none')

fetch(`${config.apiBaseUrl}/addresses`).then(res=>res.json()).then(data=>addresses.value=data).then(data=>console.log(addresses.value)).catch(err=>console.log(err))



function submit() {
//   showToast(new Toast("!", `Hello ${name}! Nice to meet you!`));
}

watch(selectedSortOption, ()=>keyUpdate.value++)


</script>

<template>
  <main>
      <div class="mx-[10rem]">
        <h1 class="bg-primary-200 text-dark-100 mt-3 py-2 font-black w-full text-center text-3xl subpixel-antialiased rounded-t-3xl">
          Search for a ride
        </h1>
        <div class="py-1.5"></div>
        <div class="flex flex-row">
          <div class="py-[12rem] bg-dark-100 w-6/12 mr-1.5">

          </div>
          <div class="py-[12rem] bg-dark-100 w-6/12 ml-1.5">
          
          </div>
        </div>
        <div class="py-1.5"></div>
        <div id="sortSection">
          <label><FontAwesomeIcon icon="sort" /> Sort by: </label>
          <select v-model="selectedSortOption">
            <option value="none">None</option>
            <option value="price_asc">Price (Low -> High)</option>
            <option value="price_dec">Price (High -> Low)</option>
            <option value="date_asc">Date (Oldest -> Latest)</option>
            <option value="date_dec">Date (Latest -> Oldest)</option>
            <option value="passengers_asc">Passenger Limit (Low -> High)</option>
            <option value="passengers_dec">Passenger Limit (High -> Low)</option>
          </select>
        </div>
        <div class="py-1.5"></div>
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
              <ViewRides :key="keyUpdate" :sortOption="selectedSortOption" />
            </tbody>
          </table>
        </div>

      <!--ViewRides :showRidesUserRegisteredFor="showRidesRegisteredFor" /-->
      <!--h1>Available rides:</h1>
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
      </div-->
      <!--ViewRides :showRidesUserRegisteredFor="!showRidesRegisteredFor" :filterOn="filterOnVal" :key="filterOnVal" :from_location="fromLocation" :to_location="toLocation" /-->
    </div>
  </main>
</template>
