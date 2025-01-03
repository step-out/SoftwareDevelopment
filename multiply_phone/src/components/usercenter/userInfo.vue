<template>
  <el-container class='info-container'>
    <!-- 个人信息 -->
    <el-card shadow="hover" class="box-card">
      <div slot="header" class="clearfix">
        <span>个人信息</span>
        <el-button style="float: right; padding: 3px 0; margin-right: 5px" type="text"
          @click="editInfo()">修改</el-button>
      </div>
      <el-form ref="form" :model="info" label-position="left" label-width="80px">
        <el-form-item label="用户名">
          <span>{{info.username}}</span>
        </el-form-item>
        <el-form-item label="密码" type="password">
          <span>{{info.password}}</span>
        </el-form-item>
        <el-form-item label="电话号码">
          <span>{{info.phone}}</span>
        </el-form-item>
      </el-form>
    </el-card>
    <!-- 修改个人信息 -->
    <el-dialog title="修改个人信息" :visible.sync="dialogVisible" width="60%" :modal="false">
      <el-form :model="newinfo" ref="form" :rules="FormRules">
        <!-- 用户名 -->
        <el-form-item prop="username">
          <el-input
            v-model="newinfo.username"
            placeholder="用户名"
            prefix-icon="el-icon-user">
          </el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input
            type="password"
            v-model="newinfo.password"
            placeholder="密码"
            prefix-icon="el-icon-lock"
            :show-password="true">
          </el-input>
        </el-form-item>
        <!-- 手机号 -->
        <el-form-item prop="phone">
          <el-input
            v-model="newinfo.phone"
            placeholder="手机号"
            prefix-icon="el-icon-phone">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="save">保存</el-button>
      </span>
    </el-dialog>
  </el-container>
</template>

<script>
import jwtDecode from 'jwt-decode'
export default {
  data () {
    return {
      // 个人信息
      info: {
        id: '',
        username: '',
        password: '',
        phone: ''
      },
      newinfo: {
        id: '',
        username: '',
        password: '',
        phone: ''
      },
      dialogVisible: false,
      FormRules: {
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
  created () {
    this.getuserinfo()
  },
  methods: {
    async getuserinfo () {
      const tokenStr = window.sessionStorage.getItem('token')
      const code = jwtDecode(tokenStr)
      this.info.username = code.username
      const { data: res } = await this.$http.post('userinfo', this.info)
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.info = res.data[0]
      this.newinfo.id = this.info.id
    },
    editInfo () {
      // 打开修改个人信息模态框，并将当前个人信息填充到表单中
      this.dialogVisible = true
      this.newinfo = { ...this.info }
    },
    async save () {
      this.$refs.form.validate(async valid => {
        if (!valid) return
        const { data: res } = await this.$http.post('edituser', [this.info, this.newinfo]) // 更新个人信息
        if (res.status !== 200) return this.$message.error(res.message)
        this.$message.success('修改成功！')
        this.dialogVisible = false
        this.getuserinfo()
      })
    }
  }
}
</script>

<style scoped>
.info-container {
  height: 100%;
  width: 100%;
}

.box-card {
  width: 100%;
  margin-top: 10px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>
