<template>
  <div class="app">

    <el-carousel height="500px" indicator-position="outside">
      <el-carousel-item v-for="(item, index) in posterList" :key="index">
        <img alt=""
             :src="item.url"
             style="width: 100%;height: 500px">
      </el-carousel-item>
    </el-carousel>

    <div class="as">

      <div style="padding-top: 40px" class="most-expect-wrapper">
        <div class="panel">
          <div class="panel-header">
            <span class="panel-more">
              <span class="panel-arrow panel-arrow-orange"></span>
            </span>
            <span class="panel-title">
              <span class="textcolor_orange">热门榜单Top10</span>
            </span>
          </div>
          <div class="panel-content">
            <ul class="ranking-wrapper ranking-mostExpect">
              <li class="ranking-item ranking-top ranking-index-1">
                <router-link :to="'/film/info?fid=' + top1.id" target="_blank">
                  <div class="ranking-top-left">
                    <i class="ranking-top-icon"></i>
                    <img class="ranking-img  default-img" :alt="top1.name" :src="top1.cover">
                  </div>
                  <div class="ranking-top-right">
                    <div class="ranking-top-right-main">
                      <span class="ranking-top-moive-name">{{ top1.name }}</span>

                      <p class="ranking-release-time">上映时间：{{ top1.releaseTime }}</p>

                      <p class="ranking-top-wish">
                        <span class="stonefont">热度值: </span>{{ top1.hot }}
                      </p>
                    </div>
                  </div>
                </router-link>
              </li>

              <li class="ranking-item ranking-index-2">
                <router-link :to="'/film/info?fid=' + top2.id" target="_blank">
                  <i class="ranking-index">2</i>
                  <span class="img-link">
                    <img style="height: 180px" class="ranking-img default-img" :alt="top2.name" :src="top2.cover"></span>
                  <div class="name-link ranking-movie-name">{{ top2.name }}</div>
                  <span class="ranking-num-info"><span class="stonefont">热度值: </span>{{ top2.hot }}</span>
                </router-link>
              </li>

              <li class="ranking-item ranking-index-3">
                <router-link :to="'/film/info?fid=' + top3.id" target="_blank">
                  <i class="ranking-index">3</i>
                  <span class="img-link">
                    <img style="height: 180px" class="ranking-img default-img" :alt="top3.name" :src="top3.cover"></span>
                  <div class="name-link ranking-movie-name">{{ top3.name }}</div>
                  <span class="ranking-num-info"><span class="stonefont">热度值: </span>{{ top3.hot }}</span>
                </router-link>
              </li>

              <li v-for="(item, index) in topList" :key="index" class="ranking-item ranking-index-4">
                <router-link :to="'/film/info?fid=' + item.id" target="_blank">
                  <span class="normal-link">
                    <i class="ranking-index">{{ index + 4 }}</i>
                    <span class="ranking-movie-name">{{ item.name }}</span>
                    <span class="ranking-num-info"><span class="stonefont">热度值: </span>{{ item.hot }}</span>
                  </span>
                </router-link>
              </li>

            </ul>
          </div>
        </div>
      </div>

    </div>

    <div class="main">
      <div class="movie-grid">
        <div class="panel">
          <div class="panel-header">
            <span class="panel-title">
              <span class="textcolor_orange">正在热播  ( {{ filmList.length }} )</span>
            </span>
          </div>
          <div class="panel-content">
            <dl class="movie-list">
              <dd v-for="item in filmList">
                <router-link :to="'/film/info?fid=' + item.id">
                  <div class="movie-item">
                    <div class="movie-poster">
                      <img class="poster-default" alt=""
                           :src="item.cover">
                      <img class="movie-poster-img" alt="不要先生与好的女士海报封面"
                           :src="item.cover">
                      <div class="movie-overlay movie-overlay-bg">
                        <div class="movie-info">
                          <div class="movie-title" title="">{{ item.name }}</div>
                        </div>
                      </div>
                    </div>
                    <div class="movie-detail movie-wish"><span class="stonefont">123</span>人想看</div>
                    <div class="movie-ver"></div>
                  </div>
                  <div class="movie-detail movie-rt">{{ item.releaseTime }} 上映</div>
                </router-link>
              </dd>
            </dl>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import {ListAllFilm, ListAllPoster, ListHots} from "@/api/film";

export default {

  data() {
    return {
      top1: {},
      top2: {},
      top3: {},
      topList: [],
      filmList: [],
      posterList: []
    }
  },

  mounted() {
    ListAllFilm().then(res => {
      this.filmList = res.data
    })
    ListAllPoster().then(res => {
      this.posterList = res.data
    })
    ListHots().then(res => {
      this.topList = res.data
      this.top1 = this.topList[0]
      this.top2 = this.topList[1]
      this.top3 = this.topList[2]
      this.topList.splice(0, 3)
    })
  }
}
</script>

<style scoped>
@import "../assets/css/home.css";

.app {
  padding: 50px 120px;
}

.as {
  float: right;
}

.main {
  float: left;
  padding-top: 40px;
}
</style>