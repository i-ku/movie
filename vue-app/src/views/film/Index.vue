<template>
  <div class="film">

    <div class="film-header">
    </div>

    <div class="film-card">
      <img alt="" style="width: 100%;height: 100%" :src="film.cover">
    </div>

    <div class="film-des">
      <div class="p1">{{ film.name }}
        <el-rate style="float: right;padding-left: 50px"
                 v-model="value"
                 disabled
                 show-score
                 text-color="#ff9900"
                 score-template="{value}">
        </el-rate>
      </div>
      <div class="p2">电影类型 : {{ film.type }}</div>
      <div class="p2">{{ film.region }} / {{ film.duration }}分钟</div>
      <div class="p2">上映日期 : {{ film.releaseTime }} 上映</div>
      <div style="padding-top: 30px">
        <router-link style="margin-right: 20px" :to="'/film/ticket?fid=' + filmId">
          <el-button type="danger" style="width: 130px;letter-spacing: 2px">
            <i style="padding-right: 5px;font-size: 15px" class="el-icon-s-finance"></i>特惠购票
          </el-button>
        </router-link>
        <el-button @click="openComment" type="danger" style="width: 130px;letter-spacing: 2px">
          <i style="padding-right: 5px;font-size: 15px" class="el-icon-star-on"></i>评分
        </el-button>
      </div>
    </div>

    <div class="film-content">
      <router-view />
    </div>

    <el-dialog
        :show-close=false
        title="电影评分"
        :visible.sync="dialogVisible"
        width="30%">
      <div class="block">
        <el-rate
            v-model="form.star"
            :colors="colors">
        </el-rate>
      </div>
      <el-input
          style="margin-top: 40px"
          type="textarea"
          :rows="8"
          placeholder="请输入内容"
          v-model="form.comment">
      </el-input>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitComment">确 认 提 交</el-button>
             </span>
    </el-dialog>

  </div>
</template>

<script>
import {AddFilmEvaluate, FindFilmById, FindFilmEvaluateByFId} from "@/api/film";

export default {
  name: "Film",
  data() {
    return {
      value2: null,
      colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
      filmId: this.$route.query.fid,
      value: 3.5,
      list: [{}, {}, {}],
      dialogVisible: false,
      film: {},
      evaluateList: [],
      form: {
        fid: '',
        uid: '',
        star: 5,
        comment: '',
      },
    }
  },

  methods: {

    openComment() {
      if (!localStorage.getItem("uid")) {
        this.$confirm('系统还没有检测到您的登陆信息, 是否去登录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$router.push("/login")
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消为电影评分'
          });
        });
      } else {
        this.dialogVisible = true
      }
    },

    submitComment() {
      this.form.fid = this.filmId;
      this.form.uid = localStorage.getItem("uid");
      const form = this.form
      AddFilmEvaluate(form).then(res => {
        this.dialogVisible = false;
      })
    },

  },

  mounted() {
    FindFilmById(this.filmId).then(res => {
      this.film = res.data;
    })
  }
}
</script>

<style scoped>

.film-header {
  position: relative;
  width: 100%;
  height: 380px;
  background: #5a84fd;
  margin-top: 12px;
}

.film-content {
  padding: 80px 200px;
  letter-spacing: 2px;
}

.p1 {
  font-weight: bolder;
  letter-spacing: 3px;
  color: #FFFFFF;
  font-size: 30px;
  padding-top: 20px;
  padding-bottom: 10px;
}

.p2 {
  font-size: 14px;
  padding-top: 20px;
  letter-spacing: 2px;
  color: #FFFFFF;
}

.film-card {
  width: 240px;
  height: 330px;
  background: #FFFFFF;
  margin-right: 100px;
  margin-left: 190px;
  position: absolute;
  overflow: hidden;
  top: 160px;
  z-index: 999;
}

.film-des {
  margin-left: 500px;
  position: absolute;
  overflow: hidden;
  top: 150px;
  z-index: 999;
}

>>> .el-rate__icon {
  font-size: 25px;
}

>>> .el-rate__text {
  padding-left: 8px;
  font-size: 30px;
}

</style>
