const list1 = [
  { id: 0, name: 'item-00' },
  { id: 1, name: 'item-01' },
  { id: 2, name: 'item-02' },
  { id: 3, name: 'item-03' },
  { id: 4, name: 'item-04' }
]
const list2 = [
  { id: 5, name: 'item-05' },
  { id: 6, name: 'item-06' },
  { id: 7, name: 'item-07' },
  { id: 8, name: 'item-08' },
  { id: 9, name: 'item-09' }
]

new Vue({
  el: '#app',
  template: `
    <div>
      <ul>
        <li v-for="item in items" :key="item.id">
          <p class="item" @click="select">{{ item.name }}</p>
        </li>
      </ul>
      <button @click="toggle">Toggle Data</button>
    </div>
  `,
  data () {
    return {
      items: list1,
      flg: false
    }
  },
  methods: {
    select (e) {
      e.currentTarget.style.color = 'red'
    },
    toggle () {
      if (this.items === list1) this.items = list2
      else this.items = list1
    }
  }
})
