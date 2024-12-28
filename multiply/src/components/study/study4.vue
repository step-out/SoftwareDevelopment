// 答疑服务
<template>
    <div>
        <br><br>
        <el-row :gutter="60">
          <el-col :span="13">
            <!-- 搜索 -->
            <el-input placeholder="请输入关键词" v-model="queryinfo.query" style="margin-left: 10%">
              <el-button slot="append" icon="el-icon-search" @click="findinfo"></el-button>
            </el-input>
          </el-col>
          <el-col :span="6">
            <el-button type="primary" @click="dialogFormVisiblePut = true">发布疑问</el-button>
              <!-- 发表内容 -->
              <el-dialog :visible.sync="dialogFormVisiblePut" width="40%" :modal="false">
                <el-form :model="putinfo">
                  <el-input v-model="putinfo.main" :rows="3" type="textarea" autocomplete="off"></el-input>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button type="primary" @click="giveinfo()">确 定</el-button>
                </div>
              </el-dialog>
          </el-col>
        </el-row>
        <br><br>
        <el-card v-for="item in info" :key="item.id">
            <div style="font-size:20px">{{ item.main }}</div>
            <div style="float:right">{{ item.user }}</div>
            <el-button type="text" style="margin-top:2%" @click="getanswer(item.id)">查看回复</el-button>
            <!-- 查看回复内容 -->
            <el-dialog :visible.sync="dialogVisible" width="40%" :modal="false">
                <el-table :data="answer" stripe height="350" style="width: 100%; font-size:20px" :show-header="false">
                  <el-table-column  prop="main" label="内容" width="400"></el-table-column>
                  <el-table-column  prop="user" label="发布者" width="100"></el-table-column>
                </el-table>
                <span slot="footer">
                    <el-button type="primary" @click="dialogFormVisible = true">回复</el-button>
                    <!-- 回复内容 -->
                    <el-dialog :visible.sync="dialogFormVisible" width="50%" :modal="false">
                        <el-form :model="answerinfo">
                            <el-input v-model="answerinfo.main" :rows="3" type="textarea" autocomplete="off"></el-input>
                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button type="primary" @click="giveanswer()">确 定</el-button>
                        </div>
                    </el-dialog>
                </span>
            </el-dialog>
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
        query: '',
        // 当前的页数
        pagenum: 1,
        pagesize: 4,
        level: ''
      },
      info: [],
      total: '',
      answer: [],
      dialogVisible: false,
      dialogFormVisible: false,
      dialogFormVisiblePut: false,
      answerinfo: {
        main: '',
        user: '',
        level: ''
      },
      putinfo: {
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
      const { data: res } = await this.$http.post('study/study4/problem', this.queryinfo)
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.info = res.data
      this.total = res.totalpage
    },
    async getanswer (id) {
      this.queryinfo.level = id
      this.answerinfo.level = id
      const { data: res } = await this.$http.post('study/study4/getanswer', this.queryinfo)
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.answer = res.data
      this.dialogVisible = true
    },
    // 监听页码值改变的事件
    handleCurrentChange (newPage) {
      this.queryinfo.pagenum = newPage
      this.getinfo()
    },
    // 回复
    async giveanswer () {
      const tokenStr = window.sessionStorage.getItem('token')
      const code = jwtDecode(tokenStr)
      this.answerinfo.user = code.username
      const { data: res } = await this.$http.post('study/study4/giveanswer', this.answerinfo)
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.dialogFormVisible = false
      this.getanswer(this.answerinfo.level)
    },
    // 发表
    async giveinfo () {
      const tokenStr = window.sessionStorage.getItem('token')
      const code = jwtDecode(tokenStr)
      this.putinfo.user = code.username
      const { data: res } = await this.$http.post('study/study4/giveinfo', this.putinfo)
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.dialogFormVisiblePut = false
      this.getinfo()
    },
    // 搜索
    async findinfo () {
      const { data: res } = await this.$http.post('study/study4/findinfo', this.queryinfo)
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.info = res.data
      this.total = res.totalpage
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
