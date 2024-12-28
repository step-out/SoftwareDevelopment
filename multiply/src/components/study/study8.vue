// 问卷服务
<template>
    <div>
        <br><br>
        <el-button type="primary" @click="dialogFormVisible = true" style="margin-left:50px">发布问卷</el-button>
        <!-- 发布内容 -->
        <el-dialog :visible.sync="dialogFormVisible" width="30%" :modal="false">
            <el-form :model="questionnaireinfo">
                <el-input v-model="questionnaireinfo.main" :rows="5" type="textarea" autocomplete="off"></el-input>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="giveinfo()">发 布</el-button>
            </div>
        </el-dialog>
        <br><br>
        <el-card v-for="item in info" :key="item.id">
            <div style="font-size:20px">{{ item.main }}</div>
            <div style="text-align:right">{{ item.user }}</div>
        </el-card>
        <!-- 分页 -->
        <el-pagination @current-change="handleCurrentChange"
        :current-page="queryinfo.pagenum" :page-size="queryinfo.pagesize"
        layout="total, prev, pager, next, jumper" :total="total">
        </el-pagination>
    </div>
</template>

<script>
import jwtDecode from 'jwt-decode'
export default {
  data () {
    return {
      queryinfo: {
        // 当前的页数
        pagenum: 1,
        pagesize: 4
      },
      info: [],
      total: '',
      dialogFormVisible: false,
      questionnaireinfo: {
        main: '',
        user: ''
      }
    }
  },
  created () {
    this.getinfo()
  },
  methods: {
    async getinfo () {
      const { data: res } = await this.$http.post('study/study8/questionnaire', this.queryinfo)
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.info = res.data
      this.total = res.totalpage
    },
    // 监听页码值改变的事件
    handleCurrentChange (newPage) {
      this.queryinfo.pagenum = newPage
      this.getinfo()
    },
    // 发布
    async giveinfo () {
      const tokenStr = window.sessionStorage.getItem('token')
      const code = jwtDecode(tokenStr)
      this.questionnaireinfo.user = code.username
      const { data: res } = await this.$http.post('study/study8/givequestionnaire', this.questionnaireinfo)
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.dialogFormVisible = false
      this.getinfo()
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
    margin-left: 35%;
}
</style>
