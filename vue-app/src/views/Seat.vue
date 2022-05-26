<template>
  <div class="seat-main">
    <el-steps style="padding-bottom: 50px" :active="2" align-center>
      <el-step title="步骤1" description="选择电影场次"></el-step>
      <el-step title="步骤2" description="选择座位号"></el-step>
      <el-step title="步骤3" description="加入购物车"></el-step>
      <el-step title="步骤4" description="结算付款"></el-step>
    </el-steps>
    <div class="seat-content">
      <div class="seat-aside">
        <div style="display: flex">
          <img style="padding: 30px" :src="film.cover" alt=""/>
          <div>
            <div
                style="
                padding-top: 40px;
                padding-bottom: 10px;
                font-size: 25px;
                letter-spacing: 2px;
              "
            >
              {{ film.name }}
            </div>
            <div class="seat-aside-text">类型：{{ film.type }}</div>
            <div class="seat-aside-text">地区：{{ film.region }}</div>
            <div class="seat-aside-text">时长：{{ film.duration }}分钟</div>
          </div>
        </div>
        <div style="padding: 5px 30px">
          <div class="d1"><span>放映：</span>{{ arrangement.type }}</div>
          <div class="d1" style="color: #f56c6c">
            <span>开场：</span>{{ arrangement.date }}
            {{ arrangement.startTime }}
          </div>
          <div class="d1"><span>散场：</span>{{ arrangement.endTime }}</div>
          <div class="d1"><span>票价：</span>¥{{ arrangement.price }}/张</div>
          <el-divider></el-divider>
          <div class="d1">
            已选座位：
            <el-tag v-for="(item, i) in userSelectSeats" :key="i"
                    type="danger"
                    style="margin-right: 5px"
                    effect="plain">
              {{ item }} 号
            </el-tag>
          </div>
          <div class="d1" style="padding-top: 10px">
            总计：
            <span style="color: #f56c6c">¥ </span>
            <span style="color: #f56c6c; font-size: 25px; font-weight: bold"
            >{{ cart.price }}</span
            >
          </div>
          <el-divider></el-divider>
          <div style="padding: 0 50px">
            <el-input
                v-model="cart.phone"
                style="padding-top: 20px; padding-bottom: 30px"
                placeholder="请输入手机号码"
                clearable
            >
            </el-input>
            <el-button @click="submitSeat" class="add-cart-btn" type="danger" round
            >加入购物车
            </el-button>
          </div>
        </div>
      </div>

      <div class="hall seat-select">
        <div style="padding-left: 30px" class="seat-example">
          <div class="selectable-example example">
            <span>可选座位</span>
          </div>
          <div class="sold-example example">
            <span>已售座位</span>
          </div>
          <div class="selected-example example">
            <span>已选座位</span>
          </div>
        </div>

        <div class="seats-block">
          <div class="seats-container">
            <div class="screen-container" style="left: 5px">
              <div class="screen">银幕中央</div>
              <div class="c-screen-line"></div>
            </div>

            <div class="seats-wrapper">
              <div style="padding: 0 40px;width: 500px">
                <span v-for="(item, index) in seats" :key="index">
                  <!--不可选-->
                  <span v-if="item.status === 0" class="seat sold item"/>
                  <!--可选-->
                  <span v-if="item.status === 1" @click="handleSelect(index)" class="seat selectable item"/>
                  <!--已选-->
                  <span v-if="item.status === 2" @click="handleDisSelect(index)" class="seat selected item"/>
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {FindArrangementById, GetArrangementSeats} from "@/api/film";
import {CreateCart} from "@/api/cart";

export default {
  data() {
    return {
      cart: {
        uid: localStorage.getItem("uid"),
        aid: this.$route.query.id,
        seats: '',
        phone: "",
        price: 0
      },
      arrangement: {},
      film: {},
      seats: [],
      userSelectSeats: [],
      selectSeats: [],
    }
  },

  mounted() {
    FindArrangementById(this.$route.query.id).then((res) => {
      this.arrangement = res.data.arrangement;
      this.film = res.data.film;
      GetArrangementSeats(this.$route.query.id).then(res => {
        this.selectSeats = res.data;
        this.loadSeats();
      })
    });
  },

  methods: {
    //0 已选
    //1 可选
    //2 已选中
    loadSeats() {
      const n = this.arrangement.seatNumber
      let arr = new Array(n)
      for (let i = 0; i < n; i++) {
        if (this.selectSeats.indexOf(i + 1) === -1) {
          arr[i] = {seat: i + 1, status: 1}
        } else {
          arr[i] = {seat: i + 1, status: 0}
        }
      }
      this.seats = arr
    },

    handleSelect(index) {
      if (this.userSelectSeats.length >= 4) {
        let d = this.userSelectSeats[0] - 1
        this.seats[d].status = 1
        this.userSelectSeats.splice(0, 1)
      }
      this.userSelectSeats.push(index + 1)
      this.seats[index].status = 2
      this.cart.price = (this.arrangement.price) * (this.userSelectSeats.length)
    },

    handleDisSelect(index) {
      this.seats[index].status = 1
      this.userSelectSeats.splice(this.userSelectSeats.indexOf(index + 1), 1)
      this.cart.price = (this.arrangement.price) * (this.userSelectSeats.length)
    },

    checkPhoneAndSeats() {
      if (this.userSelectSeats.length === 0) {
        this.$message({
          message: '请选择要订购的座位',
          type: 'warning'
        });
        return false;
      }
      if (this.cart.phone.length !== 11) {
        this.$message({
          message: '请输入11位的电话号码',
          type: 'warning'
        });
        return false;
      }
      return true;
    },

    submitSeat() {
      if (this.checkPhoneAndSeats()) {
        for (let i = 0; i < this.userSelectSeats.length; i++) {
          this.cart.seats += this.userSelectSeats[i] + '号'
        }
        console.log(this.cart)
        CreateCart(this.cart).then(res => {
          this.$message({
            message: '以为您添加购物车成功，请尽快付款吧',
            type: 'success'
          });
          this.$router.go(-1)
        })

      }
    },

  },
};
</script>

<style scoped>
@import "../assets/css/seat.css";

.seat-main {
  padding: 80px;
}

.seat-content {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  height: 850px;
}

.seat-aside {
  width: 30%;
  height: 100%;
  float: left;
  background: #f5f6f7;
}

.seat-aside img {
  width: 140px;
  height: 200px;
}

.seat-select {
  padding-top: 50px;
  padding-left: 8%;
}

.seat-aside-text {
  padding-top: 8px;
  font-size: 13px;
  letter-spacing: 1px;
  color: #666666;
}

.d1 {
  font-size: 16px;
  letter-spacing: 2px;
  padding-bottom: 15px;
}

.d1 span {
  color: #91949c;
}

.add-cart-btn {
  width: 100%;
  height: 60px;
  border-radius: 50px;
}

.item {
  padding-bottom: 20px;
}

.row-id {
  padding-bottom: 11px;
}

>>> .el-input__inner {
  border-radius: 50px;
  height: 55px;
}
</style>