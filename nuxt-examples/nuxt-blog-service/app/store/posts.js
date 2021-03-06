import moment from '~/plugins/moment'

export const state = () => ({
  posts: []
})

export const getters = {
  posts: state => state.posts.map(post => Object.assign({ likes: [] }, post))
}

export const mutations = {
  addPost (state, { post }) {
    state.posts.push(post)
  },
  updatePost (state, { post }) {
    state.posts = state.posts.map(p => p.id === post.id ? post : p)
  },
  clearPosts (state) {
    state.posts = []
  }
}

export const actions = {
  async fetchPost ({ commit }, { id }) {
    const post = await this.$axios.$get(`/posts/${id}.json`)
    commit('addPost', {
      post: {
        id,
        ...post
      }
    })
  },
  async fetchPosts ({ commit }) {
    const posts = await this.$axios.$get('/posts.json')
    commit('clearPosts')
    Object.entries(posts || [])
      .reverse()
      .forEach(([ id, content ]) => {
        commit('addPost', {
          post: {
            id,
            ...content
          }
        })
      })
  },
  async publishPost({ commit }, { payload }) {
    const user = await this.$axios.$get(`/users/${payload.user.id}.json`)
    const created_at = moment().format()
    payload = {
      ...payload,
      created_at
    }
    const post_id = (await this.$axios.$post('/posts.json', payload)).name
    const post = {
      id: post_id,
      ...payload
    }
    const putData = {
      id: post_id,
      ...payload
    }
    delete putData.user
    await this.$axios.$put(`/users/${user.id}/posts.json`, [
      ...(user.posts || []),
      putData
    ])
    commit('addPost', { post })
  },
  async addLikeToPost ({ commit }, { user, post }) {
    post.likes.push({
      created_at: moment().format(),
      user_id: user.id,
      post: {
        id: post.id,
        title: post.title,
        user_id: post.user.id
      }
    })
    const newPost = await this.$axios.$put(`/posts/${post.id}.json`, post)
    commit('updatePost', { post: newPost })
  },
  async removeLikeFromPost ({ commit }, { user, post }) {
    post.likes = post.likes.filter(like => like.user_id !== user.id) || []
    const newPost = await this.$axios.$put(`/posts/${post.id}.json`, post)
    commit('updatePost', { post: newPost })
  }
}