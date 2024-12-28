<template>
    <el-row class="home_container">
        <el-col class="left" :span="5">
            <div>
            <!-- 个人信息 -->
            <div class="info" @click="user">
                <el-avatar icon="el-icon-user-solid" :size="170"></el-avatar>
            </div>

            <!-- 侧边栏菜单区 -->
            <div class="menu">
                <el-menu unique-opened background-color="#EFF5FB" :default-active="activePath"
                text-color="#000000" active-text-color="#409eff" :router='true'>
                    <!-- 一级菜单 -->
                    <el-submenu>
                    <!-- 一级菜单模板区 -->
                        <template slot="title">
                            <i :class="iconsObj[menulist[0].id]"></i>
                            <span>{{menulist[0].serve_name}}</span>
                        </template>
                        <!-- 二级菜单 -->
                        <el-menu-item :index="'/' + subItem.study_path" v-for="subItem in menulist[0].children"
                        :key="subItem.id" @click="saveNavState('/' + subItem.study_path)">
                            <i class="el-icon-menu"></i>
                            <span>{{subItem.serve_name}}</span>
                        </el-menu-item>
                    </el-submenu>
                    <el-menu-item :index="'/' + menulist[o].study_path" v-for="o in 3" :key="o">
                        <i :class="iconsObj[menulist[o].id]"></i>
                        <span>{{menulist[o].serve_name}}</span>
                    </el-menu-item>
                </el-menu>
            </div>

            <div class="other">
                <div>
                    <i class="el-icon-right"></i>
                    <el-button type="text" class="lbutton" @click="life">生活服务</el-button>
                </div>
                <div>
                    <i class="el-icon-right"></i>
                    <el-button type="text" class="lbutton" @click="work">就业服务</el-button>
                </div>
                <div style="padding-left:45%">
                    <el-button type="text"  @click="logout">退出</el-button>
                </div>
            </div>
        </div>
        </el-col>
        <el-col :span="13" class="main">
            <!-- 路由占位符 -->
            <router-view></router-view>
        </el-col>
    </el-row>
</template>

<script>
export default {
  data () {
    return {
      menulist: [],
      iconsObj: {
        1: 'el-icon-s-management',
        2: 'el-icon-s-promotion',
        3: 'el-icon-s-flag',
        4: 'el-icon-document'
      },
      // 被激活的动态地址
      activePath: ''
    }
  },
  created () {
    this.getMenuList()
    this.activePath = window.sessionStorage.getItem('activePath')
  },
  methods: {
    logout () {
      window.sessionStorage.clear()
      this.$router.push('/login')
    },
    // 获取所有菜单数据
    async getMenuList () {
      const { data: res } = await this.$http.get('study/getserve')
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.menulist = res.data
    },
    life () {
      this.$router.push('/life')
    },
    work () {
      this.$router.push('/work')
    },
    // 保存链接的激活状态
    saveNavState (activePath) {
      window.sessionStorage.setItem('activePath', activePath)
      this.activePath = activePath
    },
    user () {
      this.$router.push('/usercenter')
    }
  }
}
</script>

<style scoped>
.home_container {
    height: 100%;
    width: 100%;
}

.left {
    height: 65%;
    background-color: rgba(212, 209, 209, 0.5);
    position: relative;
    left: 5%;
    top: 5%;
    border-radius: 25px;
}

.info {
    border-radius: 60px;
    width: 170px;
    height: 170px;
    position: fixed;
    top:10%;
    left: 9%;
    padding-left: 8px;
}

.menu {
    position: fixed;
    top: 37%;
    left: 8%;
    width: 15%;
}

.other {
    float: left;
    padding-left: 33%;
    padding-top: 190%;
}

.lbutton {
    font-size: 20px;
    color: rgb(14, 13, 13);
}

.main {
    height: 90%;
    background-color: rgba(255, 255, 255, 0.5);
    position: absolute;
    left:35%;
    top:5%;
    border-radius: 25px;
}
</style>
