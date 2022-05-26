<template>
  <div class="films">
    <div class="tags-panel">
      <ul class="tags-lines">
        <li class="tags-line">
          <div class="tags-title">类型 :</div>
          <ul class="tags">
            <li v-for="item in typeList" :class="selectType===item ? 'active' : ''" @click="handleTypeSelect(item)">
              <router-link :to=" '/films?region='+ selectRegion + '&type=' + item ">{{ item }}</router-link>
            </li>
          </ul>
        </li>
        <li class="tags-line">
          <div class="tags-title">地区 :</div>
          <ul class="tags">
            <li v-for="item in regionList" :class="selectRegion===item ? 'active' : ''"
                @click="handleRegionSelect(item)">
              <router-link :to=" '/films?region='+ item + '&type=' + selectType ">{{ item }}</router-link>
            </li>
          </ul>
        </li>
      </ul>
    </div>
    <div>
      <el-row :gutter="20" style="padding-top: 60px;">
        <el-col style="padding-bottom: 40px;text-align: center;" v-for="(item, index) in filmList" :key="index"
                :span="4">
          <router-link :to=" '/film/info?fid=' + item.id">
            <el-card shadow="hover" style="padding: 0">
              <img style="width: 100%; height: 300px;padding-bottom: 10px" :src="item.cover"
                   alt="">
              <span class="s">{{ item.name }}</span>
            </el-card>
          </router-link>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>

import {FindFilmByRegionAndType, ListAllFilm} from "@/api/film";

export default {

  name: "Films",

  data() {
    return {
      selectType: '全部',
      selectRegion: '全部',
      typeList: ["全部", "家庭", "惊悚", "科幻", "爱情", "动作", "喜剧", "恐怖", "悬疑", "犯罪", "冒险", "战争", "历史", "武侠", "传记", "古装", "其他"],
      regionList: ["全部", "内地", "香港", "台湾", "美国", "韩国", "日本", "泰国", "印度", "法国", "英国", "德国", "其他"],
      filmList: [],
    }
  },

  mounted() {
    ListAllFilm().then(res => {
      this.filmList = res.data
    })
  },


  methods: {

    handleTypeSelect(item) {
      this.selectType = item;
      this.reloadFilmList()
    },

    handleRegionSelect(item) {
      this.selectRegion = item;
      this.reloadFilmList()
    },

    reloadFilmList() {
      FindFilmByRegionAndType(this.selectRegion, this.selectType).then(res => {
        this.filmList = res.data
      })
    },

  },

}
</script>

<style scoped>
@import "../assets/css/movie-list.css";

.films {
  padding: 20px 220px;
}

>>> .el-card__body {
  padding: 0 0 10px;
}

.s {
  margin-bottom: 10px;
  padding: 0 8px;
  letter-spacing: 1px;
  color: coral;
  text-align: center;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

</style>
