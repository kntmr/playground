window.addEventListener('load', function() {

  var vm1 = new Vue({
    el: '#example1',
    data: {
      message: 'Hello!'
    },
    computed: {
      // 算出プロパティ
      reversedMessage: function() {
        // this は Vue インスタンスを指す
        // vm.reversedMessage の値は vm.message の値に依存する (リアクティブ)
        return this.message.split('').reverse().join('');
      }
    }
  });

}, false);
