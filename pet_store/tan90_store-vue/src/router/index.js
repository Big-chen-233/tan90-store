import Vue from 'vue'
import Router from 'vue-router'
import Login from "../components/Login";
import Register from "../components/Register";
import Market from "../components/Market";
import Exhibition from "../components/market/birds/Exhibition";
import UserHome from "../components/home/UserHome";
import FinishInfo from "../components/FinishInfo";
import Admin from "../components/home/childrenPage/Admin";
import ShopCart from "../components/home/childrenPage/ShopCart";
import ItemOrder from "../components/home/childrenPage/ItemOrder";



Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [
    {
      path: '/',
      name: 'Market',
      component: Market,
      redirect: '/market',
      children: [
        {
          path:'/market',
          name: 'Market',
          component: Exhibition
        },
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/finish',
      name: 'FinishInfo',
      component: FinishInfo
    },
    {
      path: '/home',
      name:'UserHome',
      component: UserHome,
      redirect: '/admin',
      children: [
        {
          path: '/admin',
          name: 'Admin',
          component: Admin,

        },
        {
          path: '/shopCart',
          name: 'ShopCart',
          component: ShopCart,

        },
        {
          path: '/itemOrder',
          name: 'ItemOrder',
          component: ItemOrder,

        }
      ]
    }

  ]
})
