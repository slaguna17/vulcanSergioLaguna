<template>
  <div id="app">
    <Header />
    <router-view></router-view>
    <Footer />
  </div>
</template>

<script>
import Header from './components/Header.vue';
import Home from './components/Home.vue'
import Footer from './components/Footer.vue';

export default {
  components: {
    Header,
    Footer,
    Home
  }
}
</script>