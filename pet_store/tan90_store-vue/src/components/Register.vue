<template>
  <div id="paper">
  <el-form :model="loginForm" :rules="rules" class="login-container" label-position="left"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">用户注册</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="loginForm.username"
                auto-complete="off" placeholder="账号" @change="isExist"></el-input>
      <p>{{message}}</p>
      <p style="color: crimson">{{error}}</p>

    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="register">下一步</el-button>
    </el-form-item>
  </el-form>
  </div>
</template>

<script>
export default {
  name: "Register",
  data () {
    return {
      rules: {
        username: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
        password: [{required: true, message: '密码不能为空', trigger: 'blur'}]
      },
      checked: true,
      loginForm: {
        username: '',
        password: '',
      },
      loading: false,
      message:'',
      error:'',
      exist:1
    }
  },
  methods:{
    register() {
      if(this.loginForm.username!==''&&this.loginForm.password!==''&&this.exist===0){
        var _this = this
        _this.$router.push({path:'/finish', query: this.loginForm})
      }else{
        alert("填写有效的信息")
      }
    },
    isExist(){
      this.message = ''
      this.error = ''
      this.$axios.post('login/isExist',this.loginForm.username)
        .then(resp => {
          if (resp.data.code === 200) {
            this.message = resp.data.result
            this.exist=0
          }else {
            this.error = "用户名已被使用"
            this.exist=0
          }
        }).catch()
    }
  }
}
</script>

<style scoped>
#paper {
  background:url("../assets/bg/2cbed63d4739ca88b9d240830c226eb8.2217c9b.jpg") no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
body{
  margin: -5px 0px;
}
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
.login_remember {
  margin: 0px 0px 35px 0px;
  text-align: left;
}
</style>
