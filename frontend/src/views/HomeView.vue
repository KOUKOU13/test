<script setup lang="ts">
import {Button} from "agnostic-vue";
import {showToast, Toast} from "@/ts/toasts";

let name = "";

function submit() {
  showToast(new Toast("Welcome!", `Hello ${name}! Nice to meet you!`));
}

</script>
<template>
  <h1>Welcome to e-mundo's ride & share </h1>
  <h2> test </h2>
  <link rel="stylesheet" href="assets/style.css">


 </template>






