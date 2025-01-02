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
import Canteen from '../components/manager/canteen-manage.vue'
import Second from '../components/manager/second-manage.vue'
import Help from '../components/manager/help-manage.vue'
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
    {
      path: '/manage',
      component: Manage,
      children: [
        { path: '/canteen', component: Canteen },
        { path: '/second', component: Second },
        { path: '/help', component: Help },
        { path: '/usermanage', component: UserManage }
      ]
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
