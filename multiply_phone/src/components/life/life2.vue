// 二手交易/跑腿代办
<template>
  <div style="height:100%; width:100%">
    <br>
    <el-button type="text" style="float:right; font-size:20px; margin-right:30px;" @click="dialogFormVisiblesecond = true">发布</el-button>
    <el-dialog :visible.sync="dialogFormVisiblesecond" width="60%" :modal="false" v-if="isSecond">
      <el-form :model="putsecondinfo">
        <el-input v-model="putsecondinfo.goods" :rows="3" type="textarea" autocomplete="off"></el-input>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="givesecondinfo()">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="dialogFormVisiblehelp" width="60%" :modal="false" v-if="isHelp">
      <el-form :model="puthelpinfo">
        <el-input v-model="puthelpinfo.need" :rows="3" type="textarea" autocomplete="off"></el-input>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="givesecondinfo()">确 定</el-button>
      </div>
    </el-dialog>
    <div style="height: 10%; position: relative; left: 36%;">
      <el-dropdown @command="setbelong">
        <el-button>选择服务<i class="el-icon-arrow-down el-icon--right"></i></el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="second">二手交易</el-dropdown-item>
          <el-dropdown-item command="help">跑腿服务</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <div class="container" v-if="isSecond">
      <div style="width: 90%; height: 90%; margin-left: 5%; overflow: auto;">
        <div class="card" v-for="item in second" :key="item.id">
          <div style="font-size:20px">{{ item.goods }}</div>
          <div style="float:right">{{ item.user }}</div>
          <el-divider></el-divider>
        </div>
      </div>
      <!-- 分页 -->
      <el-pagination @current-change="handleCurrentChangesecond"
      :current-page="querysecondinfo.pagenum" :page-size="querysecondinfo.pagesize"
      layout="total, prev, pager, next, jumper" :total="secondtotal" style="margin-top: 2%">
      </el-pagination>
    </div>
    <div class="container" v-if="isHelp">
      <div style="width: 90%; height: 90%; margin-left: 5%; overflow: auto;">
        <div class="card" v-for="item in help" :key="item.id">
          <div style="font-size:20px">{{ item.need }}</div>
          <div style="float:right">{{ item.user }}</div>
          <el-divider></el-divider>
        </div>
      </div>
      <!-- 分页 -->
      <el-pagination @current-change="handleCurrentChangehelp"
      :current-page="queryhelpinfo.pagenum" :page-size="queryhelpinfo.pagesize"
      layout="total, prev, pager, next, jumper" :total="helptotal" style="margin-top: 2%">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import jwtDecode from 'jwt-decode'
export default ({
  data () {
    return {
      isSecond: false,
      isHelp: false,
      second: [],
      help: [],
      querysecondinfo: {
        // 当前的页数
        pagenum: 1,
        pagesize: 5
      },
      queryhelpinfo: {
        // 当前的页数
        pagenum: 1,
        pagesize: 5
      },
      secondtotal: '',
      helptotal: '',
      putsecondinfo: {
        goods: '',
        user: ''
      },
      puthelpinfo: {
        need: '',
        user: ''
      },
      dialogFormVisiblesecond: false,
      dialogFormVisiblehelp: false
    }
  },
  created () {
    this.getsecondinfo()
    this.gethelpinfo()
  },
  methods: {
    // 获取内容
    async getsecondinfo () {
      const { data: res } = await this.$http.post('getSecond', this.querysecondinfo)
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.second = res.data
      this.secondtotal = res.totalnum
    },
    async gethelpinfo () {
      const { data: res } = await this.$http.post('getHelp', this.queryhelpinfo)
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.help = res.data
      this.helptotal = res.totalnum
    },
    setbelong (command) {
      if (command === 'second') {
        this.isSecond = true
        this.isHelp = false
      } else {
        this.isHelp = true
        this.isSecond = false
      }
    },
    // 监听页码值改变的事件
    handleCurrentChangesecond (newPage) {
      this.querysecondinfo.pagenum = newPage
      this.getsecondinfo()
    },
    handleCurrentChangehelp (newPage) {
      this.queryhelpinfo.pagenum = newPage
      this.gethelpinfo()
    },
    async givesecondinfo () {
      const tokenStr = window.sessionStorage.getItem('token')
      const code = jwtDecode(tokenStr)
      this.putsecondinfo.user = code.username
      const { data: res } = await this.$http.post('postSecond', this.putsecondinfo)
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.dialogFormVisiblesecond = false
      this.getsecondinfo()
    },
    async givehelpinfo () {
      const tokenStr = window.sessionStorage.getItem('token')
      const code = jwtDecode(tokenStr)
      this.puthelpinfo.user = code.username
      const { data: res } = await this.$http.post('postHelp', this.puthelpinfo)
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.dialogFormVisiblehelp = false
      this.gethelpinfo()
    }
  }
})
</script>

<style scoped>
.container {
    height: 460px;
    width: 90%;
    position: relative;
    left: 5%;
    margin-top: 5%;
    border-radius: 25px;
    background-color: rgba(192, 189, 189, 0.5);
}

.el-pagination {
    margin-left: 9%;
}

.card {
    width: 90%;
    margin-left: 5%;
    margin-top: 5%;
}
</style>
