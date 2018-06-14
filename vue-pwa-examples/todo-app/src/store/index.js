import Vue from 'vue'
import Vuex from 'vuex'
import firebase from 'firebase/app'
import 'firebase/database'

Vue.use(Vuex)

const STORAGE_KEY = 'vue-todo-app'

export default new Vuex.Store({
  state: {
    todos: {}
  },
  mutations: {
    initialize (state, todos) {
      console.log('initializing ...')
      if (todos !== null) {
        state.todos = todos
      }
    },
    addToDo (state, todo) {
      const newKey = firebase.database().ref('todos').push().key
      todo.id = newKey
      Vue.set(state.todos, newKey, todo)
      firebase.database().ref('todos/' + newKey).set(todo)
    },
    removeToDo (state, todo) {
      const id = todo.id
      Vue.delete(state.todos, todo.id)
      firebase.database().ref('todos/' + id).remove()
    },
    changeCompleted (state, todo) {
      todo.completed = !todo.completed
      const id = todo.id
      firebase.database().ref('todos/' + id).set(todo)
    }
  },
  strict: process.env.NODE_ENV !== 'production'
})
