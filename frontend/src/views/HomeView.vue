<script setup lang="ts">
import {Button} from "agnostic-vue";
import {showToast, Toast} from "@/ts/toasts";

let name = "";

function submit() {
  showToast(new Toast("Welcome!", `Hello ${name}! Nice to meet you!`));
}
</script>

<template>
  <main>
    <h1>Welcome to BlaBlaBlaCar</h1>
  </main>
</template>
