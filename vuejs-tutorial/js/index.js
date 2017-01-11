window.addEventListener("load", function(e) {

  app = new Vue({
    el: '#app',
    data: {
      message: 'Hello Vue!'
    }
  });

  app2 = new Vue({
    el: '#app-2',
    data: {
      message: 'You loaded this page on ' + new Date()
    }
  });

  app3 = new Vue({
    el: '#app-3',
    data: {
      seen: true
    }
  });

  app4 = new Vue({
    el: '#app-4',
    data: {
      todos: [
        { text: 'Learn JavaScript' },
        { text: 'Learn Vue' },
        { text: 'Build something awesome' }
      ]
    }
  });

  app5 = new Vue({
    el: '#app-5',
    data: {
      message: 'Hello Vue.js!'
    },
    methods: {
      reverseMessage: function() {
        this.message = this.message.split('').reverse().join(''); // 文字を分割して逆順に並べる
      }
    }
  });

  app6 = new Vue({
    el: '#app-6',
    data: {
      message: 'Hello Vue!'
    }
  });

  // Vueでコンポーネントを登録する
  Vue.component('todo-item', {
    // todo-item コンポーネントに todo という名前のプロパティを定義する
    props: ['todo'],
    template: '<li>{{ todo.text }}</li>'
  });

  app7 = new Vue({
    el: '#app-7',
    data: {
      groceryList: [
        { text: 'Vegetables' },
        { text: 'Cheese' },
        { text: 'Whatever else humans are supposed to eat' }
      ]
    }
  });

}, false);
