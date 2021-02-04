<template>
  <el-table
    class="books-area"
    :data="orders"
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
      label="成交单价"
      width="180">
      <template slot-scope="scope">
        <p>${{scope.row.item.price}}</p>
      </template>
    </el-table-column>
    <el-table-column
      prop="quantity"
      label="购买数量">
    </el-table-column>
    <el-table-column
      label="总价格">
      <template width="90" slot-scope="scope">
        <p>${{scope.row.item.price*scope.row.quantity}}</p>
      </template>
    </el-table-column>
    <el-table-column
      label="物流信息">
      等待卖家发货
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "ItemOrder",
  data(){
    return {
      orders:[]
    }
  },
  async created() {
    const _this = this;
    await this.$axios.post('shopcart/getOrder', _this.$store.state.username)
      .then(resp => {
        if (resp && resp.status === 200) {
          this.orders = resp.data
        }
      })
    console.log(this.orders)
  },

}
</script>

<style scoped>

</style>
