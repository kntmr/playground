window.addEventListener('load', function() {

  vm1 = new Vue({
    el: '#example1',
    data: {
      ok: true
    }
  });

  vm2 = new Vue({
    el: '#example2',
    data: {
      ok: true
    }
  });

  vm3 = new Vue({
    el: '#example3',
    data: {
      type: 'a'
    }
  });

  vm4 = new Vue({
    el: '#example4',
    data: {
      loginType: 'username'
    },
    methods: {
      toggle: function() {
        return this.loginType = this.loginType === 'username' ? 'email' : 'username';
      }
    }
  });

  vm5 = new Vue({
    el: '#example5',
    data: {
      ok: true
    }
  });

}, false);
