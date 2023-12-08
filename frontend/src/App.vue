<script setup lang="ts">
import {RouterView} from 'vue-router'
import {Close, Header, HeaderNav, HeaderNavItem, Toast, Toasts} from "agnostic-vue";
import {activeToasts} from "@/ts/toasts";

import 'agnostic-vue/dist/common.min.css';
import "agnostic-vue/dist/index.css";
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
          <HeaderNavItem>
            <RouterLink to="/login">Login</RouterLink>
          </HeaderNavItem>
          <HeaderNavItem>
            <RouterLink to="/about">About</RouterLink>
          </HeaderNavItem>
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
