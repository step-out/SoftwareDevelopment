// 二手交易/跑腿代办
<template>
    <el-row style="height:100%; width:100%">
        <!-- 二手交易 -->
        <el-col style="height:100%" :span="12">
            <br>
            <div class="container">
                <div class="box-card">二手交易</div>
                <el-button type="text" style="float:right;font-size:20px;margin-right:30px" @click="dialogFormVisiblesecond = true">发布</el-button>
                <el-dialog :visible.sync="dialogFormVisiblesecond" width="40%" :modal="false">
                    <el-form :model="putsecondinfo">
                        <el-input v-model="putsecondinfo.goods" :rows="3" type="textarea" autocomplete="off"></el-input>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button type="primary" @click="givesecondinfo()">确 定</el-button>
                    </div>
                </el-dialog>
                <div style="width: 90%; margin-top: 6%; margin-left: 5%; height:65%" >
                    <div class="card" v-for="item in second" :key="item.id">
                        <div style="font-size:20px">{{ item.goods }}</div>
                        <div style="float:right">{{ item.user }}</div>
                        <el-divider></el-divider>
                    </div>
                </div>
                <!-- 分页 -->
                <el-pagination @current-change="handleCurrentChangesecond"
                :current-page="querysecondinfo.pagenum" :page-size="querysecondinfo.pagesize"
                layout="total, prev, pager, next, jumper" :total="secondtotal">
                </el-pagination>
            </div>
        </el-col>
        <!-- 跑腿代办 -->
        <el-col style="height:100%;" :span="12">
            <br>
            <div class="container">
                <div class="box-card">跑腿代办</div>
                <el-button type="text" style="float:right;font-size:20px;margin-right:30px" @click="dialogFormVisiblehelp = true">发布</el-button>
                <el-dialog :visible.sync="dialogFormVisiblehelp" width="40%" :modal="false">
                    <el-form :model="puthelpinfo">
                        <el-input v-model="puthelpinfo.need" :rows="3" type="textarea" autocomplete="off"></el-input>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button type="primary" @click="givehelpinfo()">确 定</el-button>
                    </div>
                </el-dialog>
                <div style="width: 90%; margin-top: 6%; margin-left: 5%; height:65%" >
                    <div class="card" v-for="item in help" :key="item.id">
                        <div style="font-size:20px">{{ item.need }}</div>
                        <div style="float:right">{{ item.user }}</div>
                        <el-divider></el-divider>
                    </div>
                </div>
                <!-- 分页 -->
                <el-pagination @current-change="handleCurrentChangehelp"
                :current-page="queryhelpinfo.pagenum" :page-size="queryhelpinfo.pagesize"
                layout="total, prev, pager, next, jumper" :total="helptotal">
                </el-pagination>
            </div>
        </el-col>
    </el-row>
</template>

<script>
import jwtDecode from 'jwt-decode'
export default ({
  data () {
    return {
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
    height: 400px;
    width: 90%;
    position: relative;
    left:5%;
    top:5%;
    border-radius: 25px;
    background-color: rgba(192, 189, 189, 0.5);
}

.box-card {
    width: 60%;
    height: 10%;
    background: linear-gradient(180deg,#fff, 30%, rgba(192, 189, 189, 0.5));
    border-radius: 10px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    text-align: center;
    font-family: "cat";
    font-size: 30px;
    position: relative;
    top: 5%;
    left: 20%;
}

.el-pagination {
    margin-left: 30%;
}

.card {
    height: 20%;
    width: 80%;
    margin-left: 10%;
}
</style>
