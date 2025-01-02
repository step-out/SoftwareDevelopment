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
                        <el-table :data="nanguang" stripe style="width: 90%; margin-top: 20%; margin-left: 5%; overflow: auto" height="300" :show-header="false">
                            <el-table-column prop="menu" align="center"></el-table-column>
                        </el-table>
                    </el-col>
                    <el-col style="height:100%" :span="6">
                        <div class="box-card">芙蓉餐厅</div>
                        <el-table :data="furong" stripe style="width: 90%; margin-top: 20%; margin-left: 5%; overflow: auto" height="300" :show-header="false">
                            <el-table-column prop="menu" align="center"></el-table-column>
                        </el-table>
                    </el-col>
                    <el-col style="height:100%" :span="6">
                        <div class="box-card">勤业餐厅</div>
                        <el-table :data="qinye" stripe style="width: 90%; margin-top: 20%; margin-left: 5%; overflow: auto" height="300" :show-header="false">
                            <el-table-column prop="menu" align="center"></el-table-column>
                        </el-table>
                    </el-col>
                    <el-col style="height:100%" :span="6">
                        <div class="box-card">东苑餐厅</div>
                        <el-table :data="dongyuan" stripe style="width: 90%; margin-top: 20%; margin-left: 5%; overflow: auto" height="300" :show-header="false">
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
                        <el-table :data="jingfeng" stripe style="width: 90%; margin-top: 20%; margin-left: 5%; overflow: auto" height="300" :show-header="false">
                            <el-table-column prop="menu" align="center"></el-table-column>
                        </el-table>
                    </el-col>
                    <el-col style="height:100%" :span="8">
                        <div class="box-card">丰庭餐厅</div>
                        <el-table :data="fengting" stripe style="width: 90%; margin-top: 20%; margin-left: 5%; overflow: auto" height="300" :show-header="false">
                            <el-table-column prop="menu" align="center"></el-table-column>
                        </el-table>
                    </el-col>
                    <el-col style="height:100%" :span="8">
                        <div class="box-card">思源餐厅</div>
                        <el-table :data="siyuan" stripe style="width: 90%; margin-top: 20%; margin-left: 5%; overflow: auto" height="300" :show-header="false">
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
      menus: [],
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
    this.getMenus()
  },
  methods: {
    // 获取内容
    async getMenus () {
      const { data: res } = await this.$http.get('menu/getAll')
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.menus = res.data
      this.nanguang = this.menus.filter((menus) => {
        return (
          menus.belong.includes('南光')
        )
      })
      this.furong = this.menus.filter((menus) => {
        return (
          menus.belong.includes('芙蓉')
        )
      })
      this.qinye = this.menus.filter((menus) => {
        return (
          menus.belong.includes('勤业')
        )
      })
      this.dongyuan = this.menus.filter((menus) => {
        return (
          menus.belong.includes('东苑')
        )
      })
      this.jingfeng = this.menus.filter((menus) => {
        return (
          menus.belong.includes('竞丰')
        )
      })
      this.fengting = this.menus.filter((menus) => {
        return (
          menus.belong.includes('丰庭')
        )
      })
      this.siyuan = this.menus.filter((menus) => {
        return (
          menus.belong.includes('思源')
        )
      })
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
    font-size: 25px;
    position: relative;
    top: 5%;
    left: 20%;
}
</style>
