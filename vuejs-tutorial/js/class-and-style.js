window.addEventListener('load', function() {

  // バインディングHTMLクラス
  // オブジェクト構文
  vm1 = new Vue({
    el: '#example1',
    data: {
      isActive: true,
      hasError: false
    },
    // オブジェクトを返す算出プロパティ
    computed: {
      classObject: function() {
        return { active: this.isActive, 'text-danger': this.hasError }
      }
    }
  });

  // 配列構文
  vm2 = new Vue({
    el: '#example2',
    data: {
      activeClass: 'active',
      errorClass: 'text-danger',
      isActive: false,
      hasError: false
    }
  });

  // バインディングインラインスタイル
  // オブジェクト構文
  vm3 = new Vue({
    el: '#example3',
    data: {
      activeColor: 'red',
      fontSize: 30
    },
    // オブジェクトを返す算出プロパティ
    computed: {
      styleObject: function() {
        return { color: this.activeColor, fontSize: this.fontSize + 'px' }
      }
    }
  });

  // 配列構文
  vm4 = new Vue({
    el: '#example4',
    data: {
      baseStyle: {
        color: 'red',
        fontSize: 30 + 'px'
      },
      overridingStyle: {
        color: 'blue'
      }
    }
  });

}, false);
