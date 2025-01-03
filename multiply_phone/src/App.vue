<template>
  <div id="app">
    <!--路由占位符-->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
#app {
  background: url('./components/b-g.png');
  height: 100%;
  position: fixed;
  width:100%;
}
</style>
