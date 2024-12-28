<template>
  <el-container class="home-container">
    <el-header height="100px">
      <div style="margin-left:20px">
        <span>个人中心</span>
      </div>
      <div style="position:relative;left:77%">
        <el-button type="info" @click="shouye">首页</el-button>
        <el-button type="info" @click="logout" style="margin-left:20px">退出</el-button>
      </div>
    </el-header>

    <el-container>
      <el-aside :width="isCollapse?'64px':'250px'">
        <!-- 侧边栏菜单区 -->
        <div class="toggle-button" @click="toggleCollaspe">|||</div>
        <el-menu
        unique-opened
        background-color="#333744"
        text-color="#fff"
        active-text-color="#409eff"
        :collapse="isCollapse"
        :collapse-transition="false"
        :router='true'>
          <!-- 一级菜单 -->
          <el-submenu :index="item.id + ''"
          v-for="item in menulist" :key="item.id">
            <!-- 一级菜单模板区 -->
            <template slot="title">
              <i :class="iconsObj[item.id]"></i>
              <span>{{item.authName}}</span>
            </template>
            <!-- 二级菜单 -->
            <el-menu-item :index="'/' + subItem.path"
            v-for="subItem in item.children" :key="subItem.id">
              <i class="el-icon-menu"></i>
              <span>{{subItem.authName}}</span>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        mian
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data () {
    return {
      menulist: [],
      iconsObj: {
        125: 'el-icon-s-custom',
        103: 'el-icon-lock',
        101: 'el-icon-goods',
        102: 'el-icon-document',
        145: 'el-icon-s-data'
      },
      isCollapse: false
    }
  },
  created () {
    this.getMenuList()
  },
  methods: {
    logout () {
      window.sessionStorage.clear()
      this.$router.push('/login')
    },
    shouye () {
      this.$router.push('/welcome')
    },
    // 获取所有菜单数据
    async getMenuList () {
      const { data: res } = await this.$http.get('menus')
      if (res.meta.status !== 200) return this.$message.console.error(res.meta.msg)
      this.menulist = res.data
      console.log(res)
    },
    // 点击按钮切换菜单折叠与展开
    toggleCollaspe () {
      this.isCollapse = !this.isCollapse
    }
  }
}
</script>

<style scoped>
.home-container {
  height: 100%;
  width: 100%;
}

.el-header {
  background-color: #bcd7e9;
  display: flex;
  align-items: center;
  color: #fff;
  font-size: 40px;
}

.el-aside {
  background-color: rgba(192, 189, 189, 0.5);
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
