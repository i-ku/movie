<template>
    <div class="login">
        <div class="login-form">
            <div class="login-form-header">
                <img style="width: 75px; height: 75px;float: left;padding-right: 40px;" src="../assets/img/logo.png"
                     alt=""/>
                <div class="login-form-text">小小电影 - 登录</div>
            </div>
            <div style="color: #91949c;font-weight: bolder">
                <p>Username</p>
                <el-input class="login-form-input" v-model="username" placeholder="账 号"></el-input>
                <p>Password</p>
                <el-input class="login-form-input" placeholder="密 码" v-model="password" show-password></el-input>
                <div style="padding-top: 10px">
                    <el-checkbox v-model="remember">记住密码</el-checkbox>
                    <div style="float: right">
                        <el-link href="/forget" style="font-weight: bolder;font-size: 14px;color: #91949c;"
                                 :underline="false">
                            忘记密码?
                        </el-link>
                    </div>
                </div>
                <el-button @click="postLogin" class="login-form-button" type="primary">SIGN IN</el-button>
            </div>
            <div class="login-form-footer">
                <el-link href="/register" style="font-weight: bolder;font-size: 16px;color: #91949c;"
                         :underline="false">
                    还没有账号？去注册
                    <i style="font-weight: bolder;font-size: 15px" class="el-icon-right"></i>
                </el-link>
            </div>
        </div>
    </div>
</template>

<script>

    import {Login} from "../api/user"

    export default {
        data() {
            return {
                username: '',
                password: '',
                remember: false,
            }
        },
        methods: {
            postLogin() {
                const LoginData = {
                    username: this.username,
                    password: this.password,
                    remember: this.remember
                };
                console.log(LoginData);
                Login(LoginData).then(res => {
                    localStorage.setItem("uid", res.data.user.id);
                    localStorage.setItem("token", res.data.token);
                    this.$router.push({path: '/', replace: true})
                })
            }
        }
    }
</script>

<style scoped>

    .login {
        width: 100%;
        height: 100%;
        background: #FFFFFF;
    }

    .login-form {
        width: 500px;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -60%);
        letter-spacing: 2px;
    }

    .login-form-header {
        height: 20px;
        padding-left: 45px;
        padding-bottom: 100px;
    }

    .login-form-text {
        color: #000000;
        font-weight: bold;
        font-size: 30px;
        padding-top: 15px;
    }

    .login-form-input {
        margin-bottom: 10px;
    }

    .login-form-button {
        border-radius: 3px;
        width: 100%;
        font-weight: 600;
        font-size: 15px;
        letter-spacing: 2px;
        height: 60px;
        background: #5a84fd;
        box-shadow: 0 5px 30px rgb(0 66 8.5%);
        margin-top: 35px;
    }

    .login-form-footer {
        font-weight: bolder;
        color: #91949c;
        padding-top: 40px;
        text-align: center;
    }

    >>> .el-input__inner {
        height: 48px;
    }

    .el-checkbox {
        color: #91949c;
        font-weight: bolder;
        font-size: 15px;
    }

</style>
