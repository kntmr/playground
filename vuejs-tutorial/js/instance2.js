window.addEventListener('load', function() {

  var vm1 = new Vue({
    el: '#app1',
    data: {
      val: 'Hello!'
    },
    // ライフサイクルフック
    // this は Vue インスタンス (vm1) を指す
    beforeCreate: function() {
      console.log('beforeCreate - val: ' + this.val); // undefined
    },
    created: function() {
      console.log('created - val: ' + this.val); // Hello!
    },
    // this.$el は [object HTMLDivElement]
    beforeMount: function() {
      console.log('beforeMount - el: ' + this.$el.innerHTML); // {{ val }}
    },
    mounted: function() {
      console.log('mounted - el: ' + this.$el.innerHTML); // Hello!
    }
  });

}, false);
