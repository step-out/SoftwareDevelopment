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
      <div v-else v-for="(record, index) in filteredRecords" :key="index">
        <el-card shadow="hover" class="box-card">
          <div slot="header" class="clearfix">
            <!-- <span>卡片名称{{ recordType }}</span> -->
            <el-button style="float: right; padding: 3px 0" type="text" @click="deleteRecord(record)">删除</el-button>
            <el-button style="float: right; padding: 3px 0; margin-right: 5px" type="text" @click="editRecord(record)">修改</el-button>
          </div>
          <div class="text-content">{{ record.text }}</div>
        </el-card>
      </div>
    </el-main>
    <!-- 修改记录的模态框 -->
    <el-dialog title= "修改记录" :visible.sync="editDialogVisible" width="40%" :modal="false">
        <el-form :model="editRecordForm">
            <el-input v-model="editRecordForm.text" :rows="3" type="textarea" autocomplete="off"></el-input>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="editDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="saveEditRecord">保存</el-button>
        </span>
    </el-dialog>
  </el-container>
</template>

<script>
export default {
  data () {
    return {
      tradeRecords: [
        // {
        //   id: 1,
        //   text: '1111111111111111111111111111111111111111'
        // },
        // {
        //   id: 2,
        //   text: '1111111111111111111111111111111111111111'
        // },
        // {
        //   id: 3,
        //   text: '1111111111111111111111111111111111111111'
        // },
        // {
        //   id: 4,
        //   text: '1111111111111111111111111111111111111111'
        // },
        // {
        //   id: 5,
        //   text: '1111111111111111111111111111111111111111'
        // },
        // {
        //   id: 6,
        //   text: '1111111111111111111111111111111111111111'
        // }
      ], // 所有记录
      searchKeyword: '', // 存储搜索关键词
      filteredRecords: [], // 存储经过搜索筛选后的记录
      editDialogVisible: false, // 控制修改记录模态框的显示与隐藏
      editRecordForm: {}, // 用于存储正在修改的记录数据
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
      const { data: res } = await this.$http.get('records', {
        params: {
          type: this.recordType, // 记录类型：1表示二手交易，2表示跑腿信息
          user: this.user
        }
      }) // 获取与我(user)相关的交易记录
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.tradeRecords = res.data // 列表--[{id:, text:}]
      this.filteredRecords = this.tradeRecords
    },
    searchRecords () {
      if (this.searchKeyword.trim() === '') {
        // 如果搜索关键词为空，显示全部记录
        this.filteredRecords = this.tradeRecords
      } else {
        // 根据关键词过滤交易记录
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
    async DelRecord (recordId) {
      const { data: res } = await this.$http.delete('record', {
        params: {
          type: this.recordType, // 记录类型：1表示二手交易，2表示跑腿信息
          user: this.user,
          id: recordId
        }
      }) // 根据id删除记录
      if (res.status !== 200) return this.$message.console.error(res.message)
    },
    deleteRecord (record) {
      // 删除操作，向后端发送删除请求
      const index = this.tradeRecords.findIndex((r) => r.id === record.id)
      if (index !== -1) {
        this.tradeRecords.splice(index, 1)
        this.filteredRecords = this.filteredRecords.filter((r) => r.id !== record.id)
        // this.DelRecord(record.id)
      }
    },
    saveEditRecord () {
      // 保存修改操作，后端发送修改后的数据
      const index = this.tradeRecords.findIndex(
        (r) => r.id === this.editRecordForm.id
      )
      if (index !== -1) {
        this.tradeRecords[index] = { ...this.editRecordForm }
        this.filteredRecords = this.filteredRecords.map((record) =>
          record.id === this.editRecordForm.id ? { ...this.editRecordForm } : record
        )
        this.SaveEditRecord()
      }
      this.editDialogVisible = false
    },
    async SaveEditRecord () {
      const { data: res } = await this.$http.put('record', {
        params: {
          type: this.recordType, // 记录类型：1表示二手交易，2表示跑腿信息
          user: this.suser,
          id: this.editRecordForm.id,
          text: this.editRecordForm.text
        }
      }) // 根据id保存修改后的记录
      if (res.status !== 200) return this.$message.console.error(res.message)
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
