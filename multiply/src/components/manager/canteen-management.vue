<template>
  <el-row style="height:100%; width:100%" type="flex" justify="start">
    <!-- 返回管理中心按钮 -->
    <el-button class="back-btn" type="primary" @click="goToManage">返回管理中心</el-button>
    <el-col style="height:100%" :span="5" v-for="(restaurant, index) in restaurants" :key="index">
      <!-- 餐厅名称框 -->
      <div class="box-card">
        <div class="restaurant-name">{{ restaurant.name }}</div>
      </div>
      <!-- 菜单表格 -->
      <el-table :data="restaurant.menu" stripe style="width: 90%; margin-top: 20%; margin-left: 5%" height="500" :show-header="false">
        <el-table-column prop="menu" align="center"></el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="danger" size="mini" @click="deleteMenuItem(restaurant.name, scope.$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data () {
    return {
      // 将各餐厅的数据放到一个数组中，方便遍历
      restaurants: [
        { name: '南光餐厅', menu: this.nanguang },
        { name: '芙蓉餐厅', menu: this.furong },
        { name: '勤业餐厅', menu: this.qinye },
        { name: '东苑餐厅', menu: this.dongyuan },
        { name: '竞丰餐厅', menu: this.jingfeng },
        { name: '丰庭餐厅', menu: this.fengting },
        { name: '思源餐厅', menu: this.siyuan }
      ]
    }
  },
  methods: {
    goToManage () {
      this.$router.push('/manage') // 管理中心页面的路由
    },
    async getinfo1 () {
      // 获取菜品数据的逻辑
    },
    async getinfo2 () {
      // 获取菜品数据的逻辑
    },
    async deleteMenuItem (restaurant, index) {
      const menuArray = this[restaurant] // 获取对应餐厅的菜品列表
      const itemToDelete = menuArray[index] // 获取要删除的菜品
      // 调用API删除该菜品
      const response = await this.$http.post('menu/delete', { id: itemToDelete.id })
      if (response.status === 200) {
        // 删除成功后移除该菜品
        menuArray.splice(index, 1)
        this.$message.success('删除成功')
      } else {
        this.$message.error('删除失败')
      }
    }
  }
}
</script>

<style scoped>
/* 右上角的返回按钮 */
.back-btn {
  position: absolute;
  top: 700px;
  right: 20px;
  z-index: 999;
}

.container {
  height: 40%;
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

.restaurant-name {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 24px;
  font-weight: bold;
  padding: 10px;
  background-color: #ffbb33;
  border-radius: 8px;
  color: white;
  margin: 10px 0;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
}

.el-table {
  margin-top: 20px;
}

.el-button {
  margin-top: 5px;
}

.el-pagination {
  margin-left: 30%;
}
</style>
