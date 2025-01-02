<template>
  <div class="container">
    <el-row height="10%">
      <el-dropdown @command="setbelong">
        <el-button>餐厅<i class="el-icon-arrow-down el-icon--right"></i></el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="餐厅">所有餐厅</el-dropdown-item>
          <el-dropdown-item command="南光" divided>南光餐厅</el-dropdown-item>
          <el-dropdown-item command="芙蓉">芙蓉餐厅</el-dropdown-item>
          <el-dropdown-item command="勤业">勤业餐厅</el-dropdown-item>
          <el-dropdown-item command="东苑">东苑餐厅</el-dropdown-item>
          <el-dropdown-item command="竞丰" divided>竞丰餐厅</el-dropdown-item>
          <el-dropdown-item command="丰庭">丰庭餐厅</el-dropdown-item>
          <el-dropdown-item command="思源">思源餐厅</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-button type="success" style="margin-left: 10px" @click="DialogVisible = true">添加菜品</el-button>
      <el-dialog title="添加新菜品" :visible.sync="DialogVisible" width="40%" :modal="false">
        <el-form :model="newmenu" label-width="100px">
          <el-form-item label="菜名" prop="menu">
            <el-input v-model="newmenu.menu" placeholder="请输入菜名"></el-input>
          </el-form-item>
          <el-form-item label="餐厅" prop="belong">
            <el-input v-model="newmenu.belong" placeholder="请输入餐厅"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="addMenu">确定</el-button>
        </div>
      </el-dialog>
    </el-row>
    <el-table :data="filteredMenu" stripe style="width: 100%" :show-header="true"  height="580" overflow: auto>
      <el-table-column label="菜名" prop="menu" width="300" align="center"></el-table-column>
      <el-table-column label="餐厅" prop="belong" width="300" align="center"></el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="danger" size="mini" @click="deleteMenu(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data () {
    return {
      menus: [],
      filteredMenu: [],
      DialogVisible: false,
      newmenu: {
        menu: '',
        belong: ''
      }
    }
  },
  created () {
    this.getMenus()
  },
  methods: {
    async getMenus () {
      const { data: res } = await this.$http.get('menu/getAll')
      if (res.status !== 200) return this.$message.console.error(res.message)
      this.menus = res.data
      this.filteredMenu = this.menus
    },
    setbelong (command) {
      this.filteredMenu = this.menus.filter((menus) => {
        return (
          menus.belong.includes(command)
        )
      })
    },
    async addMenu () {
      const { data: res } = await this.$http.post('menu/add', this.newmenu)
      if (res.status !== 200) return this.$message.error(res.message)
      this.$message.success('添加成功！')
      this.DialogVisible = false
      this.getMenus()
    },
    async deleteMenu (menu) {
      const { data: res } = await this.$http.post('menu/deletemenu', menu)
      if (res.status !== 200) return this.$message.error(res.message)
      this.$message.success('删除成功！')
      this.getMenus()
    }
  }
}
</script>

<style scoped>
.container {
  height: 100%;
  width: 100%;
}

.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}

.el-icon-arrow-down {
  font-size: 12px;
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
