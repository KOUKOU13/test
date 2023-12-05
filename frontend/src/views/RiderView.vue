<script setup lang="ts">
import {Button} from "agnostic-vue";
import ViewRides from "../components/ViewRides.vue";

let name = "";

function submit() {
//   showToast(new Toast("!", `Hello ${name}! Nice to meet you!`));
}
</script>

<template>
  <main>
    <h1>Rider View</h1>
    <ViewRides />
    <!-- <label class="mbs2 mbe2" >Type your name:</label>
    <br/>
    <input type="text" class="mbs2 mbe2" v-model="name"/>
    <br/>
    <Button class="mbs2 mbe2" @click="submit">Submit</Button> -->
  </main>
</template>
