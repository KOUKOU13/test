<script setup lang="ts">

import { ref, toRefs, reactive, computed } from 'vue'


const props = defineProps({
  rideId: {
    type: Number,
    required: true
  },
})

const { rideId } = toRefs(props)
console.log(rideId.value)

</script>


<template>
    <h3>{{ rideId }}</h3>
</template>