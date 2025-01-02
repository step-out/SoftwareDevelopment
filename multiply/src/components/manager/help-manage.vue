<template>
  <div class="container">
    <el-row height="10%" :gutter="17">
      <el-col :span="12">
        <el-input placeholder='请输入用户名搜索' v-model='searchUsername' clearable @keyup.enter='searchByUsername'>
          <el-button slot='append' icon='el-icon-search' @click='searchByUsername'></el-button>
        </el-input>
      </el-col>
      <el-col :span="12">
        <el-input placeholder='请输入内容搜索' v-model='searchContent' clearable @keyup.enter='searchByContent'>
          <el-button slot='append' icon='el-icon-search' @click='searchByContent'></el-button>
        </el-input>
      </el-col>
    </el-row>
    <el-table :data="filteredHelp" stripe style="width: 100%" :show-header="true"  height="580" overflow: auto>
      <el-table-column label="内容" prop="need" width="900" align="center"></el-table-column>
      <el-table-column label="用户名" prop="user" width="100" align="center"></el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="danger" size="mini" @click="deleteHelp(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data () {
    return {
      help: [],
      filteredHelp: [],
      searchUsername: '',
      searchContent: ''
    }
  },
  created () {
    this.getHelp()
  },
  methods: {
    async getHelp () {
      const { data: res } = await this.$http.get('getAllHelp')
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.help = res.data
      this.filteredHelp = this.help
    },
    searchByUsername () {
      if (this.searchUsername.trim() === '') {
        this.filteredHelp = this.help
      } else {
        this.filteredHelp = this.help.filter((help) => {
          return (
            help.user.includes(this.searchUsername)
          )
        })
      }
    },
    searchByContent () {
      if (this.searchContent.trim() === '') {
        this.filteredHelp = this.help
      } else {
        this.filteredHelp = this.help.filter((help) => {
          return (
            help.need.includes(this.searchContent)
          )
        })
      }
    },
    async deleteHelp (help) {
      const { data: res } = await this.$http.post('deleteHelp', help)
      if (res.status !== 200) return this.$message.error(res.message)
      this.$message.success('删除成功！')
      this.getHelp()
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
