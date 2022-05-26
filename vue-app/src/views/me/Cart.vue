<template>
    <div style="width: 100%">
        <h3 style="letter-spacing: 1px;font-weight: 400;padding-bottom: 20px">购物车</h3>

        <div v-loading="loading">

            <el-card v-for="(item, index) in cartList" :key="index" class="box-card">
                <div>
                    <div style="float: left;line-height: 100px;padding-right: 20px">
                        <el-checkbox @change="handleCheck" v-model="selectList[index].checked"></el-checkbox>
                    </div>
                    <img class="item-film-img" alt="" src="../../assets/img/film.png"/>
                    <div style="float: left;">
                        <div class="item-film-name">《{{ item.film.name }}》</div>
                        <div class="item-film-seat">座位 : {{ item.cart.seats }}</div>
                        <div class="item-film-seat">手机号码 : {{ item.cart.phone }}</div>
                        <div class="item-film-time">开场时间 :
                            <span style="color: red">{{ item.arrangement.date }} - {{ item.arrangement.startTime }}</span>
                        </div>
                    </div>
                    <div style="float: right;color: #f34d41;letter-spacing: 2px; line-height: 100px;padding-right: 5px">
                        ￥{{ item.cart.price }}
                        <el-button @click="handleDelete(index)"
                                   type="text"
                                   style="color: red;padding-left: 50px"
                                   icon="el-icon-delete">删除
                        </el-button>
                    </div>
                </div>
            </el-card>

            <div v-if="cartList.length !==0" style="height: 60px;background: #91949c">
                <div>
                    <div style="float: left;line-height: 60px;padding: 0 20px;font-size: 14px">
                        <el-checkbox @change="handleCheckAll" v-model="checkAll">全 选</el-checkbox>
                    </div>
                    <el-button @click="submitCart" type="text" class="sub-btn">全部结算</el-button>
                    <div class="order-footer1">合计 : ￥{{ price }}</div>
                </div>
            </div>
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
    import {DeleteCartById, ListCarts} from "@/api/cart"
    import {CreateOrder} from "@/api/order";

    export default {
        data() {
            return {
                i: 0,
                success: false,
                centerDialogVisible: false,
                loading: false,
                price: 0,
                selectList: [],
                checkAll: false,
                cartList: [],
                uid: localStorage.getItem("uid"),
            }
        },

        mounted() {
            this.loadCarts()
        },

        methods: {

            loadCarts() {
                this.loading = true;
                ListCarts(this.uid).then(res => {
                    setTimeout(() => {
                        this.cartList = res.data
                        for (let i = 0; i < this.cartList.length; i++) {
                            this.selectList[i] = {checked: false, cart: this.cartList[i].cart}
                        }
                        this.loading = false
                    }, 700)
                })
            },

            handleCheck() {
                this.checkOut()
            },

            handleCheckAll() {
                if (this.checkAll) {
                    this.changeAllChecked(true)
                    this.checkOut()
                } else {
                    this.changeAllChecked(false)
                    this.price = 0
                }
            },

            changeAllChecked(status) {
                for (let i = 0; i < this.selectList.length; i++) {
                    this.selectList[i].checked = status
                }
            },

            checkOut() {
                this.price = 0
                for (let i = 0; i < this.selectList.length; i++) {
                    if (this.selectList[i].checked) {
                        this.price += this.selectList[i].cart.price
                    }
                }
            },

            handleDelete(index) {
                DeleteCartById(this.cartList[index].cart.id).then(res => {
                    console.log(res)
                    if (res.success) {
                        this.cartList.splice(index, 1)
                    }
                })
            },

            submitCart() {
                this.$confirm('请您仔细确认订单金额为' + this.price + '元, 是否继续?', '提示', {
                    confirmButtonText: '确认支付',
                    cancelButtonText: '取消支付',
                    type: 'success',
                    center: true
                }).then(() => {
                    for (let i = 0; i < this.selectList.length; i++) {
                        if (this.selectList[i].checked) {
                            this.selectList[i].cart.status = 2
                            this.centerDialogVisible = true
                            this.i = i
                        }
                    }
                }).catch(() => {
                    for (let i = 0; i < this.selectList.length; i++) {
                        if (this.selectList[i].checked) {
                            this.selectList[i].cart.status = 0
                            CreateOrder(this.selectList[i].cart).then(res => {
                                if (res.success) {
                                    DeleteCartById(this.selectList[i].cart.id)
                                    this.$message({
                                        type: 'warning',
                                        message: '用户已取消支付, 请您前往我的订单进行支付'
                                    });
                                    this.loadCarts()
                                }
                            })
                        }
                    }
                });
            },

            submitPay() {
                const i = this.i
                CreateOrder(this.selectList[i].cart).then(res => {
                    if (res.success) {
                        this.centerDialogVisible = false
                        DeleteCartById(this.selectList[i].cart.id)
                        this.$message({
                            type: 'success',
                            message: '恭喜你支付成功!'
                        });
                        this.loadCarts()
                    }
                })
            },

        },

    }
</script>

<style scoped>

    .box-card {
        margin-bottom: 5px;
    }

    >>> .el-card__header {
        background: #C0C4CC;
    }

    .item-film-img {
        float: left;
        width: 80px;
        height: 100px;
        padding-bottom: 20px;
        padding-right: 10px;
    }

    .item-film-name {
        letter-spacing: 2px;
        font-weight: 500;
        font-size: 18px;
        padding-bottom: 13px;
    }

    .item-film-seat {
        letter-spacing: 1px;
        font-size: 12px;
        padding-bottom: 8px;
        padding-left: 10px;
        color: #91949c;
    }


    .item-film-time {
        font-size: 12px;
        padding-left: 10px;
        letter-spacing: 1px;
        color: #91949c;
    }

    .c-img {
        width: 100%;
        height: 100%;
    }

    .sub-btn {
        float: right;
        color: #000000;
        height: 60px;
        width: 120px;
        background: #f34d41;
        letter-spacing: 2px;
        line-height: 33px;
        text-align: center;
        font-weight: 500;
        font-size: 18px;
    }

    .order-footer1 {
        float: right;
        line-height: 60px;
        padding-right: 40px;
    }

    a {
        color: #333333;
    }

    .di {
        text-align: center;
        font-size: 20px;
        letter-spacing: 3px;
        font-weight: bold;
        padding-top: 10px;
    }
</style>