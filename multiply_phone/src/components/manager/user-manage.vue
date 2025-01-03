 <!-- 用户管理内容 -->
<template>
  <div class="container">
    <el-row height="10%" :gutter="20">
      <el-col :span="17">
        <el-input placeholder='请输入用户名搜索' v-model='searchUsername' clearable @keyup.enter='searchByUsername'>
          <el-button slot='append' icon='el-icon-search' @click='searchByUsername'></el-button>
        </el-input>
        <el-input placeholder='请输入手机号搜索' v-model='searchPhone' clearable @keyup.enter='searchByPhone'>
          <el-button slot='append' icon='el-icon-search' @click='searchByPhone'></el-button>
        </el-input>
      </el-col>
      <el-col :span="2">
        <br>
        <!-- 添加用户按钮 -->
        <el-button type="success" size="small" @click="showAddDialog">添加用户</el-button>
        <!-- 添加用户对话框 -->
        <el-dialog title="添加新用户" :visible.sync="AddDialogVisible" width="60%" :modal="false">
          <el-form :model="newUser" ref="add_form" :rules="FormRules" label-width="70px">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="newUser.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input v-model="newUser.password" placeholder="请输入密码" type="password"></el-input>
            </el-form-item>
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="newUser.phone" placeholder="请输入手机号"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="addUser">确定</el-button>
          </div>
        </el-dialog>
      </el-col>
    </el-row>
    <!-- 用户表格 -->
    <el-table :data="filteredUsers" stripe style="width: 100%" :show-header="true"  height="85%" overflow: auto>
      <el-table-column label="用户名" prop="username" width="70" align="center"></el-table-column>
      <el-table-column label="手机号" prop="phone" width="100" align="center"></el-table-column>
      <el-table-column label="密码" prop="password" width="70" align="center"></el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-dialog title="修改用户信息" :visible.sync="EditDialogVisible" width="60%" :modal="false">
            <el-form :model="newUser" ref="edit_form" :rules="FormRules" label-width="70px">
              <el-form-item label="用户名" prop="username">
                <el-input v-model="newUser.username" placeholder="请输入用户名"></el-input>
              </el-form-item>
              <el-form-item label="密码" prop="password">
                <el-input v-model="newUser.password" placeholder="请输入密码" type="password" :show-password="true"></el-input>
              </el-form-item>
              <el-form-item label="手机号" prop="phone">
                <el-input v-model="newUser.phone" placeholder="请输入手机号"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button type="primary" @click="save()">确定</el-button>
            </div>
          </el-dialog>
          <!-- 修改按钮 -->
          <el-button type="primary" size="mini" @click="editUser(scope.row)">修改</el-button>
          <!-- 删除按钮 -->
          <el-button type="danger" size="mini" @click="deleteUser(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data () {
    return {
      users: [],
      filteredUsers: [],
      EditDialogVisible: false,
      AddDialogVisible: false,
      oldUser: {
        username: '',
        password: '',
        phone: ''
      },
      newUser: {
        username: '',
        password: '',
        phone: ''
      },
      searchUsername: '',
      searchPhone: '',
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
    this.getUsers()
  },
  methods: {
    // 获取当前用户列表
    async getUsers () {
      const { data: res } = await this.$http.get('getusers')
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.users = res.data
      this.filteredUsers = this.users
    },
    searchByUsername () {
      if (this.searchUsername.trim() === '') {
        this.filteredUsers = this.users
      } else {
        this.filteredUsers = this.users.filter((users) => {
          return (
            users.username.includes(this.searchUsername)
          )
        })
      }
    },
    searchByPhone () {
      if (this.searchPhone.trim() === '') {
        this.filteredUsers = this.users
      } else {
        this.filteredUsers = this.users.filter((users) => {
          return (
            users.phone.includes(this.searchPhone)
          )
        })
      }
    },
    editUser (user) {
      this.EditDialogVisible = true
      this.newUser = { ...user }
      this.oldUser = { ...user }
    },
    async save () {
      this.$refs.edit_form.validate(async valid => {
        if (!valid) return
        const { data: res } = await this.$http.post('edituser', [this.oldUser, this.newUser]) // 更新个人信息
        if (res.status !== 200) return this.$message.error(res.message)
        this.$message.success('修改成功！')
        this.EditDialogVisible = false
        this.getUsers()
      })
    },
    async deleteUser (user) {
      const { data: res } = await this.$http.post('deleteuser', user)
      if (res.status !== 200) return this.$message.error(res.message)
      this.$message.success('删除成功！')
      this.getUsers()
    },
    showAddDialog () {
      this.AddDialogVisible = true
      this.newUser = {
        username: '',
        password: '',
        phone: ''
      }
    },
    async addUser () {
      this.$refs.add_form.validate(async valid => {
        if (!valid) return
        const { data: res } = await this.$http.post('reguser', this.newUser)
        if (res.status !== 200) return this.$message.error(res.message)
        this.$message.success('添加成功！')
        this.AddDialogVisible = false
        this.getUsers()
      })
    }
  }
}
</script>

<style scoped>
.container {
  height: 100%;
  width: 100%;
}

.box-card {
  width: 100%;
  background: linear-gradient(180deg, #fff, 30%, rgba(192, 189, 189, 0.5));
  border-radius: 10px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.card-title {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px;
}

.el-table {
  margin-top: 20px;
}

.el-button {
  margin-top: 10px;
}

.dialog-footer {
  text-align: right;
}

.back-btn {
  position: fixed;
  bottom: 20px;
  right: 20px;
  z-index: 999;
}
</style>
