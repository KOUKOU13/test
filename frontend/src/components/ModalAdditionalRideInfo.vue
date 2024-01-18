<script setup lang="ts">
import { toRefs } from 'vue'

const props = defineProps({
    rideId: {
        type: Number,
        required: true
    },
})

const { rideId } = toRefs(props)

</script>

<template>
    <div class="modal" aria-modal="true">
        <div @click.stop="">
            <h1 class="font-bold w-full text-center">Description</h1>
            {{ rideId }}
            <button class="button text-center w-full"
                @click="() => $emit('close')">
                close
            </button>
       </div>
    </div>
</template>
