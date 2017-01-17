window.addEventListener('load', function() {

  vm1 = new Vue({
    el: '#example1',
    data: {
      message: 'Hello!'
    },
    computed: {
      // 算出プロパティ (算出 getter 関数)
      // reversedMessage の計算結果はキャッシュされる (依存するプロパティが更新された場合のみ再評価される)
      reversedMessage: function() {
        // this は Vue インスタンスを指す
        // vm.reversedMessage の値は vm.message の値に依存する (リアクティブ)
        return this.message.split('').reverse().join('');
      }
    }
  });

  vm1a = new Vue({
    data: {
      message: 'Hello!'
    },
    // 算出プロパティではなくメソッドを使う場合
    methods: {
      // HTML に {{ reverseMessage() }} を記述して呼び出す
      // 算出プロパティと違って再描画のたびに常に関数を実行する
      reverseMessage: function() {
        return this.message.split('').reverse().join('');
      }
    }
  });

  vm1b = new Vue({
    data: {
      firstName: 'Foo',
      lastName: 'Bar',
      fullName: 'Foo Bar'
    },
    // 算出プロパティではなく監視プロパティを使う場合
    watch: {
      firstName: function(val) {
        this.fullName = val + ' ' + this.lastName;
      },
      lastName: function(val) {
        this.fullName = this.firstName + ' ' + val;
      }
    }
  });

  vm2 = new Vue({
    el: '#example2',
    data: {
      firstName: 'foo',
      lastName: 'bar'
    },
    computed: {
      fullName: {
        get: function() {
          return this.firstName + ' ' + this.lastName;
        },
        // 算出 setter 関数
        // vm2.fullName で値を更新すると setter が呼ばれる
        set: function(newVal) {
          var names = newVal.split(' ');
          this.firstName = names[0];
          this.lastName = names[names.length - 1];
        }
      }
    }
  });

}, false);
