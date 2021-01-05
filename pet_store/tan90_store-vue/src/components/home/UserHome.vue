<template>
  <el-container id="admin-body" class="el-container">
    <el-header style="z-index: 1;height: 80px;margin-left: -20px">
      <Header style="position: absolute;width: 98%;"></Header>
    </el-header>

    <el-container >
      <el-aside style="height: 112%;margin-top: -80px;width: 250px">
        <side-menu></side-menu>
      </el-aside>

      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideMenu from "./SideMenu";
import Header from "./Header";
export default {
  name: "UserHome",
  components: {SideMenu ,Header}
}
</script>

<style scoped>
#admin-body {
  height: 95vh;
}
</style>
