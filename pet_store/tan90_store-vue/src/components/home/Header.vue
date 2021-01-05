<template>
  <el-card class="admin-header">
    <a href="/market">
      <img src="../../assets/adfwb123sqwgg.png" alt="" width="55px" style="float: left;margin-top: -5px;">
    </a>
    <span style="font-size: 32px;font-weight: bold;position:absolute;left: 100px">tan90-商店</span>
    <i class="el-icon-switch-button" v-on:click="logout" style="font-size: 40px;float: right"></i>
  </el-card>
</template>

<script>
export default {
  name: "Header",
  methods: {
    logout () {
      var _this = this
      this.$axios.get('/logout').then(resp => {
        if (resp && resp.data.code === 200) {
          _this.$store.commit('logout')
          _this.$router.replace('/market')
        }
      }).catch(failResponse => {})
    }
  }
}
</script>

<style scoped>
.admin-header {
  height: 80px;
  opacity: 0.85;
  line-height: 40px;
  min-width: 900px;
}
.el-icon-switch-button {
  cursor: pointer;
  outline:0;
}
</style>
