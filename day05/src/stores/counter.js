import { ref, computed } from 'vue'
import { defineStore } from 'pinia'



export const useCounterStore = defineStore('counter', () => {
  
  //共享的数据（好比是vuex中state）
  const count = ref(10)


  //类似于computed（好比是vuex中getter）
  const doubleCount = computed(() => count.value * 2)

  //好比是vuex中的action
  function increment() {
    console.log("111111111111111111111");
    count.value++
  }

  return { count, doubleCount, increment }
})
