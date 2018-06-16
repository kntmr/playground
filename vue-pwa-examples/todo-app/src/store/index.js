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
      // callback
      firebase.database().ref('todos').on('child_added', data => {
        const todo = data.val()
        todo.id = data.key
        this.commit('_addToDo', todo)
      })
      firebase.database().ref('todos').on('child_removed', data => {
        const todo = data.val()
        todo.id = data.key
        this.commit('_removeToDo', todo)
      })
      firebase.database().ref('todos').on('child_changed', data => {
        const todo = data.val()
        todo.id = data.key
        this.commit('_changeCompleted', todo)
      })
    },
    addToDo (state, todo) {
      const newKey = firebase.database().ref('todos').push().key
      todo.id = newKey
      firebase.database().ref('todos/' + newKey).set(todo)
      this.commit('_addToDo', todo)
    },
    removeToDo (state, todo) {
      const id = todo.id
      firebase.database().ref('todos/' + id).remove()
      this.commit('_removeToDo', todo)
    },
    changeCompleted (state, todo) {
      todo.completed = !todo.completed
      const id = todo.id
      firebase.database().ref('todos/' + id).set(todo)
      this.commit('_changeCompleted', todo)
    },
    _addToDo (state, todo) {
      Vue.set(state.todos, todo.id, todo)
    },
    _removeToDo (state, todo) {
      Vue.delete(state.todos, todo.id)
    },
    _changeCompleted (state, todo) {
      Vue.set(state.todos, todo.id, todo)
    }
  },
  strict: process.env.NODE_ENV !== 'production'
})
