<template>
  <section class="container">
    <div>
      <h3>{{ user.id }}</h3>
      <img :src="user.profile_image_url" width="120" alt="">
      <p>{{ user.description || 'No description.' }}</p>
      <p>
        <nuxt-link to="/">
          <small><b>トップに戻る</b></small>
        </nuxt-link>
      </p>
      <h3>{{ user.id }}さんの投稿一覧</h3>
      <ul>
        <li v-for="item in items" :key="item.id">
          <h4>
            <span>{{ item.title }}</span>
            <small>
              <span> by </span>
              <nuxt-link :to="`/users/${item.user.id}`">{{ item.user.id }}</nuxt-link>
            </small>
          </h4>
          <div>{{ item.body.slice(0, 130) }}...</div>
          <p><a :href="item.url">{{ item.url }}</a></p>
        </li>
      </ul>
    </div>
  </section>
</template>

<script>
export default {
  async asyncData ({ route, app }) {
    const user = await app.$axios.$get(`https://qiita.com/api/v2/users/${route.params.id}`)
    const items = await app.$axios.$get(`https://qiita.com/api/v2/items?query=user:${route.params.id}`)
    return {
      user,
      items
    }
  }
}
</script>

<style scoped>
.container {
  min-height: 100vh;
  padding: 16px;
}

h3 {
  margin: 16px 0;
  padding: 8px 0;
  border-bottom: solid 1px #e5e5e5;
}

li + li {
  margin: 16px 0;
}

p {
  margin: 8px 0;
}
</style>