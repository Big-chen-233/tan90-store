<template>
<div>
  <el-container>
    <el-aside style="width: 200px;margin-top: 20px">
      <switch></switch>
      <SideMenu ref="sideMenu" @indexSelect="listByProduct"></SideMenu>
    </el-aside>
    <el-main>

      <el-table
        class="books-area"
        :data="items"
        stripe
        style="width: 100%">
        <el-table-column
          prop="product.img"
          label="\"
          width="180">
          <template width="90" slot-scope="scope">

            <img style="width:80px;height:80px;border:none;" :src="scope.row.product.img">

          </template>
        </el-table-column>
        <el-table-column
          prop="name"
          label="名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="price"
          label="价格"
          width="180">
        </el-table-column>
        <el-table-column
          prop="quantity"
          label="剩余数量">
        </el-table-column>
        <el-table-column
          prop="describe"
          label="描述">
        </el-table-column>
        <el-table-column
          label="加购物车">

          <template slot-scope="scope">
          <el-button icon="el-icon-circle-plus" style="font-size: 20px" @click="addToCart(scope.row)"></el-button>
          </template>

        </el-table-column>
      </el-table>

    </el-main>
  </el-container>

</div>
</template>

<script>
import SideMenu from "./SideMenu";
export default {
  name: "Exhibition",
  components: {SideMenu},
  data() {
    return {
      items:[]
    }
  },
  methods:{
    listByProduct(){
      // console.log("1111111")
      var _this = this
      var pid = this.$refs.sideMenu.pid
      var cid1 = this.$store.state.products[0].category.id
      var cid2 = this.$store.state.products[this.$store.state.products.length-1].category.id
      if(cid1!==cid2){
        cid1=0;
      }
      var url = 'categories/'+cid1+'/products/'+pid+'/items'
      this.$axios.get(url).then(resp => {
        if (resp && resp.status === 200) {
          _this.items = resp.data
        }
      })
    },
    addToCart(item) {
      if(this.$store.state.username!==''){
        var _this = this
        var username = _this.$store.state.username
        this.$axios.post('shopcart/update',{
          item:item,
          username:username,
          quantity:1
        })

      }else{
        this.$router.push('/login')
      }
    }
  },


}
</script>

<style scoped>
.books-area {
  width: 990px;
  margin-left: auto;
  margin-right: auto;
}
</style>
