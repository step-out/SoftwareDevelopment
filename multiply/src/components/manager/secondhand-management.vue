<template>
  <el-row style="height:100%; width:100%">
    <!-- 二手交易 -->
    <el-col style="height:80%" :span="12">
      <br>
      <div class="container">
        <!-- 二手交易内容 -->
        <el-card class="box-card" :body-style="{ padding: '10px' }">
          <div class="card-title">二手交易</div>
          <div style="width: 90%; margin-top: 6%; margin-left: 5%; height:73%" >
            <div class="card" v-for="(item, index) in second" :key="item.id">
              <div style="font-size:20px">{{ item.goods }}</div>
              <div style="float:right">{{ item.user }}</div>
              <el-button type="danger" size="mini" @click="deleteSecondItem(index)">删除</el-button>
              <el-divider></el-divider>
            </div>
          </div>
        </el-card>
      </div>
    </el-col>
    <!-- 跑腿代办 -->
    <el-col style="height:80%;" :span="12">
      <br>
      <div class="container">
        <!-- 跑腿代办内容 -->
        <el-card class="box-card" :body-style="{ padding: '10px' }">
          <div class="card-title">跑腿代办</div>
          <div style="width: 90%; margin-top: 6%; margin-left: 5%; height:73%" >
            <div class="card" v-for="(item, index) in help" :key="item.id">
              <div style="font-size:20px">{{ item.need }}</div>
              <div style="float:right">{{ item.user }}</div>
              <el-button type="danger" size="mini" @click="deleteHelpItem(index)">删除</el-button>
              <el-divider></el-divider>
            </div>
          </div>
        </el-card>
      </div>
    </el-col>
    <!-- 返回管理中心按钮 -->
    <el-button class="back-btn" type="primary" @click="goToManage">返回管理中心</el-button>
  </el-row>

</template>

<script>
export default {
  data () {
    return {
      second: [],
      help: []
    }
  },
  created () {
    this.getsecondinfo()
    this.gethelpinfo()
  },
  methods: {
    // 获取二手交易数据的逻辑
    async getsecondinfo () {
      // 模拟获取数据
    },
    // 获取跑腿代办数据的逻辑
    async gethelpinfo () {
      // 模拟获取数据
    },
    // 删除二手交易项
    async deleteSecondItem (index) {
      const itemToDelete = this.second[index]
      const response = await this.$http.post('life/deleteSecond', { id: itemToDelete.id })
      if (response.status === 200) {
        this.second.splice(index, 1)
        this.$message.success('删除成功')
      } else {
        this.$message.error('删除失败')
      }
    },
    // 删除跑腿代办项
    async deleteHelpItem (index) {
      const itemToDelete = this.help[index]
      const response = await this.$http.post('life/deleteHelp', { id: itemToDelete.id })
      if (response.status === 200) {
        this.help.splice(index, 1)
        this.$message.success('删除成功')
      } else {
        this.$message.error('删除失败')
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
    height: 100%;
    width: 90%;
    position: relative;
    left: 5%;
    top: 5%;
    border-radius: 25px;
    background-color: rgba(192, 189, 189, 0.5);
}

.box-card {
    width: 100%;
    background: linear-gradient(180deg, #fff, 30%, rgba(192, 189, 189, 0.5));
    border-radius: 10px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    text-align: center;
    font-family: "cat";
    font-size: 30px;
    padding: 0px;
}

.card-title {
    font-size: 24px;
    font-weight: bold;
    text-align: center;
    margin-bottom: 20px;
}

.card {
    height: auto;
    width: 80%;
    margin-left: 10%;
    padding: 10px;
    background-color: #f9f9f9;
    border-radius: 8px;
}

.el-pagination {
    margin-left: 30%;
}

.card-title {
    text-align: center;
    font-size: 24px;
    font-weight: bold;
}

/* 样式：右下角返回按钮 */
.back-btn {
  position: fixed;
  bottom: 20px;
  right: 20px;
  z-index: 999;
}
</style>
