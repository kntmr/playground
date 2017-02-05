window.addEventListener('load', function() {

  vm1 = new Vue({
    el: '#example1',
    data: {
      items: [
        { message: 'foo' },
        { message: 'bar' },
        { message: 'baz' }
      ]
    }
  });

  vm2 = new Vue({
    el: '#example2',
    data: {
      parentMessage: 'parent',
      items: [
        { message: 'foo' },
        { message: 'bar' },
        { message: 'baz' }
      ]
    }
  });

  vm3 = new Vue({
    el: '#example3',
    data: {
      items: [
        { message: 'foo', description: 'This is foo.' },
        { message: 'bar', description: 'This is bar.' },
        { message: 'baz', description: 'This is baz.' }
      ]
    }
  });

  vm4 = new Vue({
    el: '#example4',
    data: {
      object: {
        firstName: 'John',
        lastName: 'Doe',
        age: 30
      }
    }
  });

  vm5 = new Vue({
    el: '#example5'
  });

  vm6 = new Vue({
    el: '#example6',
    data: {
      todos: [
        { text: 'task-A', isComplete: false },
        { text: 'task-B', isComplete: false },
        { text: 'task-C', isComplete: true },
        { text: 'task-D', isComplete: true },
        { text: 'task-E', isComplete: false },
      ]
    }
  });

  vm7 = new Vue({
    el: '#example7',
    data: {
      numbers: [ 1, 2, 3, 4, 5 ]
    },
    // 算出プロパティで処理済みの配列を表示する
    computed: {
      evenNumbers: function() {
        return this.numbers.filter(function(num) {
          return num % 2 === 0
        });
      }
    }
  });

  vm8 = new Vue({
    el: '#example8',
    data: {
      numbers: [ 1, 2, 3, 4, 5 ]
    },
    // 算出プロパティが使えない場合はメソッドを使用する
    methods: {
      even: function() {
        return this.numbers.filter(function(num) {
          return num % 2 === 0
        });
      }
    }
  });

}, false);
