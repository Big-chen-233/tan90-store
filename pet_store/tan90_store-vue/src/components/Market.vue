<template>
  <div>
    <nav-menu @indexSelect = "listByCategory" ref="navMenu"  ></nav-menu>
    <router-view/>
  </div>
</template>

<script>
import NavMenu from './common/NavMenu'
export default {
  name: "Market",
  components: {NavMenu},
  data(){
    return {
      products:[]
    }
  },
  methods:{
    listByCategory(){
      var _this = this
      var cid = this.$refs.navMenu.cid
      var url = 'categories/'+cid+'/products'
      this.$axios.get(url).then(resp => {
        if (resp && resp.status === 200) {
          this.products = resp.data
          this.$store.commit('setProducts',this.products)
        }
      })
    }
  },
  mounted:function () {
    var _this = this
    var cid = 0
    var url = 'categories/'+cid+'/products'
    this.$axios.get(url).then(resp => {
      if (resp && resp.status === 200) {
        this.products = resp.data
        this.$store.commit('setProducts',this.products)
      }
    })
  }
}
</script>

<style scoped>

</style>
