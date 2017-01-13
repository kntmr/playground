window.addEventListener("load", function(e) {

  var data1 = { a: 1 };
  var vm1 = new Vue({
    data: data1
  });
  // data オブジェクトのプロパティをプロキシする
  // プロキシされたプロパティはリアクティブ
  console.log(data1.a === vm1.a); // => true
  data1.a = 2;
  console.log("data1.a : " + data1.a + " / vm1.a : " + vm1.a); // => 2
  vm1.a = 3;
  console.log("data1.a : " + data1.a + " / vm1.a : " + vm1.a); // => 3

  var data2 = { a: 1 };
  var vm2 = new Vue({
    el: '#example',
    data: data2
  });
  console.log(vm2.$data === data2); // => true
  console.log(vm2.$el === document.getElementById('example')); // => true
  vm2.$watch('a', function(oldVal, newVal) {
    // プロパティの値が変更されたときにコールバックされる
    console.log("oldVal : " + oldVal + " / newVal : " + newVal);
  });
  vm2.a = 2; // data2.a を変更してもコールバックされる

}, false);
