window.addEventListener('load', function() {

  // グローバルなコンポーネント登録
  Vue.component('my-component-1', {
    template: '<div>A custom component-1!</div>'
  });
  // root の Vue インスタンスを生成する前にコンポーネントを登録すること
  new Vue({
    el: '#example1'
  });

  // ローカルなコンポーネント登録
  var child = {
    template: '<div>A custom component-2!</div>'
  };
  // この Vue インスタンスの中でコンポーネントを利用できる
  new Vue({
    el: '#example2',
    components: {
      'my-component-2': child
    }
  });

  var data = { counter : 0 };
  Vue.component('my-counter-1', {
    template: '<button v-on:click="counter += 1">{{ counter }}</button>',
    data: function() {
      // コンポーネントは同じ data オブジェクトを共有する
      return data;
    }
  });
  Vue.component('my-counter-2', {
    template: '<button v-on:click="counter += 1">{{ counter }}</button>',
    data: function() {
      // 個別に data オブジェクトを保持する
      return { counter: 0 }
    }
  });
  new Vue({
    el: '#example3'
  });

  // プロパティは親から子に情報を伝達するためのカスタム属性
  Vue.component('child-1', {
    // props で伝達するためのプロパティを宣言する
    props: ['message'],
    template: '<span>{{ message }}</span>'
  });
  new Vue({
    el: '#example4'
  });

  Vue.component('child-2', {
    props: ['message'],
    template: '<span>{{ message }}</span>'
  });
  new Vue({
    el: '#example5',
    data: {
      parentMsg: 'Message from parent'
    }
  });

}, false);
