<!-- 管理中心页面 -->
<template>
  <div class="container">
    <div class="buttons-container">
      <!-- 食堂菜品管理按钮 -->
      <el-button type="primary" @click="goToCanteen">食堂菜品管理</el-button>
      <!-- 二手交易/跑腿代办管理按钮 -->
      <el-button type="primary" @click="goToSecondhand">二手交易/跑腿代办管理</el-button>
      <!-- 用户管理-->
      <el-button type="primary" @click="goUserManage">用户管理中心</el-button>
    </div>
    <!-- 返回按钮 -->
    <el-button class="back-button" @click="logout">退出</el-button>
  </div>
</template>
<script>
export default {
  methods: {
    goToCanteen () {
      this.$router.push('/canteen') // 跳转到食堂菜品管理页面
    },
    goToSecondhand () {
      this.$router.push('/secondhand') // 跳转到二手交易/跑腿代办管理页面
    },
    logout () {
      window.sessionStorage.clear()
      this.$router.push('/login')
    },
    goUserManage () {
      this.$router.push('/usermanage') // 跳转到用户管理页面
    }
  }
}
</script>

<style scoped>
.container {
  text-align: center;
  margin-top: 50px;
}
.buttons-container {
  margin-bottom: 30px;
}
.el-button {
  margin: 10px;
}
.back-button {
  position: fixed;
  top: 20px;
  right: 20px;
}
</style>
