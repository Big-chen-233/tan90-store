<template>
  <div>
    <el-menu
      :default-active="0"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      style="min-width: 1300px"
      @select="handleSelect">
      <el-menu-item index="0">
        <i class="el-icon-menu"></i>
        <span slot="title">首页</span>
      </el-menu-item>
      <el-menu-item index="1">
        <i class="el-icon-menu"></i>
        <span slot="title">鸟类</span>
      </el-menu-item>
      <el-menu-item index="2">
        <i class="el-icon-menu"></i>
        <span slot="title">猫猫</span>
      </el-menu-item>
      <el-menu-item index="3">
        <i class="el-icon-menu"></i>
        <span slot="title">狗子</span>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-menu"></i>
        <span slot="title">虎纹鲨鱼</span>
      </el-menu-item>
      <el-menu-item index="5">
        <i class="el-icon-menu"></i>
        <span slot="title">爬行动物</span>
      </el-menu-item>


      <el-dropdown style="float:right;padding: 10px;margin-right: 10px">
        <el-button type="primary" >
          个人中心<i class="el-icon-arrow-down el-icon--right"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <div v-if="this.$store.state.username!=='' ">
            <el-dropdown-item @click.native="goShopCart">购物车</el-dropdown-item>
            <el-dropdown-item @click.native="goHome">订单管理</el-dropdown-item>
            <el-dropdown-item @click.native="logout">注销</el-dropdown-item>
          </div>
          <div v-else>
            <el-dropdown-item @click.native="login">登录</el-dropdown-item>
            <el-dropdown-item @click.native="register">注册</el-dropdown-item>
          </div>
        </el-dropdown-menu>
      </el-dropdown>


      <span style="color: #ffffff;position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">tan90 -不存在的商店</span>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Menu",
  data() {
    return {
      cid: ''
    }
  },
  methods:{
    login(){
      this.$router.push('/login')
    },
    goHome(){
      this.$router.push('/admin')
    },
    handleSelect(key, keyPath) {
      this.cid = key
      this.$emit('indexSelect')
    },
    logout() {
      var _this = this
      this.$axios.get('/logout').then(resp => {
        if (resp.data.code === 200) {
          // 前后端状态保持一致
          _this.$store.commit('logout')
        }
      }).catch(failResponse => {})
    },
    goShopCart() {
      this.$router.push('/shopCart')
    },
    register() {
      this.$router.push('/register')
    }
  },

}
</script>

<style scoped>
a{
  text-decoration: none;
}

span {
  pointer-events: none;
}
</style>
