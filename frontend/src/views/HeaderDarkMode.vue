<template>
    <div>
      <!-- Other header content -->
  
      <button @click="toggleDarkMode">Toggle Dark Mode</button>
    </div>
  </template>
  
  <script>
  export default {
    methods: {
      toggleDarkMode() {
        // Call the method from the parent component or emit an event
        this.$emit('toggle-dark-mode');
      },
    },
  };
  </script>
  