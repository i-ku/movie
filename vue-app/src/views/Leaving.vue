<template>
  <div class="main">

    <el-container>

      <el-aside width="400px" v-show="false">
        <h1 style="color: #FFFFFF;padding-bottom: 40px;letter-spacing: 1px">客服人员</h1>
        <div class="item" v-for="(item, index) in workerList" :key="index">
          <img class="av" alt="" :src="item.avatar">
          <div>
            <div class="d1">名字: {{ item.nickname }}</div>
            <div class="d1">性别: {{ item.gender }}</div>
            <div class="d1">ID: {{ item.id }}</div>
            <div>
              <el-button
                  @click="handleListEvaluate(item)"
                  type="text">我的评价
              </el-button>
              <el-button
                  style="padding-left: 40px"
                  :disabled="!isLogin"
                  @click="handleEvaluate(item)"
                  type="text">评价客服
              </el-button>
            </div>
          </div>
        </div>
      </el-aside>

      <el-main>
        <h1 style="color: #FFFFFF;padding-bottom: 40px;letter-spacing: 1px">影院留言</h1>
        <div class="item2" v-for="(item, index) in leavingMessageList" :key="index">
          <div style="display: flex">
            <img class="av2" :src="item.user.avatar" alt="null">
            <div>
              <div class="d2">{{ item.user.nickname }}</div>
              <div v-if="!item.user.nickname" class="d2">匿名用户</div>
              <div class="d3">{{ item.leavingMessage.content }}</div>
              <div class="d2">Time: {{ item.leavingMessage.createAt }}</div>
            </div>
          </div>
          <div v-if="!item.leavingMessage.reply" class="reply">
            <div class="d2">客服回复:</div>
            <div class="d3">{{ item.leavingMessage.reply }}</div>
          </div>
        </div>
        <el-button :disabled="!isLogin"
                   @click="handleLeaving"
                   class="btn"
                   type="success"
                   icon="el-icon-share">
          添加留言
        </el-button>
      </el-main>
    </el-container>

    <el-dialog title="客服评价" :visible.sync="dialogFormVisible1">
      <el-form :model="evaluate">
        <el-form-item label="满意程度" label-width="120px">
          <el-select v-model="evaluate.type" placeholder="请选择满意程度">
            <el-option label="满意" value="满意"></el-option>
            <el-option label="不满意" value="不满意"></el-option>
            <el-option label="非常满意" value="非常满意"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item style="width: 100%" label="评价内容" label-width="120px">
          <el-input type="textarea" :rows="8" v-model="evaluate.content" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="submitEvaluate">提 交 评 价</el-button>
      </div>
    </el-dialog>

    <el-dialog title="添加留言" :visible.sync="dialogFormVisible2">
      <el-input type="textarea" :rows="8" v-model="leavingMessage.content" autocomplete="off"></el-input>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="submitLeaving">提 交 留 言</el-button>
      </div>
    </el-dialog>

    <el-dialog title="我的评价" :visible.sync="dialogFormVisible3">
      <el-table :data="evaluateList">
        <el-table-column property="createAt" label="日期" width="250"></el-table-column>
        <el-table-column property="content" label="评语"></el-table-column>
        <el-table-column property="type" width="150">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.type==='非常满意'" type="success">非常满意</el-tag>
            <el-tag v-if="scope.row.type==='满意'" type="info">满意</el-tag>
            <el-tag v-if="scope.row.type==='不满意'" type="danger">不满意</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

  </div>
</template>

<script>
import {findById} from "@/api/user";
import {
  CreateEvaluate,
  CreateLeavingMessage,
  ListAllLeavingMessage,
  ListAllWorker,
  ListWorkerEvaluate
} from "@/api/leaving";

export default {

  data() {
    return {
      isLogin: false,
      evaluate: {
        wid: '',
        uid: localStorage.getItem("uid"),
        type: '非常满意',
        content: '',
      },
      evaluateList: [],
      workerList: [],
      leavingMessageList: [],
      leavingMessage: {
        uid: localStorage.getItem("uid"),
        reply: 'null',
        content: '',
        createAt: '',
      },
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      dialogFormVisible3: false,
    }
  },

  mounted() {
    if (localStorage.getItem("uid")) {
      this.isLogin = true
    }
    ListAllLeavingMessage().then(res => {
      this.leavingMessageList = res.data
    })
    ListAllWorker().then(res => {
      this.workerList = res.data
    })
  },

  methods: {

    handleListEvaluate(item) {
      this.dialogFormVisible3 = true
      ListWorkerEvaluate(item.id).then(res => {
        this.evaluateList = res.data
      })
    },

    handleEvaluate(item) {
      this.dialogFormVisible1 = true
      this.evaluate.wid = item.id
    },

    handleLeaving() {
      this.dialogFormVisible2 = true
    },

    submitEvaluate() {
      CreateEvaluate(this.evaluate).then(res => {
        if (res.success) {
          this.dialogFormVisible1 = false
          this.$message({
            message: '感谢您对工作人员作出的评价',
            type: 'success'
          });
        }
      })
    },

    submitLeaving() {
      CreateLeavingMessage(this.leavingMessage).then(res => {
        if (res.success) {
          this.dialogFormVisible2 = false
          this.$message({
            message: '感谢您对小小电影留下的宝贵意见',
            type: 'success'
          });
        }
      })
    },

  },

}
</script>

<style scoped>

.main {
  color: #FFFFFF;
  padding: 40px;
}

.el-aside {
  background-color: #242930;
  color: #333;
  padding: 10px 50px 40px;
  min-height: 500px;
}

.el-main {
  background-color: #353b43;
  color: #333;
  padding: 10px 70px 50px;
  min-height: 500px;
}

.item {
  margin-bottom: 40px;
  padding-bottom: 10px;
  border-bottom: 1px dashed rgba(255, 255, 255, .3);
}

.item2 {
  padding-bottom: 60px;
}

.av {
  width: 76px;
  height: 76px;
  border-radius: 38px;
  margin-right: 20px;
  float: left;
}

.av2 {
  width: 60px;
  height: 60px;
  border-radius: 30px;
  margin-right: 20px;
  margin-top: 10px;
}

.d1 {
  letter-spacing: 2px;
  line-height: 30px;
  color: #afbac4;
  font-size: 14px;
}

.d2 {
  color: #afbac4;
  letter-spacing: 2px;
  font-size: 14px;
  line-height: 30px;
}

.d3 {
  letter-spacing: 2px;
  font-size: 15px;
  font-weight: 400;
  line-height: 30px;
  color: #FFFFFF;
}

.el-button--text {
  color: #57cc8a;
}

.btn {
  float: right;
  background: #57cc8a;
  letter-spacing: 1px;
}

.reply {
  padding-left: 75px;
  padding-top: 15px;
}

</style>