<template>
  <el-menu
    class="el-menu-admin"
    router
    mode="vertical"
    default-active="'/admin'">
    <div style="height: 80px;"></div>
    <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
      {{ item.navItem }}
    </el-menu-item>

  </el-menu>
</template>

<script>
export default {
  name: "SideMenu",
  data() {
    return {
      navList:[
        {name:'/admin',navItem:'管理面板'},
        {name:'/shopCart',navItem:'购物车'},
        {name:'/itemOrder',navItem:'订单管理'}
      ]
    }
  },
  methods:{

  }
}
</script>

<style scoped>
.el-menu-admin {
  border-radius: 5px;
  height: 100%;
}
</style>
