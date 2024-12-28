// 教材资源
<template>
    <div>
        <br><br>
        <el-card>
            <!-- 搜索 -->
            <el-input placeholder="请输入教材名称" v-model="queryinfo.query" style="margin-top: 2%;">
                <el-button slot="append" icon="el-icon-search" @click="getinfo"></el-button>
            </el-input>
            <el-table :data="info" stripe style="width: 100%; margin-top: 5%" height="500">
                <el-table-column prop="name" label="教材名称" width="150" align="center">
                </el-table-column>
                <el-table-column label="电子书" width="240">
                    <template slot-scope="scope">
                        <a :href="scope.row.text" target="_blank">{{ scope.row.text }}</a>
                    </template>
                </el-table-column>
                <el-table-column label="推荐网课">
                    <template slot-scope="scope">
                        <a :href="scope.row.course" target="_blank">{{ scope.row.course }}</a>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>

<script>
export default {
  data () {
    return {
      queryinfo: {
        query: ''
      },
      info: []
    }
  },
  created () {
    this.getinfo()
  },
  methods: {
    async getinfo () {
      const { data: res } = await this.$http.post('study/textbooksource', this.queryinfo)
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.info = res.data
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
