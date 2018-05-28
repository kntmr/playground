import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const STORAGE_KEY = 'vue-todo-app'

export default new Vuex.Store({
  state: {
    todos: JSON.parse(window.localStorage.getItem(STORAGE_KEY) || '[]')
  },
  mutations: {
    addToDo (state, todo) {
      state.todos.push(todo)
    },
    removeToDo (state, todo) {
      const index = state.todos.indexOf(todo)
      if (index < 0) {
        return
      }
      state.todos.splice(index, 1)
    },
    changeCompleted (state, todo) {
      todo.completed = !todo.completed
    }
  },
  plugins: [
    store => {
      store.subscribe((mutations, state) => {
        window.localStorage.setItem(STORAGE_KEY, JSON.stringify(state.todos))
      })
    }
  ],
  strict: process.env.NODE_ENV !== 'production'
})
