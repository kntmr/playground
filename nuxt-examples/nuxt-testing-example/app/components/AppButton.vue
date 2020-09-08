<template>
  <div>
    <p v-if="clicked">clicked</p>
    <button type="button" @click="click">click</button>
  </div>
</template>

<script>
export default {
  data () {
    return {
      clicked: false
    }
  },
  methods: {
    click () {
      this.clicked = true
    }
  }
}
</script>