<template>
  <el-container class="home-container">
    <el-header class="home-header" height="10vh">
      <el-col :span="16">
        <div style="margin-left:20px">
          <span>个人中心</span>
        </div>
      </el-col>
      <el-col :span="8">
        <el-button type="info" style="float: right" @click="logout">退出</el-button>
        <el-button type="info" style="float: right; margin-right:20px" @click="shouye">首页</el-button>
      </el-col>
    </el-header>
    <el-container>
      <el-aside :width="isCollapse ? '64px' : '250px'">
        <!-- 侧边栏菜单区 -->
        <div class="toggle-button" @click="toggleCollaspe">|||</div>
        <el-menu unique-opened background-color="#333744" text-color="#fff" active-text-color="#409eff"
          :collapse="isCollapse" :collapse-transition="false" :router='true'>
          <!-- 一级菜单 -->
          <el-submenu :index="item.id + ''" v-for="item in menulist" :key="item.id">
            <!-- 一级菜单模板区 -->
            <template slot="title">
              <i :class="iconsObj[item.id]"></i>
              <span>{{ item.authName }}</span>
            </template>
            <!-- 二级菜单 -->
            <el-menu-item :index="'/' + subItem.path + '/' + $route.params.user + '/' + subItem.id" v-for="subItem in item.children" :key="subItem.id">
              <i class="el-icon-menu"></i>
              <span>{{ subItem.authName }}</span>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view :key="$route.fullPath"></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data () {
    return {
      menulist: [
        {
          id: 125,
          authName: '个人信息',
          children: [
            { id: 1, authName: '主页', path: 'userInfo' }
          ]
        },
        {
          id: 101,
          authName: '交易记录',
          children: [
            { id: 3, authName: '二手交易', path: 'default' },
            { id: 4, authName: '跑腿代办', path: 'default' }
          ]
        },
        {
          id: 102,
          authName: '发帖记录',
          children: [
            { id: 1, authName: '二手交易', path: 'userPosted' },
            { id: 2, authName: '跑腿代办', path: 'userPosted' }
          ]
        },
        {
          id: 145,
          authName: '统计信息',
          children: [
            { id: 5, authName: '二手交易', path: 'default' },
            { id: 6, authName: '跑腿代办', path: 'default' }
          ]
        },
        {
          id: 103,
          authName: '设置中心',
          children: [
            { id: 7, authName: '账号管理', path: 'default' },
            { id: 8, authName: '通用设置', path: 'default' },
            { id: 9, authName: '帮助与反馈', path: 'default' }
          ]
        }
      ],
      iconsObj: {
        125: 'el-icon-s-custom',
        103: 'el-icon-lock',
        101: 'el-icon-goods',
        102: 'el-icon-document',
        145: 'el-icon-s-data'
      },
      isCollapse: false,
      user: ''
    }
  },
  created () {
    this.getMenuList()
    this.user = this.$route.params.user
  },
  methods: {
    logout () {
      window.sessionStorage.clear()
      this.$router.push('/login')
    },
    shouye () {
      this.$router.push('/welcome/' + this.user)
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
  height: 100vh;
  width: 100%;
  display: flex;
}

.home-header {
  background-color: #bcd7e9;
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
