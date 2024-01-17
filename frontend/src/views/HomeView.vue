<script setup lang="ts">
import {Button} from "agnostic-vue";
import {showToast, Toast} from "@/ts/toasts";

import config from "@/config";
import { useRouter } from "vue-router"
import { ref } from 'vue';

let name = "";

const router = useRouter()
const users = ref([])

function submit() {
  showToast(new Toast("Welcome!", `Hello ${name}! Nice to meet you!`));
}

function debugFillUsers() {
  const firstNames = [
    'James',
    'John',
    'Robert',
    'Michael',
    'William',
    'David',
    'Richard',
    'Charles',
    'Joseph',
    'Thomas',
    'Heather',
    'Teresa',
    'Doris',
    'Gloria',
    'Evelyn',
    'Jean',
    'Cheryl',
    'Mildred',
    'Katherine'
  ]
  const lastNames = [
    'Smith',
    'Johnson',
    'Williams',
    'Jones',
    'Brown',
    'Davis',
    'Miller',
    'Wilson',
    'Moore',
    'Taylor',
  ]

  for (let i = 0; i < 50; i++) {
    let email = (Math.random()*1e32).toString(36) + "@test.de";
    fetch(`${config.apiBaseUrl}/users`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        firstName: firstNames[Math.floor(Math.random() * firstNames.length)],
        lastName: lastNames[Math.floor(Math.random() * lastNames.length)],
        email: email, // temporary
        password: "DEBUG",
        description: "DEBUG",
        carDescription: "DEBUG",
      })
    })
        .then(res=>{
            if (res.status != 201) {throw "Registration failed"}
          })
        .catch(err=>console.log("Debug error: " + err))
  }
}

function debugFillRides() {
  const addresses = [
    {
      city: "München",
      district: "Flughafen",
      postcode: "12345"
    },
    {
      city: "Berlin",
      district: "Tegel",
      postcode: "12345"
    },
    {
      city: "München",
      district: "Hauptbahnhof",
      postcode: "12345"
    },
    {
      city: "Wien",
      district: "Hauptbahnhof",
      postcode: "12345"
    },
    {
      city: "Zürich",
      district: "Hauptbahnhof",
      postcode: "12345"
    },
    {
      city: "Berlin",
      district: "Alexanderplatz",
      postcode: "12345"
    },
    {
      city: "Stuttgart",
      district: "Friedhof",
      postcode: "12345"
    },
    {
      city: "Frankfurt am Main",
      district: "Fernbahnhof",
      postcode: "12345"
    },
    {
      city: "Köln",
      district: "Messe",
      postcode: "12345"
    },
    {
      city: "Düsseldorf",
      district: "Hauptbahnhof",
      postcode: "12345"
    },
    {
      city: "Stuttgart",
      district: "Untertürkheim",
      postcode: "12345"
    },
    {
      city: "Amsterdam",
      district: "Centraal",
      postcode: "12345"
    }
  ]

  fetch(`${config.apiBaseUrl}/users`)
        .then(res=>res.json())
        .then(data=>users.value=data)
        .then(()=>console.log("Fetched users."))
        .then(()=>{
          debuggerFillRidesHelper(addresses)
        })
        .catch(err=>console.log("Error fetching users: " + err))

  // for (let i = 0; i < 50; i++) {
  //   let start = addresses[Math.floor(Math.random() * addresses.length)]
  //   let dst = addresses[Math.floor(Math.random() * addresses.length)]
  //   let user = users.value[Math.floor(Math.random() * users.value.length)]
  //   fetch(`${config.apiBaseUrl}/rides/new`, {
  //     method: "POST",
  //     headers: { "Content-Type": "application/json" },
  //     body: JSON.stringify({
  //       startCity: start.city,
  //       startPostCode: start.postcode,
  //       startDistrict: start.district,
  //       dstCity: dst.city,
  //       dstPostCode: dst.postcode,
  //       dstDistrict: dst.district,
  //       driverId: user.id,
  //       passengerLimit: Math.floor(Math.random() * 4),
  //       startTimestamp: +new Date(Math.floor(Date.now() / 1000 + Math.random() * 1000000)), // gives unix timestamp of date and time
  //       description: "Lorem ipsum dolor sit amet",
  //       smokingAllowed: Math.random() < 0.5 ? false : true,
  //       petTransportAllowed: Math.random() < 0.5 ? false : true,
  //       price: Math.round((Math.random() * 20.0 + 3.5) * 100.0) / 100.0,
  //      })
  //     })
  //     .then(res=>console.log(res))
  //     .catch(err=>console.log("DEBUG CREATE RIDE ERROR: " + err))
  // }

  console.log("Generated random rides.")
}

// here to fix async issue
function debuggerFillRidesHelper(addresses: Array<Object>) {
  for (let i = 0; i < 50; i++) {
    let start = addresses[Math.floor(Math.random() * addresses.length)]
    let dst = addresses[Math.floor(Math.random() * addresses.length)]
    let user = users.value[Math.floor(Math.random() * users.value.length)]
    fetch(`${config.apiBaseUrl}/rides/new`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        startCity: start.city,
        startPostCode: start.postcode,
        startDistrict: start.district,
        dstCity: dst.city,
        dstPostCode: dst.postcode,
        dstDistrict: dst.district,
        driverId: user.id,
        passengerLimit: Math.floor(Math.random() * 4),
        startTimestamp: +new Date(Math.floor(Date.now() / 1000 + Math.random() * 1000000)), // gives unix timestamp of date and time
        description: "Lorem ipsum dolor sit amet",
        smokingAllowed: Math.random() < 0.5 ? false : true,
        petTransportAllowed: Math.random() < 0.5 ? false : true,
        price: Math.round((Math.random() * 20.0 + 3.5) * 100.0) / 100.0,
       })
      })
      .then(res=>console.log(res))
      .catch(err=>console.log("DEBUG CREATE RIDE ERROR: " + err))
  }
}

</script>
<template>

  <h1>Welcome to e.mundo's ride & share </h1>
  <link rel="stylesheet" href="assets/style.css">
  <div>
      <h2>Pick your identity</h2>

      <!-- Buttons to navigate to driver and rider pages -->
      <router-link to="/driver">
        <button class="button">Driver</button>
      </router-link>

      <router-link to="/rider">
        <button class="button">Rider</button>
      </router-link>
    </div>
    <div>
      <h2>Not yet a member of e.mundo's ride & share ?</h2>
      <h3>Register here</h3>
      <!-- Buttons to navigate to driver and rider pages -->
      <router-link to="/registration">
        <button class="button">Register</button>
      </router-link>
    </div>
    <div>
      <h2>You are a part of the family?</h2>
      <h3>Login here</h3>
      <!-- Buttons to navigate to driver and rider pages -->
      <router-link to="/Login">
        <button class="button">Login</button>
      </router-link>
    
    </div>
    <div>
      <h2>Curious about e.mundo's ride & share ?</h2>
      <h3>Read Our Story</h3>
      <!-- Buttons to navigate to driver and rider pages -->
      <router-link to="/about">
        <button class="button">About Us</button>
      </router-link>
    </div>

    <div>
      <h2>DEBUG ONLY</h2>
      <h2>GENERATE RANDOM USERS AND RIDES</h2>
      <button class="button" @click="debugFillUsers()">GENERATE RANDOM USERS</button>
      <button class="button" @click="debugFillRides()">GENERATE RANDOM RIDES</button>
    </div>
    

 </template>
 <style scoped>

 .dark-mode {
  --background-color: #333; /* dark mode background color */
  --text-color: #fff;
}
</style>






