<template>
  <el-table
    class="books-area"
    :data="orders"
    stripe
    style="width: 100%">
    <el-table-column
      prop="name"
      label="名称"
      width="180">
    </el-table-column>
    <el-table-column
      prop="price"
      label="成交单价"
      width="180">
    </el-table-column>
    <el-table-column
      prop="quantity"
      label="购买数量">
    </el-table-column>
    <el-table-column
      prop="describe"
      label="总价格">
    </el-table-column>
    <el-table-column
      label="购买时间">
    </el-table-column>
    <el-table-column
      label="物流信息">
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
  }
}
</script>

<style scoped>

</style>
