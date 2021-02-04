<template>
  <div id="paper">
    <el-form v-loading="loading" :model="loginForm" :rules="rules" class="login-container"
             label-position="left" label-width="0px">
      <h3 class="login_title">系统登录</h3>

      <el-form-item prop="username">
        <el-input v-model="loginForm.username" auto-complete="off"
                  placeholder="账号" type="text"></el-input>
      </el-form-item>

      <el-form-item prop="password">
        <el-input v-model="loginForm.password" auto-complete="off"
                  placeholder="密码" type="password"></el-input>
      </el-form-item>
      <el-form-item>

        <el-row :span="24">
          <el-col :span="12">
            <el-input v-model="code" auto-complete="off" placeholder="请输入验证码" size="4" type="text" @change="verify"></el-input>
          </el-col>
          <el-col :span="12">
            <div class="login-code" @click="refreshCode">
              <s-identify :identify-code="identifyCode"></s-identify>
            </div>
          </el-col>
          <el-col>
            <p style="color: crimson">{{errorMessage}}</p>
          </el-col>
        </el-row>

      </el-form-item>

      <el-checkbox v-model="checked" class="login_remember"
                   label-position="left"><span style="color: #505458">记住密码</span></el-checkbox>

      <el-form-item style="width: 100%">
        <el-button style="width: 40%;background: #505458;border: none" type="primary" v-on:click="login">登录</el-button>
        <router-link to="register">
          <el-button style="width: 40%;background: #505458;border: none" type="primary">注册</el-button>
        </router-link>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import SIdentify from './SIdentify'

export default {
  name: "Login",
  components: {SIdentify},
  data() {
    return {
      rules: {
        username: [{required: true, message: '用户名不为空', trigger: 'blur'}],
        password: [{required: true, message: '密码不为空', trigger: 'blur'}],
        code: [{required: true, message: '验证码不为空', trigger: 'blur'}]
      },
      checked: true,
      loginForm: {
        username: '',
        password: ''
      },
      loading: false,
      identifyCode: '',
      code: '',
      identifyCodes: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '0'],
      errorMessage: ''
    }
  },
  methods: {
    verify(){
      this.errorMessage = ''
      console.log(this.code)
      if (this.code !== this.identifyCode){
        this.errorMessage = '验证码错误'
      }
    },
    login() {
      if (this.code !== this.identifyCode) {

        return
      }
      var _this = this;
      this.$axios
        .post('/login/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(resp => {
          if (resp.data.code === 200) {
            var data = resp.data.result
            _this.$store.commit('login', data)
            var path = _this.$route.query.redirect
            _this.$router.replace({path: path === '/' || path === undefined ? '/market' : path})
          } else {
            this.$alert(resp.data.message, '提示', {
              confirmButtonText: '确定'
            })
          }
        })
        .catch(failResponse => {
        })
    },
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    },
    refreshCode() {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[
          this.randomNum(0, this.identifyCodes.length)
          ]
      }
    }
  },
  mounted: function () {
    this.refreshCode()
  }
}
</script>

<style scoped>

#paper {
  background: url("../assets/bg/2cbed63d4739ca88b9d240830c226eb8.2217c9b.jpg") no-repeat center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}

body {
  margin: 0;
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
  margin: 0 auto 40px auto;
  text-align: center;
  color: #505458;
}

.login_remember {
  margin: 0 0 35px 0;
  text-align: left;
}
</style>
