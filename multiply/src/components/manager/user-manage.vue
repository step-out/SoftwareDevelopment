<template>
  <div>
    <!-- 用户管理内容 -->
    <el-row style="height:100%; width:100%">
      <el-col :span="24">
        <div class="container">
          <el-card class="box-card" :body-style="{ padding: '10px' }">
            <div class="card-title">用户管理</div>

            <!-- 用户表格 -->
            <el-table :data="users" stripe style="width: 100%" :show-header="true">
              <el-table-column label="用户名" prop="username" width="150" align="center"></el-table-column>
              <el-table-column label="手机号" prop="phone" width="200" align="center">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.phone" size="mini" placeholder="请输入手机号"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="密码" prop="password" width="200" align="center">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.password" size="mini" type="password" placeholder="请输入密码"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="操作" width="200" align="center">
                <template slot-scope="scope">
                  <!-- 修改按钮 -->
                  <el-button type="primary" size="mini" @click="updateUser(scope.row)">修改</el-button>
                  <!-- 删除按钮 -->
                  <el-button type="danger" size="mini" @click="deleteUser(scope.row)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>

            <!-- 添加用户按钮 -->
            <el-button type="success" @click="showAddDialog">添加用户</el-button>
          </el-card>
        </div>
      </el-col>
    </el-row>

    <!-- 返回管理中心按钮 -->
    <el-button class="back-btn" type="primary" @click="goToManage">返回管理中心</el-button>

    <!-- 添加用户对话框 -->
    <el-dialog title="添加新用户" :visible.sync="dialogVisible" width="400px">
      <el-form :model="newUser" ref="form" label-width="100px">
        <el-form-item label="用户名" prop="username" :rules="[{ required: true, message: '请输入用户名', trigger: 'blur' }]">
          <el-input v-model="newUser.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
          <el-input v-model="newUser.password" placeholder="请输入密码" type="password"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone" :rules="[{ required: true, message: '请输入手机号', trigger: 'blur' }]">
          <el-input v-model="newUser.phone" placeholder="请输入手机号"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addUser">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
      users: [], // 用户列表
      dialogVisible: false, // 控制添加用户对话框的显示
      newUser: {
        username: '',
        password: '',
        phone: ''
      }
    }
  },
  created () {
    this.fetchUsers()
  },
  methods: {
    // 获取当前用户列表
    async fetchUsers () {
      try {
        const response = await this.$http.get('/api/users')// 请求用户列表
        if (response.status === 200) {
          this.users = response.data
        }
      } catch (error) {
        this.$message.error('获取用户列表失败')
      }
    },

    // 修改用户信息（密码和手机号）
    async updateUser (user) {
      try {
        const response = await this.$http.post('/api/users/update', user)
        if (response.status === 200) {
          this.$message.success('用户信息修改成功')
        }
      } catch (error) {
        this.$message.error('修改失败')
      }
    },

    // 删除用户
    async deleteUser (user) {
      try {
        const response = await this.$http.post('/api/users/delete', { id: user.id })
        if (response.status === 200) {
          const index = this.users.indexOf(user)
          if (index !== -1) {
            this.users.splice(index, 1)
          }
          this.$message.success('用户删除成功')
        }
      } catch (error) {
        this.$message.error('删除失败')
      }
    },

    // 打开添加用户对话框
    showAddDialog () {
      this.dialogVisible = true
      this.newUser = {
        username: '',
        password: '',
        phone: ''
      }
    },

    // 添加新用户
    async addUser () {
      try {
        const response = await this.$http.post('/api/users/add', this.newUser)
        if (response.status === 200) {
          this.users.push(response.data)
          this.dialogVisible = false
          this.$message.success('添加用户成功')
        }
      } catch (error) {
        this.$message.error('添加失败')
      }
    },

    goToManage () {
      this.$router.push('/manage') // 管理中心页面的路由
    }
  }
}
</script>

<style scoped>
.container {
  margin-top: 20px;
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
