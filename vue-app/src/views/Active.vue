<template>
  <div class="main">
    <el-timeline>
      <el-timeline-item
          style="letter-spacing: 2px"
          v-for="(item, index) in list"
          :key="index"
          :timestamp="item.startTime"
          placement="top">
        <el-card>
          <h4 class="h1">{{ item.content }}</h4>
          <h4 class="p1">活动结束时间：{{ item.endTime }}</h4>
          <el-button @click="handleRegister(item)" type="text">报名参加</el-button>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
import {ListAllActivity, SubmitActive} from "@/api/active";

export default {

  data() {
    return {
      list: [],
      form: {
        aid: '',
        uid: '',
      },
    }
  },

  mounted() {
    this.load()
  },

  methods: {

    load() {
      ListAllActivity().then(res => {
        if (res.success) {
          this.list = res.data
        }
      })
    },

    handleRegister(item) {
      if (!localStorage.getItem("uid")) {
        this.$confirm('系统还没有检测到您的登陆信息, 是否去登录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$router.push("/login")
        })
      } else {
        this.form.aid = item.id
        this.form.uid = localStorage.getItem('uid')
        SubmitActive(this.form).then(res => {
          if (res.success) {
            this.$message({
              message: '活动报名成功！',
              type: 'success'
            });
          }
        })
      }
    },

  },

}

</script>

<style scoped>
.main {
  padding: 100px;
}

.h1 {
  font-size: 18px;
}

.p1 {
  color: #666666;
  font-weight: 300;
}
</style>