<script setup lang="ts">
import {RouterView} from 'vue-router'
import {Close, Header, HeaderNav, HeaderNavItem, Toast, Toasts} from "agnostic-vue";
import {activeToasts} from "@/ts/toasts";

import 'agnostic-vue/dist/common.min.css';
import "agnostic-vue/dist/index.css";

let loggedIn = localStorage.getItem("userID") ? true : false
console.log(loggedIn)
let userFirstName = localStorage.getItem("userFirstName")

function logoutFunc() {
  localStorage.clear()
  console.log("logging out")
  location.reload()
}

</script>

<template>
  <div id="app">
    <Header isHeaderContentStart>
      <template v-slot:headernav>
        <HeaderNav>
          <HeaderNavItem>
            <RouterLink to="/">Home</RouterLink>
          </HeaderNavItem>
          <HeaderNavItem>
            <RouterLink to="/rider">Rider</RouterLink>
          </HeaderNavItem>
          <HeaderNavItem>
            <RouterLink to="/driver">Driver</RouterLink>
          </HeaderNavItem>
          <HeaderNavItem>
            <RouterLink to="/registration">Register</RouterLink>
          </HeaderNavItem>
          <HeaderNavItem v-if="!loggedIn">
            <RouterLink to="/login">Login</RouterLink>
          </HeaderNavItem>
          <HeaderNavItem v-if="loggedIn">
            <!-- <RouterLink to="/login">Logout</RouterLink> -->
            <h4 to="/" @click="logoutFunc">Logout</h4>
          </HeaderNavItem>
          <HeaderNavItem>
            <RouterLink to="/about">About</RouterLink>
          </HeaderNavItem>
          <!-- <HeaderNavItem>
            <h4>Login?</h4>
          </HeaderNavItem> -->
          <h4 v-if="loggedIn" style="position: absolute;right: 0;padding: 3em;">Welcome {{ userFirstName }}</h4>
        </HeaderNav>
      </template>
    </Header>

    <div class="main">
      <RouterView/>
    </div>
  </div>

</template>

<style scoped>
.main {
  padding: 10px 20px;
}
</style>
