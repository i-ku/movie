<template>
  <div>
    <h3 style="letter-spacing: 1px;font-weight: 400;padding-bottom: 20px">基本设置</h3>

    <div>
      <el-form style="width: 350px;float: left" label-position="top" ref="form" :model="user" label-width="80px">
        <el-form-item style="padding: 0" label="昵称">
          <el-input v-model="user.nickname"></el-input>
        </el-form-item>
        <el-form-item style="padding: 0" label="密码">
          <el-input type="password" v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="user.gender">
            <el-radio label="男生"></el-radio>
            <el-radio label="女生"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="出生年月">
          <el-col>
            <el-date-picker
                type="date" placeholder="选择日期"
                v-model="user.birthday"
                value-format="yyyy-MM-dd"
                style="width: 100%;"/>
          </el-col>
        </el-form-item>
        <el-form-item style="padding: 0" label="邮箱">
          <el-input type="email" v-model="user.email"></el-input>
        </el-form-item>
        <el-form-item label="个人简介">
          <el-input :rows="5" type="textarea" v-model="user.info"></el-input>
        </el-form-item>
        <el-form-item style="padding-top: 20px">
          <el-button type="primary" @click="onSubmit">更新基本信息</el-button>
        </el-form-item>
      </el-form>
      <div>
        <img style="padding-bottom: 10px;padding-left: 150px;width: 150px; height: 150px;" alt=""
             :src="user.avatar">
        <el-upload
            style="padding-left: 500px;letter-spacing: 1px"
            class="upload-demo"
            accept=".png,.jpg"
            :headers="header"
            :action="uploadAction"
            :on-success="handleUploadSuccess"
            multiple
            :limit="1">
          <el-button style="width: 150px" size="small" type="primary">
            <i class="el-icon-upload2"></i> 点击上传
          </el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件</div>
        </el-upload>
      </div>
    </div>
  </div>
</template>

<script>
import {findById, updateUser} from "@/api/user";
import config from "@/config";

export default {

  data() {
    return {
      header: {
        "Authorization": localStorage.getItem("token")
      },
      uploadAction: config.API_URL + '/upload',
      user: {
        nickname: '',
        password: '',
        email: '',
        birthday: '',
        gender: '',
        info: '',
        avatar: ''
      }
    }
  },

  mounted() {
    if (localStorage.getItem("uid") !== null) {
      findById(localStorage.getItem("uid")).then(res => {
        this.user = res.data;
      })
    }
  },

  methods: {

    onSubmit() {
      updateUser(this.user).then(res => {
        if (res.success) {
          this.user = res.data;
          this.$message({
            type: 'success',
            message: '用户基本信息更新成功!'
          });
        }
      })
    },

    handleUploadSuccess(res) {
      this.user.avatar = res;
      updateUser(this.user).then(res => {
        if (res.success) {
          this.user = res.data;
          this.$message({
            type: 'success',
            message: '头像上传成功!'
          });
        }
      })
    }

  },

}

</script>

<style scoped>
>>> .el-form--label-top .el-form-item__label {
  padding: 0;
}

.el-form-item {
  margin-bottom: 5px;
}
</style>