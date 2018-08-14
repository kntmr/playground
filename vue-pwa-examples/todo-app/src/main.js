// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import firebase from 'firebase/app'
import 'firebase/database'

Vue.config.productionTip = false

var config = {
  apiKey: "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
  authDomain: "APPNAME.firebaseapp.com",
  databaseURL: "https://APPNAME.firebaseio.com",
  projectId: "APPNAME",
  storageBucket: "APPNAME.appspot.com",
  messagingSenderId: "xxxxxxxxxxxx"
}
firebase.initializeApp(config)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
