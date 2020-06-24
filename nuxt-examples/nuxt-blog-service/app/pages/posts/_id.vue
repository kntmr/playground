<template>
  <section class="container post-page">
    <div style="flex:1;">
      <el-card v-if="post">
        <div slot="header" class="clearfix">
          <h2>{{ post.title }}</h2>
          <small>by {{ post.user.id }}</small>
        </div>
        <p style="white-space:break-spaces;">{{ post.body }}</p>
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
export default {
  async asyncData ({ store, route }) {
    const { id } = route.params
    if (store.getters['posts/posts'].find(p => p.id === id)) {
      return
    }
    await store.dispatch('posts/fetchPost', { id })
  },
  computed: {
    ...mapGetters('posts', [ 'posts' ]),
    post () {
      return this.posts.find(p => p.id === this.$route.params.id)
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
