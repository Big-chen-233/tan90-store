<template>
  <el-table
    class="books-area"
    :data="items"
    stripe
    style="width: 100%">
    <el-table-column
      label="全选"
      width="180">
    </el-table-column>
    <el-table-column
      prop="item.name"
      label="名称"
      width="180">
    </el-table-column>
    <el-table-column
      prop="item.price"
      label="单价"
      width="180">
    </el-table-column>
    <el-table-column
      prop="quantity"
      label="购买数量">
    </el-table-column>
    <el-table-column
      label="总价格">
    </el-table-column>
    <el-table-column
      label="操作">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "ShopCart",
  data(){
    return {
      items:[]
    }
  },
  async created() {
    const _this = this;
    await this.$axios.get('shopcart/getList', _this.$store.state.username)
    .then(resp=>{
      if (resp && resp.status === 200) {
        _this.items = resp.data
      }
    })
  }
}
</script>

<style scoped>

</style>
