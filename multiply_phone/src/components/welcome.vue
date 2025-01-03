<template>
    <div>
        <el-carousel :interval="4000" type="card" height="290px">
            <el-carousel-item v-for="item in images" :key="item">
                <h3 class="medium"><img :src="item.url"></h3>
            </el-carousel-item>
        </el-carousel>
        <div class="content">
            <div>
              <el-menu class="el-menu-demo" mode="horizontal" :router='true' :default-active="activePath">
                <el-menu-item v-for="item in menuList" :index="'/' + item.path"
                :key="item.id" @click="saveNavState('/' + item.path)">
                  <span>{{item.serve_name}}</span>
                </el-menu-item>
                <el-menu-item @click="logout" style="float:right">
                  <span>退出</span>
                </el-menu-item>
                <el-menu-item style="float:right" @click="usercenter">
                  <span>个人中心</span>
                </el-menu-item>
              </el-menu>
            </div>
            <div>
              <router-view></router-view>
            </div>
        </div>
    </div>
</template>

<script>
export default {
  data () {
    return {
      images: [
        { url: require('./1.png') },
        { url: require('./2.jpeg') },
        { url: require('./3.jpeg') }
      ],
      menuList: [{ id: 1, serve_name: '食堂', path: 'life1' },
        { id: 2, serve_name: '二手跑腿', path: 'life2' },
        { id: 3, serve_name: '搭子之旅', path: 'life3' }],
      activePath: '/welcome'
    }
  },
  created () {
    this.activePath = window.sessionStorage.getItem('activePath')
  },
  methods: {
    logout () {
      window.sessionStorage.clear()
      this.$router.push('/login')
    },
    saveNavState (activePath) {
      window.sessionStorage.setItem('activePath', activePath)
      this.activePath = activePath
    },
    usercenter () {
      this.$router.push('/usercenter')
    }
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
}
.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}
.el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
}
</style>
