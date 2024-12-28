// 学习资料
<template>
    <div>
        <br><br>
        <el-card>
            <!-- 搜索 -->
            <el-input placeholder="请输入关键字" v-model="queryinfo.query" style="margin-top: 2%;">
                <el-button slot="append" icon="el-icon-search" @click="getinfo"></el-button>
            </el-input>
            <el-table :data="info" stripe style="width: 100%; margin-top: 5%" height="450">
                <el-table-column prop="source" label="资源" width="550" align="center">
                </el-table-column>
                <el-table-column prop="user" label="贡献者" width="100" align="center"></el-table-column>
            </el-table>
            <br>
            <div style="text-align:right">
                <el-button type="primary" @click="dialogFormVisible = true"><i class="el-icon-upload el-icon--right"></i></el-button>
                <!-- 上传内容 -->
                <el-dialog :visible.sync="dialogFormVisible" width="30%" :modal="false">
                    <el-form :model="sourceinfo">
                        <el-input v-model="sourceinfo.source" :rows="5" type="textarea" autocomplete="off"></el-input>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button type="primary" @click="giveinfo()">确 定</el-button>
                    </div>
                </el-dialog>
            </div>
        </el-card>
    </div>
</template>

<script>
import jwtDecode from 'jwt-decode'
export default {
  data () {
    return {
      queryinfo: {
        query: ''
      },
      info: [],
      sourceinfo: {
        source: '',
        user: ''
      },
      dialogFormVisible: false
    }
  },
  created () {
    this.getinfo()
  },
  methods: {
    async getinfo () {
      const { data: res } = await this.$http.post('study/study5/source', this.queryinfo)
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.info = res.data
    },
    async giveinfo () {
      const tokenStr = window.sessionStorage.getItem('token')
      const code = jwtDecode(tokenStr)
      this.sourceinfo.user = code.username
      const { data: res } = await this.$http.post('study/study5/givesource', this.sourceinfo)
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.getinfo()
      this.dialogFormVisible = false
    }
  }
}
</script>

<style scoped>
.el-card {
    width: 90%;
    position: relative;
    left: 5%;
    margin-top: 1%;
}

.el-pagination {
    margin-top: 4%;
    margin-left: 25%;
}
</style>
