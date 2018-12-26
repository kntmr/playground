<template>
  <div>
    <h4>Spring WebFlux & Vue.js / <a href="#" @click="stop">stop events</a></h4>
    <table>
      <tr v-for="u in users">
        <td>user{{ u.id }}</td>
        <td>{{ u.name }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
let eventSource = null
export default {
  name: 'app',
  data () {
    return {
      users: []
    }
  },
  methods: {
    stop: () => {
      eventSource.close()
    }
  },
  created () {
    eventSource = new EventSource('//localhost:8080/event');
    eventSource.onmessage = (e) => {
      this.users.push(JSON.parse(e.data))
    }
  }
}
</script>
