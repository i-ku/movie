<template>
  <div style="width: 100%">
    <h3 style="letter-spacing: 1px;font-weight: 400;padding-bottom: 20px">我的订单</h3>

    <div v-loading="loading">
      <el-card v-for="(item, index) in orderList" :key="index" class="box-card">
        <div>
          <img class="item-film-img" alt="" :src="item.film.cover"/>
          <div style="float: left;">
            <div class="item-film-name">《{{ item.film.name }}》</div>
            <div class="item-film-seat">订购座位 : {{ item.order.seats }}</div>
            <div class="item-film-time">下单时间 : {{ item.order.createAt }}</div>
            <div v-if="item.order.status === 2" class="item-film-time">支付时间 : {{ item.order.payAt }}</div>
          </div>
          <el-button type="text"
                     @click="handlePay(item.order, index)"
                     v-if="item.order.status === 0"
                     style="line-height: 75px"
                     class="o1">
            等待支付
          </el-button>
          <el-tooltip class="item" effect="dark" content="请联系工作人员查询您的订单" placement="top">
            <div v-if="item.order.status === 3" style="color: #E6A23C" class="o1">订单异常</div>
          </el-tooltip>
          <div v-if="item.order.status === 2" style="color: #67C23A" class="o1">支付成功</div>
          <div v-if="item.order.status === 1" style="color: #F56C6C" class="o1">订单超时</div>
          <div class="o2">￥{{ item.order.price }}</div>
        </div>
      </el-card>
    </div>

    <el-dialog
        title="扫码支付"
        :show-close="false"
        width="30%"
        :visible.sync="centerDialogVisible"
    >
      <div>
        <img class="c-img" src="../../assets/img/c.jpeg" alt="">
      </div>
      <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="submitPay">支 付 成 功</el-button>
  </span>
    </el-dialog>

  </div>
</template>

<script>
import {FindOrderByUser, PayForOrder} from "@/api/order";

export default {
  name: "Order",

  data() {
    return {
      centerDialogVisible: false,
      loading: false,
      orderList: [],
      payOrderId: '',
    }
  },

  mounted() {
    this.loadOrder()
  },

  methods: {

    loadOrder() {
      this.loading = true
      FindOrderByUser(localStorage.getItem("uid")).then(res => {
        setTimeout(() => {
          this.orderList = res.data
          this.loading = false
        }, 700)
      })
    },

    handlePay(order, index) {
      this.open(order, index);
    },

    open(order, index) {
      this.$confirm('请您仔细确认订单金额为' + order.price + '元, 是否继续?', '提示', {
        confirmButtonText: '确认支付',
        cancelButtonText: '取消支付',
        type: 'success',
        center: true
      }).then(() => {
        this.loading = true
        this.centerDialogVisible = true
        this.payOrderId = order.id
      }).catch(() => {
        this.$message({
          type: 'warning',
          message: '用户已取消支付'
        });
      });
    },

    submitPay() {
      PayForOrder(this.payOrderId).then(res => {
        this.loadOrder();
        this.centerDialogVisible = false
        if (res.success) {
          this.$message({
            type: 'success',
            message: '恭喜你支付成功!'
          });
        }
      })
    },

  },

}
</script>

<style scoped>

.box-card {
  margin-bottom: 10px;
}

>>> .el-card__header {
  background: #C0C4CC;
}

.item-film-img {
  float: left;
  width: 80px;
  height: 100px;
  padding-bottom: 10px;
  padding-right: 10px;
}

.item-film-name {
  letter-spacing: 2px;
  font-weight: 500;
  font-size: 18px;
  padding-bottom: 15px;
  padding-top: 1px;
}

.c-img {
  width: 100%;
  height: 100%;
}

.item-film-seat {
  letter-spacing: 1px;
  font-size: 12px;
  padding-bottom: 5px;
  padding-left: 10px;
  color: #91949c;
}


.item-film-time {
  letter-spacing: 1px;
  font-size: 12px;
  padding-left: 10px;
  padding-bottom: 5px;
  color: #91949c;
}

.o1 {
  float: right;
  line-height: 100px;
  padding-right: 20px
}

.o2 {
  font-size: 20px;
  float: right;
  line-height: 100px;
  padding-right: 100px
}
</style>