<template>
  <el-container class='info-container'>
    <!-- 个人信息 -->
    <el-card shadow="hover" class="box-card">
      <div slot="header" class="clearfix">
        <span>个人信息</span>
        <el-button style="float: right; padding: 3px 0; margin-right: 5px" type="text"
          @click="editInfo()">修改</el-button>
      </div>
      <el-form ref="form" :model="myInfo" label-position="left" label-width="80px">
        <el-form-item label="用户名">
          <span>{{myInfo.username}}</span>
        </el-form-item>
        <el-form-item label="密码">
          <span>{{myInfo.password}}</span>
        </el-form-item>
        <el-form-item label="电话号码">
          <span>{{myInfo.phone}}</span>
        </el-form-item>
      </el-form>
    </el-card>
    <!-- 修改个人信息的模态款 -->
    <el-dialog title="修改个人信息" :visible.sync="dialogVisible" width="40%" :modal="false">
      <el-form :model="editInfoForm">
        <!-- 用户名 -->
        <el-form-item prop="username">
          <el-input
            v-model="myEditInfo.username"
            placeholder="用户名"
            prefix-icon="el-icon-user">
          </el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input
            type="password"
            v-model="myEditInfo.password"
            placeholder="密码"
            prefix-icon="el-icon-lock"
            :show-password="true">
          </el-input>
        </el-form-item>
        <!-- 手机号 -->
        <el-form-item prop="phone">
          <el-input
            v-model="myEditInfo.phone"
            placeholder="手机号"
            prefix-icon="el-icon-phone">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="saveEditInfo">保存</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
    </el-dialog>
  </el-container>
</template>

<script>
export default {
  data () {
    return {
      myInfo: {
        username: 'yzlmhzz',
        password: '198251',
        phone: '17720881775'
      }, // 个人信息
      myEditInfo: {}, // 用于存储正在修改的记录数据
      user: this.$route.params.user
    }
  },
  created () {
    this.getData()
  },
  methods: {
    async getData () {
      const { data: res } = await this.$http.get('user', {
        params: {
          user: this.user
        }
      }) // 根据user获取个人信息
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.myInfo = res.data // {username:, password:, phone:}
    },
    editInfo () {
      // 打开修改个人信息模态框，并将当前个人信息填充到表单中
      this.dialogVisible = true
      this.myEditInfo = { ...this.myInfo }
    },
    saveEditInfo () {
      // 保存修改操作，向后端发送修改后的数据
      this.myInfo = { ...this.myEditInfo }
      this.SaveEditInfo()
      this.dialogVisible = false
    },
    async SaveEditInfo () {
      const { data: res } = await this.$http.put('user', {
        params: {
          username: this.myInfo.name,
          password: this.myInfo.password,
          phone: this.myInfo.phone
        }
      }) // 更新个人信息
      if (res.status !== 200) return this.$message.console.error(res.message)
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
