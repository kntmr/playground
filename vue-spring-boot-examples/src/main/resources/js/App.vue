<template>
  <div>
    <p>
      <input type="number" v-model.number="input" @keyup.enter="submit">
    </p>
    <p>
      {{ output }}
    </p>
  </div>
</template>

<script>
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.headers.post['Content-Type'] = 'application/json'

export default {
  name: 'app',
  data () {
    return {
      input: '',
      output: ''
    }
  },
  methods: {
    submit() {
      axios.post('/echo', JSON.stringify({ content: this.input })).then(response => {
        this.output = response.data.content
      })
    }
  }
}
</script>
