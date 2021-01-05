<template>
  <el-menu
    class="el-menu-vertical-demo"
    default-active="0"
    @select="handleSelect"
    active-text-color="red">
    <el-menu-item index="0">
      <i class="el-icon-right"></i>
      <span slot="title">全部</span>
    </el-menu-item>
    <el-menu-item v-for="(item,i) in sideList" :key="i" :index="item.id">
      {{ item.name }}
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "SideMenu",
  data() {
    return {
      pid:'',
    }
  },
  computed: {
    sideList() {
      return this.$store.state.products
    }
  },
  methods:{
    handleSelect(key, keyPath){
      this.pid=key;
      this.$emit('indexSelect')
    }
  }
}
</script>

<style scoped>

</style>
