<!-- 管理中心页面 -->
<template>
  <el-container class="home-container">
    <el-header class="home-header" height="13vh">
      <el-col :span="16">
        <div style="margin-left:20px">
          <span>后台管理中心</span>
        </div>
      </el-col>
      <el-col :span="8">
        <el-button type="info" style="float: right" @click="logout">退出</el-button>
      </el-col>
    </el-header>
    <el-container>
      <el-aside width="250px">
        <!-- 侧边栏菜单区 -->
        <el-menu unique-opened background-color="#333744" text-color="#fff" active-text-color="#409eff" :router='true'>
          <el-menu-item index="/usermanage">
            <i class="el-icon-s-custom"></i>
            <span>用户管理</span>
          </el-menu-item>
          <el-menu-item index="/canteen">
            <i class="el-icon-s-shop"></i>
            <span>食堂管理</span>
          </el-menu-item>
          <el-submenu index="/">
            <template slot="title">
              <i class="el-icon-s-management"></i>
              <span>帖子管理</span>
            </template>
              <el-menu-item index="/second">
                <i class="el-icon-menu"></i>
                <span>二手交易</span>
              </el-menu-item>
              <el-menu-item index="/help">
                <i class="el-icon-menu"></i>
                <span>跑腿代办</span>
              </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  methods: {
    logout () {
      window.sessionStorage.clear()
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.home-container {
  height: 100vh;
  width: 100%;
  display: flex;
}

.home-header {
  background-color: #333744;
  display: flex;
  align-items: center;
  color: #fff;
  font-size: 40px;
}

.el-aside {
  height: 90vh;
  background-color: rgba(192, 189, 189, 0.5);
}

.el-main {
  height: 90vh;
}

.toggle-button {
  background-color: #aab4d3;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
</style>
