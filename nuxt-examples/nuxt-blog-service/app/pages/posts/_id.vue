<template>
  <section class="container post-page">
    <div style="flex:1;">
      <el-card v-if="post">
        <div slot="header" class="clearfix">
          <h2>{{ post.title }}</h2>
          <small>by {{ post.user.id }}</small>
        </div>
        <p style="white-space:break-spaces;">{{ post.body }}</p>
        <p class="text-right">
          <el-button type="warning" :disabled="!isLoggedIn" v-if="isLiked" @click="unlike" round>
            <span class="el-icon-star-on" />
            <span>{{ post.likes.length }}</span>
          </el-button>
          <el-button type="warning" :disabled="!isLoggedIn" v-else @click="like" round>
            <span class="el-icon-star-off" />
            <span>{{ post.likes.length }}</span>
          </el-button>
        </p>
        <p class="text-right">{{ post.created_at | time }}</p>
      </el-card>
      <p>
        <nuxt-link to="/posts">&lt; 投稿一覧に戻る</nuxt-link>
      </p>
    </div>
  </section>
</template>

<script>
import moment from '~/plugins/moment'
import { mapGetters, mapActions } from 'vuex'
import cloneDeep from 'lodash.cloneDeep'
export default {
  async asyncData ({ store, route }) {
    const { id } = route.params
    if (store.getters['posts/posts'].find(p => p.id === id)) {
      return
    }
    await store.dispatch('posts/fetchPost', { id })
  },
  computed: {
    ...mapGetters([ 'user', 'isLoggedIn' ]),
    ...mapGetters('posts', [ 'posts' ]),
    post () {
      return this.posts.find(p => p.id === this.$route.params.id)
    },
    isLiked () {
      if (!this.user) return false
      return this.post.likes.find(l => l.user_id === this.user.id)
    }
  },
  methods: {
    ...mapActions([ 'addLikeToUser', 'removeLikeFromUser' ]),
    ...mapActions('posts', [ 'addLikeToPost', 'removeLikeFromPost' ]),
    like () {
      if (!this.isLoggedIn) {
        return
      }
      const payload = { user: this.user, post: this.post }
      this.addLikeToPost(cloneDeep(payload))
      this.addLikeToUser(cloneDeep(payload))
    },
    unlike () {
      if (!this.isLoggedIn) {
        return
      }
      const payload = { user: this.user, post: this.post }
      this.removeLikeFromPost(cloneDeep(payload))
      this.removeLikeFromUser(cloneDeep(payload))
    }
  },
  filters: {
    time (val) {
      return moment(new Date(val).toISOString()).format('YYYY/MM/DD HH:mm:ss')
    }
  }
}
</script>

<style>
.posts-page .el-table__row {
  cursor: pointer;
}
</style>
