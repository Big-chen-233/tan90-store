<template>
  <div>
  <el-table
    class="books-area"
    :data="items"
    stripe
    style="width: 100%">
    <el-table-column
      prop="item.product.name"
      label="名称"
      width="180">
    </el-table-column>
    <el-table-column
      prop="item.name"
      label="描述"
      width="180">
    </el-table-column>
    <el-table-column
      prop="item.price"
      label="单价"
      width="180">
      <template slot-scope="scope">
        <p>${{scope.row.item.price}}</p>
      </template>
    </el-table-column>
    <el-table-column
      label="购买数量">
      <template slot-scope="scope">
        <div class="amount_box">
          <el-input-number v-model="scope.row.quantity" @change="valChange(scope.row,)" :min="1"></el-input-number>
        </div>
      </template>
    </el-table-column>
    <el-table-column
      label="总价格">
      <template slot-scope="scope">
        <p>${{scope.row.quantity*scope.row.item.price}}</p>
      </template>
    </el-table-column>
    <el-table-column
      label="操作">
      <template slot-scope="scope">
        <el-button type="danger" icon="el-icon-delete" circle @click="remove(scope.row)"></el-button>
      </template>
    </el-table-column>
  </el-table>

  <div style="float: right">
    <br>
    <p style="color: crimson;font-size: 20px">${{sum}}</p>
    <br>
    <el-button @click="updateItems" style="background-color: lightsalmon;color: white">结算</el-button>
  </div>
  </div>
</template>

<script>
export default {
  name: "ShopCart",
  data() {
    return {
      items: [],
      sum:0
    }
  },
  async created() {
    const _this = this;
    await this.$axios.post('shopcart/getList', _this.$store.state.username)
      .then(resp => {
        if (resp && resp.status === 200) {
          console.log(resp.data)
          this.items = resp.data
        }
      })
      this.calculate()
  },
  methods: {
    remove(item) {
      this.$axios.post('shopcart/delete', item)
        .then(resp => {
          if (resp && resp.status === 200) {
            console.log("ok")
          }
        })
      this.$router.go(0)
    },
    updateItems(){
      const _this = this;
      this.$axios.post('shopcart/buy', _this.$store.state.username)
        .then(resp => {
          if (resp && resp.status === 200) {

          }
        })

      this.$router.go(0)
    },
    valChange(shopCart){
      this.$nextTick(() => {
        this.changeValue(shopCart)
      })
    },
    changeValue(shopCart){
      this.$axios.post('shopcart/valChange', shopCart)
        .then(resp => {
          if (resp && resp.status === 200) {
            console.log("ok")
          }
        })
      this.calculate()
    },
    calculate(){
      this.sum = 0
      for(var i=0;i<this.items.length;i++){
        console.log("------")
        console.log(this.items[i])
        this.sum+=this.items[i].quantity*this.items[i].item.price
      }
    }

  }

}
</script>

<style scoped>

</style>
