window.addEventListener('load', function() {

  vm1 = new Vue({
    el: '#example1',
    data: {
      message: ''
    }
  });

  vm2 = new Vue({
    el: '#example2',
    data: {
      message: ''
    }
  });

  vm3 = new Vue({
    el: '#example3',
    data: {
      checked: false
    }
  });

  vm4 = new Vue({
    el: '#example4',
    data: {
      checkboxes: []
    }
  });

  vm5 = new Vue({
    el: '#example5',
    data: {
      picked: ''
    }
  });

  vm6 = new Vue({
    el: '#example6',
    data: {
      selected: ''
    }
  });

  vm7 = new Vue({
    el: '#example7',
    data: {
      selected: []
    }
  });

  vm8 = new Vue({
    el: '#example8',
    data: {
      selected: '',
      options: [
        { text: 'One', value: 'a' },
        { text: 'Two', value: 'b' },
        { text: 'Three', value: 'c' },
      ]
    }
  });

  vm9 = new Vue({
    el: '#example9',
    data: {
      toggle: '',
      a: 'foo',
      b: 'bar',
      pick: '',
      c: 'hoge',
      selected: ''
    }
  });

  vm10 = new Vue({
    el: '#example10',
    data: {
      msg: '',
      age: 0,
      trimed: ''
    }
  });

}, false);
