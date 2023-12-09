<script setup lang="ts">

import { ref } from 'vue';

const friendEmail = ref('')

let body = "Want to save money on your next ride and reduce your carbon footprint? Consider joining eMundo's car sharing service! " +
            "To join simply go to the following link: ..."

</script>

<template>
    <div>
        <h2>Invite a friend</h2>
        <!-- <a href="mailto:email@example.com">Send Email</a> -->
        <input v-model="friendEmail" placeholder="Enter email address">
        <a :href="`mailto:${friendEmail}?subject=Join emundo's carsharing service!&body=${body}`">Send Email</a>
    </div>
</template>