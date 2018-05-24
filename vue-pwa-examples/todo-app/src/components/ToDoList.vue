<template>
  <div>
    <input type="text" placeholder="todo" autofocus v-model="internal" @keyup.enter="addToDo">
    <ul>
      <li v-for="todo in todos">
        <input type="checkbox" v-model="todo.completed">
        <label :class="{ completed: todo.completed }">{{ todo.content }}</label>
        <button @click="remove(todo)">x</button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'ToDoList',
  data () {
    return {
      todos: [
        { content: 'aaa', completed: false },
        { content: 'bbb', completed: false },
        { content: 'ccc', completed: false }
      ],
      internal: ''
    }
  },
  methods: {
    addToDo () {
      const val = this.internal.trim()
      if (!val) {
        return
      }
      this.todos.push({ content: val, completed: false })
      this.internal = ''
    },
    remove (todo) {
      const index = this.todos.indexOf(todo)
      if (index < 0) {
        return
      }
      this.todos.splice(index, 1)
    }
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
