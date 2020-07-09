<template>
  <div>
    <el-row v-if="user">
      <el-col :span="6">
        <el-card class="text-center" style="margin-right:16px;">
          <div>
            <img src="//placehold.it/150x150" style="width:100%;margin-bottom:16px;border-radius:2px" alt="" />
          </div>
          <h2>
            <b>{{ user.id }}</b>
          </h2>
        </el-card>
      </el-col>
      <el-col :span="18">
        <el-card>
          <div slot="header" class="clearfix">
            <span>{{ user.id }} さんの投稿</span>
          </div>
          <el-table :data="userPosts" style="width:100%;" class="table">
            <el-table-column prop="title" label="タイトル" />
            <el-table-column prop="created_at" label="投稿日時" witdh="160"></el-table-column>
          </el-table>
        </el-card>
        <el-card>
          <div slot="header" class="clearfix">
            <span>{{ user.id }} さんがいいねした投稿</span>
          </div>
          <el-table :data="userLikePosts" style="width:100%;" class="table">
            <el-table-column prop="post.title" label="タイトル" />
            <el-table-column prop="post.user_id" label="投稿者" witdh="160"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import moment from '~/plugins/moment'
import { mapGetters } from 'vuex'
export default {
  async asyncData ({ store, route, error }) {
    const { id } = route.params
    try {
      await store.dispatch('users/fetchUser', { id })
    } catch (e) {
      error({ statusCode: 404 })
    }
  },
  computed: {
    ...mapGetters('users', [ 'users' ]),
    userPosts () {
      return Object.entries(this.user.posts).map(([id, post]) => {
        const copied = Object.assign({}, post)
        copied.created_at = moment(post.created_at).format('YYYY/MM/DD HH:mm:ss')
        return { id, ...copied }
      })
    },
    userLikePosts () {
      if (!this.user.likes.length) {
        return []
      }
      return Object.entries(this.user.likes).reverse().map(([id, like]) => {
        return { ...like }
      })
    },
    user () {
      const user = this.users.find(u => u.id === this.$route.params.id)
      if (!user) {
        return null
      }
      return Object.assign({ posts: [], likes: [] }, user)
    }
  }
}
</script>