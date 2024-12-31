<template>
    <div class="whole">
        <div class="total">
            <div class="smallbox" v-if="isLogin">
                <div class="text1">
                    欢迎来到<br>校园二手市场
                </div>
                <div class="text2">
                    创建一个新账户吧
                </div>
                <div>
                    <el-button round @click="change"
                    style="background-color: transparent" class="sbutton">注册</el-button>
                </div>
            </div>
            <div class="smallbox" v-else>
                <div class="text1">
                    你好，朋友
                </div>
                <div class="text2">
                    欢迎回来
                </div>
                <div>
                    <el-button round @click="change"
                    style="background-color: transparent" class="sbutton">登录</el-button>
                </div>
            </div>

            <div class="bigbox" v-if="isLogin">
                <div class="sign">LOGIN</div>
                <el-form ref="loginFormRef" :rules="loginFormRules" :model="loginForm" class="login_form">
                    <!-- 用户名 -->
                    <el-form-item prop="username">
                        <el-input
                            v-model="loginForm.username"
                            placeholder="用户名"
                            prefix-icon="el-icon-user">
                        </el-input>
                    </el-form-item>
                    <!-- 密码 -->
                    <el-form-item prop="password">
                        <el-input
                            type="password"
                            v-model="loginForm.password"
                            placeholder="密码"
                            prefix-icon="el-icon-lock">
                        </el-input>
                    </el-form-item>
                    <el-form-item class="btns">
                        <el-button type="primary" @click="login">登录（用户）</el-button>
                        <el-button type="info" @click="reset">重置</el-button>
                        <el-button type="info" @click="login_admin">登录（管理员）</el-button>
                    </el-form-item>
                </el-form>
            </div>
            <div class="bigbox" v-else>
                <div class="sign">SIGN UP</div>
                <el-form ref="reguserFormRef" :rules="reguserFormRules" :model="reguserForm" class="reguser_form">
                    <!-- 用户名 -->
                    <el-form-item prop="username">
                        <el-input
                            v-model="reguserForm.username"
                            placeholder="用户名"
                            prefix-icon="el-icon-user">
                        </el-input>
                    </el-form-item>
                    <!-- 密码 -->
                    <el-form-item prop="password">
                        <el-input
                            type="password"
                            v-model="reguserForm.password"
                            placeholder="密码"
                            prefix-icon="el-icon-lock">
                        </el-input>
                    </el-form-item>
                    <!-- 手机号 -->
                    <el-form-item prop="phone">
                        <el-input
                            v-model="reguserForm.phone"
                            placeholder="手机号"
                            prefix-icon="el-icon-phone">
                        </el-input>
                        </el-form-item>
                    <el-form-item class="btns">
                        <el-button type="primary" @click="reguser">注册</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
export default {
  data () {
    return {
      isLogin: true,
      // 登录表单的数据绑定对象
      loginForm: {
        username: '',
        password: ''
      },
      // 表单验证规则
      loginFormRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 1, max: 8, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        ]
      },
      // 注册表单的数据绑定对象
      reguserForm: {
        username: '',
        password: '',
        phone: ''
      },
      // 表单验证规则
      reguserFormRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { pattern: /^1[3|4|5|7|8][0-9]{9}$/, message: '请输入正确的手机号', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    change () {
      this.isLogin = !this.isLogin
    },
    login () {
      // 表单的数据进行验证，函数返回值为true则进行登录请求
      this.$refs.loginFormRef.validate(async valid => {
        // if (!valid) return
        // // 请求返回数据中的data重命名为res，通过res中的status判断是否登录成功
        // const res = await this.$http.post('login', this.loginForm)
        // if (res.data.status !== 200) return this.$message.error(res.data.message)
        // this.$message.success('登录成功！')
        // window.sessionStorage.setItem('token', res.data.token)
        this.$router.push('/welcome')
      })
    },
    reguser () {
      // 表单的数据进行验证，函数返回值为true则进行注册请求
      this.$refs.reguserFormRef.validate(async valid => {
        if (!valid) return
        // 请求返回数据中的data重命名为res，通过res中的status判断是否注册成功
        const res = await this.$http.post('reguser', this.reguserForm)
        if (res.data.status !== 200) return this.$message.error(res.data.message)
        this.$message.success('注册成功！')
        const result = await this.$http.post('login', this.reguserForm)
        if (result.data.status !== 200) return this.$message.error(res.data.message)
        window.sessionStorage.setItem('token', result.data.token)
        this.$router.push('/welcome')
      })
    },
    // 重置表单
    reset () {
      this.$refs.loginFormRef.resetFields()
    },
    login_admin () {
      this.$router.push('/manage')
    }
  }
}
</script>

<style scoped>
.whole {
    background-image: url(./b-g.png);
    height: 100%;
    width: 100%;
}

.total {
    height: 400px;
    width: 780px;
    position: absolute;
    top :50%;
    left: 50%;
    transform: translate(-50%,-50%);
    box-shadow: 0 10px 15px 0 rgba(27, 26, 26, 0.1);
    border-radius: 25px;
}

.smallbox {
    width: 30%;
    height: 100%;
    background: linear-gradient(135deg,rgb(236, 240, 241), 65%, rgb(21, 128, 170));
    border-radius: 0 25px 25px 0;
    position: absolute;
    top:0;
    left:70%;
}

.text1 {
    font-size: 30px;
    position: relative;
    text-align: center;
    top:30%;
    color: #fff;
}

.text2 {
    font-size: 15px;
    position: relative;
    text-align: center;
    top:34%;
}

.sbutton {
    border: 1px solid #fff;
    position: absolute;
    top: 62%;
    left: 34%;
}

.bigbox{
    width: 70%;
    height: 100%;
    background-color: whitesmoke;
    border-radius: 25px 0 0 25px;
    position: absolute;
    top: 0;
    left: 0;
}

.sign {
  text-align: center;
  font-size: 35px;
  position: relative;
  top: 13%;
  color: rgb(202, 228, 236);
}

.btns{
    display: flex;
    justify-content: center;
}

.login_form {
    position: relative;
    top:48%;
    left: 50%;
    transform: translate(-50%,-50%);
    width: 75%;
}

.reguser_form {
    position: relative;
    top:48%;
    left: 50%;
    transform: translate(-50%,-50%);
    width: 75%;
}
</style>
