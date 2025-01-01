<template>
  <el-container class='records-container'>
    <el-header height="10%">
      <!-- 搜索栏区域 -->
      <el-input placeholder='请输入关键词搜索' v-model='searchKeyword' clearable @keyup.enter='searchRecords' class='search-input'>
        <el-button slot='append' icon='el-icon-search' @click='searchRecords'></el-button>
      </el-input>
    </el-header>
    <el-main class="main-container">
      <!-- 发帖信息 -->
      <!-- 如果文本列表为空，显示提示文字 -->
      <div v-if="tradeRecords.length === 0">
        <el-empty description="暂无数据"></el-empty>
      </div>
      <div v-else v-for="record in filteredRecords" :key=record.id>
        <el-card shadow="hover" class="box-card">
          <div slot="header" class="clearfix">
            <el-button style="float: right; padding: 3px 0" type="text" @click="deleteRecord(record)">删除</el-button>
            <el-button style="float: right; padding: 3px 0; margin-right: 5px" type="text" @click="editRecord(record)">修改</el-button>
          </div>
          <div class="text-content">{{ record.goods }}</div>
        </el-card>
      </div>
    </el-main>
    <!-- 修改记录的模态框 -->
    <el-dialog title= "修改记录" :visible.sync="editDialogVisible" width="40%" :modal="false">
      <el-form :model="editRecordForm">
          <el-input v-model="editRecordForm.goods" :rows="3" type="textarea" autocomplete="off"></el-input>
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
      tradeRecords: [], // 所有记录
      filteredRecords: [], // 搜索筛选后的记录
      searchKeyword: '', // 存储搜索关键词
      editDialogVisible: false,
      editRecordForm: {
        id: '',
        goods: ''
      },
      user: this.$route.params.user,
      recordType: this.$route.params.id
    }
  },
  created () {
    this.getData()
  },
  methods: {
    async getData () {
      // 页面加载时初始化筛选后的记录为全部记录
      const tokenStr = window.sessionStorage.getItem('token')
      const code = jwtDecode(tokenStr)
      // 获取与我(user)相关的交易记录
      const { data: res } = await this.$http.post('getUserSecond', { username: code.username })
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.tradeRecords = res.data
      this.filteredRecords = this.tradeRecords
    },
    searchRecords () {
      // 如果搜索关键词为空，显示全部记录
      if (this.searchKeyword.trim() === '') {
        this.filteredRecords = this.tradeRecords
      } else { // 根据关键词过滤交易记录
        this.filteredRecords = this.tradeRecords.filter((record) => {
          return (
            record.text.includes(this.searchKeyword)
          )
        })
      }
    },
    editRecord (record) {
      // 打开修改记录模态框，并将当前记录数据填充到表单中
      this.editDialogVisible = true
      this.editRecordForm = { ...record }
    },
    async save () {
      const { data: res } = await this.$http.post('editsecond', this.editRecordForm)
      if (res.status !== 200) return this.$message.error(res.message)
      this.$message.success('修改成功！')
      this.editDialogVisible = false
      this.getData()
      this.searchRecords()
    },
    async deleteRecord (record) {
      const { data: res } = await this.$http.post('deletesecond', record)
      if (res.status !== 200) return this.$message.error(res.message)
      this.$message.success('删除成功！')
      this.getData()
      this.searchRecords()
    }
  }
}
</script>

<style scoped>

.records-container {
  height: 100%;
  width: 100%;
}

.search-input {
  width: 300px;
}

.main-container {
  min-height: 90%;
  overflow-y: auto; /* 当内容在垂直方向（y轴）超出容器高度时，自动出现滚动条 */
}

.box-card {
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
