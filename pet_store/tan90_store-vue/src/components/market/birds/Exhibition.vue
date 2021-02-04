<template>
<div>
  <el-container>
    <el-aside style="width: 200px;margin-top: 20px">
      <switch></switch>
      <SideMenu ref="sideMenu" @indexSelect="listByProduct"></SideMenu>
    </el-aside>
    <el-main>
      <el-container>

        <el-header>

          <el-autocomplete
            class="inline-input"
            v-model="state"
            :fetch-suggestions="querySearch"
            placeholder="请输入内容"
            @select="handleSelect"
          ></el-autocomplete>

        </el-header>

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
            <el-tooltip class="item" effect="dark"  placement="top-start">
              <div slot="content">{{scope.row.product.name}}</div>
              <img style="width:80px;height:80px;border:none;" :src="scope.row.product.img" alt="介绍">
            </el-tooltip>

          </template>
        </el-table-column>
        <el-table-column
          prop="product.name"
          label="名称"
          width="180">
        </el-table-column>
        <el-table-column
          label="价格"
          width="180">
          <template slot-scope="scope">
            <p>${{scope.row.price}}</p>
          </template>
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
          label="数量">
          <template slot-scope="scope">
            <div class="amount_box">
              <el-input-number v-model="num[scope.row.id]"  :min="1"></el-input-number>
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="加购物车">

          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-plus" circle @click="addToCart(scope.row,num[scope.row.id])"></el-button>
          </template>

        </el-table-column>
      </el-table>
        </el-main>
      </el-container>
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
      items:[],
      searchResult:[],
      num:[],
      advice:[],
      state:''
    }
  },
  methods:{
    querySearch(queryString, cb) {
      var restaurants = this.advice;
      var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    handleSelect(item) {
        this.searchResult = []
        for (var i = 0; i < this.items.length; i++) {
          if (this.items[i].id === item.detail) {
            this.searchResult.push(this.items[i])
          }
        }
        this.items = this.searchResult
        console.log(this.searchResult)

    },
    listByProduct(){
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
    async addToCart(item, quantity) {
      if (this.$store.state.username !== '') {
        var _this = this
        var username = _this.$store.state.username
        await this.$axios.post('shopcart/update', {
          item: item,
          username: username,
          quantity: quantity
        })
        this.$message({
          message:'成功将'+item.name+' '+item.product.name+'加入购物车',
          type:'success'
        })
      } else {
        await this.$router.push('/login')
      }
    },
  },
  mounted: async function () {
    var _this = this
    var pid = 0
    var cid1 = 0;
    var url = 'categories/' + cid1 + '/products/' + pid + '/items'
    await this.$axios.get(url).then(resp => {
      if (resp && resp.status === 200) {
        _this.items = resp.data
      }
    })
    var number = new Array(28)
    for (var i = 0; i < 28; i++) {
      number[i] = 1
    }
    this.num = number

    var advice = new Array(28)
    for (i = 0; i < 28; i++) {
      advice[i] = {'value': this.items[i].product.name, 'detail': this.items[i].id}
    }
    this.advice = advice
  }


}
</script>

<style scoped>
.books-area {
  width: 990px;
  margin-left: auto;
  margin-right: auto;
}
</style>
