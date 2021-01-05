<template>
  <div id="paper">
    <el-form :model="updateForm" :rules="rules" class="login-container" label-position="left"
             label-width="0px" v-loading="loading">
      <h3 class="login_title">完善信息</h3>
      <el-form-item prop="name">
        <el-input type="text" v-model="updateForm.name"
                  auto-complete="off" placeholder="姓名"></el-input>
      </el-form-item>
      <el-form-item prop="telephone">
        <el-input type="text" v-model="updateForm.telephone"
                  auto-complete="off" placeholder="电话号码"></el-input>
      </el-form-item>
      <el-form-item prop="country">
        <el-input type="text" v-model="updateForm.country"
                  auto-complete="off" placeholder="国家"></el-input>
      </el-form-item>
      <el-form-item prop="state">
        <el-input type="text" v-model="updateForm.state"
                  auto-complete="off" placeholder="地区"></el-input>
      </el-form-item>
      <el-form-item prop="city">
        <el-input type="text" v-model="updateForm.city"
                  auto-complete="off" placeholder="城市"></el-input>
      </el-form-item>
      <el-form-item prop="detailLocate">
        <el-input type="text" v-model="updateForm.detailLocate"
                  auto-complete="off" placeholder="详细地址"></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="update">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "FinishInfo",
  data () {
    return {
      rules: {
        name: [{required: true, message: '不能为空', trigger: 'blur'}],
        telephone: [{required: true, message: '不能为空', trigger: 'blur'}],
        country: [{required: true, message: '不能为空', trigger: 'blur'}],
        state: [{required: true, message: '不能为空', trigger: 'blur'}],
        city: [{required: true, message: '不能为空', trigger: 'blur'}],
        detailLocate: [{required: true, message: '不能为空', trigger: 'blur'}]
      },
      checked: true,
      updateForm: {
        name:'',
        telephone:'',
        country: '',
        state: '',
        city: '',
        detailLocate: '',
      },
      user:{
        username: '',
        password: '',
      },
      loading: false
    }
  },
  methods: {
    update() {
      console.log(this.updateForm)
      var _this = this
      this.$axios
        .post('/login/register', {
          simAccount :this.user,
          telephone : this.updateForm.telephone,
          country : this.updateForm.country,
          state : this.updateForm.state,
          city: this.updateForm.city,
          detailLocate: this.updateForm.detailLocate
        })
        .then(resp => {
          if (resp.data.code === 200) {
            this.$alert('注册成功', '提示', {
              confirmButtonText: '确定'
            })
            _this.$router.replace('/login')
          } else {
            this.$alert(resp.data.message, '提示', {
              confirmButtonText: '确定'
            })
          }
        })
        .catch(failResponse => {})
    }
  },
  mounted:function () {
    this.user=this.$route.query
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
div{
  margin: -5px 0;
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
