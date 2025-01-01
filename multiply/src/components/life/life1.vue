// 食堂菜品
<template>
    <el-row style="height:100%; width:100%">
        <!-- 思明 -->
        <el-col style="height:100%" :span="14">
            <br>
            <div class="container">
                <el-row style="height:100%; width:100%">
                    <el-col style="height:100%" :span="6">
                        <div class="box-card">南光餐厅</div>
                        <el-table :data="nanguang" stripe style="width: 90%; margin-top: 20%; margin-left: 5%" height="300" :show-header="false">
                            <el-table-column prop="menu" align="center"></el-table-column>
                        </el-table>
                    </el-col>
                    <el-col style="height:100%" :span="6">
                        <div class="box-card">芙蓉餐厅</div>
                        <el-table :data="furong" stripe style="width: 90%; margin-top: 20%; margin-left: 5%" height="300" :show-header="false">
                            <el-table-column prop="menu" align="center"></el-table-column>
                        </el-table>
                    </el-col>
                    <el-col style="height:100%" :span="6">
                        <div class="box-card">勤业餐厅</div>
                        <el-table :data="qinye" stripe style="width: 90%; margin-top: 20%; margin-left: 5%" height="300" :show-header="false">
                            <el-table-column prop="menu" align="center"></el-table-column>
                        </el-table>
                    </el-col>
                    <el-col style="height:100%" :span="6">
                        <div class="box-card">东苑餐厅</div>
                        <el-table :data="dongyuan" stripe style="width: 90%; margin-top: 20%; margin-left: 5%" height="300" :show-header="false">
                            <el-table-column prop="menu" align="center"></el-table-column>
                        </el-table>
                    </el-col>
                </el-row>
            </div>
        </el-col>
        <!-- 翔安 -->
        <el-col style="height:100%;" :span="10">
            <br>
            <div class="container">
                <el-row style="height:100%; width:100%">
                    <el-col style="height:100%" :span="8">
                        <div class="box-card">竞丰餐厅</div>
                        <el-table :data="jingfeng" stripe style="width: 90%; margin-top: 20%; margin-left: 5%" height="300" :show-header="false">
                            <el-table-column prop="menu" align="center"></el-table-column>
                        </el-table>
                    </el-col>
                    <el-col style="height:100%" :span="8">
                        <div class="box-card">丰庭餐厅</div>
                        <el-table :data="fengting" stripe style="width: 90%; margin-top: 20%; margin-left: 5%" height="300" :show-header="false">
                            <el-table-column prop="menu" align="center"></el-table-column>
                        </el-table>
                    </el-col>
                    <el-col style="height:100%" :span="8">
                        <div class="box-card">思源餐厅</div>
                        <el-table :data="siyuan" stripe style="width: 90%; margin-top: 20%; margin-left: 5%" height="300" :show-header="false">
                            <el-table-column prop="menu" align="center"></el-table-column>
                        </el-table>
                    </el-col>
                </el-row>
            </div>
        </el-col>
    </el-row>
</template>

<script>
export default ({
  data () {
    return {
      nanguang: [],
      furong: [],
      qinye: [],
      dongyuan: [],
      jingfeng: [],
      fengting: [],
      siyuan: []
    }
  },
  created () {
    this.getinfo1()
    this.getinfo2()
  },
  methods: {
    // 获取内容
    async getinfo1 () {
      const { data: res1 } = await this.$http.get('menu/getNanguang')
      if (res1.status !== 200) return this.$message.console.error(res1.message)
      this.nanguang = res1.data
      const { data: res2 } = await this.$http.get('menu/getFurong')
      if (res2.status !== 200) return this.$message.console.error(res2.message)
      this.furong = res2.data
      const { data: res3 } = await this.$http.get('menu/getQinye')
      if (res3.status !== 200) return this.$message.console.error(res3.message)
      this.qinye = res3.data
      const { data: res4 } = await this.$http.get('menu/getDongyuan')
      if (res4.status !== 200) return this.$message.console.error(res4.message)
      this.dongyuan = res4.data
    },
    async getinfo2 () {
      const { data: res5 } = await this.$http.get('menu/getJingfeng')
      if (res5.status !== 200) return this.$message.console.error(res5.message)
      this.jingfeng = res5.data
      const { data: res6 } = await this.$http.get('menu/getFengting')
      if (res6.status !== 200) return this.$message.console.error(res6.message)
      this.fengting = res6.data
      const { data: res7 } = await this.$http.get('menu/getSiyuan')
      if (res7.status !== 200) return this.$message.console.error(res7.message)
      this.siyuan = res7.data
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
    overflow-y: auto;
}

.box-card {
    width: 60%;
    height: 10%;
    background: linear-gradient(180deg,#fff, 30%, rgba(192, 189, 189, 0.5));
    border-radius: 10px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    text-align: center;
    font-family: "cat";
    font-size: 25px;
    position: relative;
    top: 5%;
    left: 20%;
}
</style>
