<template>
  <div class="me">
    <div class="main">

      <div class="aside" style="width: 240px">
        <h2 style="text-align: center;padding:10px 0;letter-spacing: 2px;font-weight: 400">个人中心</h2>
        <el-menu
            class="el-menu-vertical-demo">
          <router-link to="/me/cart">
            <el-menu-item index="1">
              <i class="el-icon-shopping-cart-1"></i>
              <span style="letter-spacing: 1px" slot="title">购物车</span>
            </el-menu-item>
          </router-link>
          <router-link to="/me/order">
            <el-menu-item index="2">
              <i class="el-icon-s-order"></i>
              <span style="letter-spacing: 1px" slot="title">我的订单</span>
            </el-menu-item>
          </router-link>
          <router-link to="/me/setting">
            <el-menu-item index="3">
              <i class="el-icon-setting"></i>
              <span style="letter-spacing: 1px" slot="title">基本设置</span>
            </el-menu-item>
          </router-link>
        </el-menu>
      </div>

      <div class="content">
        <router-view/>
      </div>

    </div>
  </div>
</template>

<script>
</script>

<style scoped>
.me {
  padding: 70px 180px;
}

.main {
  height: 900px;
  background: #FFFFFF;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
}

.aside {
  float: left;
  height: 100%;
  border-right: solid 1px #e6e6e6;
}

.el-menu {
  border-right: none;
}

.content {
  float: left;
  padding: 10px 40px;
  width: 800px;
}
</style>