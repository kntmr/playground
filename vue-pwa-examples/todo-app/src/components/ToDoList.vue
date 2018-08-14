<template>
  <div>
    <input type="text" placeholder="todo" autofocus v-model="internal" @keyup.enter="addToDo">
    <ul>
      <li v-for="todo in todos" :key="todo.key">
        <input type="checkbox" :checked="todo.completed" @change="changeCompleted(todo)">
        <label :class="{ completed: todo.completed }">{{ todo.content }}</label>
        <button @click="removeToDo(todo)">x</button>
      </li>
    </ul>
  </div>
</template>

<script>
import firebase from 'firebase/app'
import 'firebase/database'
export default {
  name: 'ToDoList',
  data () {
    return {
      internal: ''
    }
  },
  computed: {
    todos () {
      return this.$store.state.todos
    }
  },
  methods: {
    addToDo () {
      const val = this.internal.trim()
      if (!val) {
        return
      }
      this.$store.commit('addToDo', { content: val, completed: false })
      this.internal = ''
    },
    removeToDo (todo) {
      this.$store.commit('removeToDo', todo)
    },
    changeCompleted (todo) {
      this.$store.commit('changeCompleted', todo)
    }
  },
  created () {
    firebase.database().ref('todos').once('value').then(snapshot => {
      this.$store.commit('initialize', snapshot.val())
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin: 0 10px;
}
.completed {
  text-decoration: line-through;
}
</style>
