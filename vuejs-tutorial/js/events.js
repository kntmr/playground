window.addEventListener('load', function() {

  vm1 = new Vue({
    el: '#example1',
    data: {
      counter: 0
    }
  });

  vm2 = new Vue({
    el: "#example2",
    data: {
      name: 'Vue.js'
    },
    methods: {
      greet: function() {
        // this は Vue インスタンスを参照する
        alert('Hello ' + this.name + " !");
        // event はネイティブDOMイベント
        if (event) {
          alert(event.target.tagName);
        }
      },
      say: function(message) {
        alert(message);
      },
      warn: function(message, event) {
        if (event) {
          event.preventDefault();
        }
        alert(message + ' : ' + event.type);
      }
    }
  });
  // メソッドは親のスコープから呼び出すことができる
  //vm2.greet();

  vm3 = new Vue({
    el: '#example3',
    methods: {
      doOnce: function() {
        alert('Hello!')
      }
    }
  });

  vm4 = new Vue({
    el: '#example4',
    methods: {
      submit: function() {
        alert('Submitted!');
      }
    }
  });
  // キー修飾子のエイリアスは config.keyCodes オブジェクトで定義できる
  //Vue.config.keyCodes.f1 = 112;

}, false);
