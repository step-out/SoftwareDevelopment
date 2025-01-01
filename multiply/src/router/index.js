import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/login.vue'
import Welcome from '../components/welcome.vue'
import Life from '../components/life/home.vue'
import Life1 from '../components/life/life1.vue'
import Life2 from '../components/life/life2.vue'
import Life3 from '../components/life/life3.vue'
import UserCenter from '../components/usercenter/home.vue'
import UserInfo from '../components/usercenter/userInfo.vue'
import UserSecond from '../components/usercenter/postedSecond.vue'
import UserHelp from '../components/usercenter/postedHelp.vue'
import Manage from '../components/manager/manage.vue'
import Canteen from '../components/manager/canteen-management.vue'
import Secondhand from '../components/manager/secondhand-management.vue'
import UserManage from '../components/manager/user-manage.vue'

Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    { path: '/', redirect: 'login' },
    { path: '/login', component: Login },
    {
      path: '/welcome',
      component: Welcome,
      children: [
        { path: '/welcome', component: Life },
        { path: '/life1', component: Life1 },
        { path: '/life2', component: Life2 },
        { path: '/life3', component: Life3 }
      ]
    },
    {
      path: '/usercenter',
      component: UserCenter,
      children: [
        { path: '/userinfo', component: UserInfo },
        { path: '/postedsecond', component: UserSecond },
        { path: '/postedhelp', component: UserHelp }
      ]
    },
    // 新增路由
    {
      path: '/manage',
      component: Manage
    },
    {
      path: '/canteen',
      component: Canteen
    },
    {
      path: '/secondhand',
      component: Secondhand
    },
    {
      path: '/usermanage',
      component: UserManage
    }
  ]
})

// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
  // to 将要访问的路径
  // from 从哪个路径跳转
  // next 函数，表示放行
  if (to.path === '/login') return next()
  const tokenStr = window.sessionStorage.getItem('token')
  if (!tokenStr) return next('/login')
  next()
})

export default router
