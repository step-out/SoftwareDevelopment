// 食堂菜品
<template>
  <div style="height:100%; width:100%">
    <br>
    <div style="height: 10%; position: relative; left: 36%;">
      <el-dropdown @command="setbelong">
        <el-button>选择餐厅<i class="el-icon-arrow-down el-icon--right"></i></el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="南光">南光餐厅</el-dropdown-item>
          <el-dropdown-item command="芙蓉">芙蓉餐厅</el-dropdown-item>
          <el-dropdown-item command="勤业">勤业餐厅</el-dropdown-item>
          <el-dropdown-item command="东苑">东苑餐厅</el-dropdown-item>
          <el-dropdown-item command="竞丰" divided>竞丰餐厅</el-dropdown-item>
          <el-dropdown-item command="丰庭">丰庭餐厅</el-dropdown-item>
          <el-dropdown-item command="思源">思源餐厅</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <div class="container">
      <el-table :data="filteredMenu" stripe style="width: 90%; height: 90%; top: 5%; left: 5%; overflow: auto" :show-header="false">
        <el-table-column prop="menu" align="center"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default ({
  data () {
    return {
      menus: [],
      filteredMenu: []
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
    },
    setbelong (command) {
      this.filteredMenu = this.menus.filter((menus) => {
        return (
          menus.belong.includes(command)
        )
      })
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
</style>
