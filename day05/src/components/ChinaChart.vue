<template> 
  <div ref="chinaContainer" class="chart-container"></div> 
</template>

<script setup>
import { ref, onMounted, watch } from "vue";
import * as echarts from "echarts";
import axios from 'axios';

let chinaContainer = ref(null);
let chartInstance = ref(null);
let mapData = ref([]);

import chinaJSON from '@/assets/js/china.json';

// 注册中国地图
echarts.registerMap('china', chinaJSON);

// 获取数据的函数
const fetchData = async () => {
  try {
    const response = await axios.get('http://localhost:8080/students/workplace');
    mapData.value = response.data.map(item => ({
      name: item.workplace,
      value: item.count
    }));
  } catch (error) {
    console.error('Failed to fetch data:', error);
  }
};

// 初始化图表的函数
const initChart = () => {
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }
  
  chartInstance.value = echarts.init(chinaContainer.value);
  const option = {
    backgroundColor: "#001432",
    series: {
      type: 'map',
      mapType: 'china',
      zoom: 1.2,
      label: {
        normal: {
          show: true,
          color: '#000',
          fontSize: 10
        },
        emphasis: {
          show: true,
          color: 'red'
        }
      },
      data: mapData.value,  // 将数据赋值给 series.data
      name: '就业人数'
    },
    visualMap: {
      left: 'left',
      top: 'bottom',
      itemWidth: 10,
      itemHeight: 10,
      splitList: [
        { start: 200, color: '#ff0000' },  // 红色
        { start: 150, end: 200, color: '#CB3A56' },  // 深红色
        { start: 100, end: 150, color: '#FF7500' },  // 橙色
        { start: 50, end: 100, color: '#FAFF72' },  // 黄色
        { start: 0, end: 50, color: '#00ff00' }  // 绿色
      ],
      textStyle: {
        color: '#FFFFFF'  // 设置字体颜色为白色
      },
      show: true,
    },
    tooltip: {
      trigger: 'item'
    }
  };

  chartInstance.value.setOption(option);
};

// 在组件挂载时获取数据并初始化图表
onMounted(() => {
  fetchData();
  initChart();
});

// 监听 mapData 的变化并更新图表
watch(mapData, () => {
  if (chartInstance.value) {
    chartInstance.value.setOption({
      series: [{
        data: mapData.value
      }]
    });
  }
});
</script>

<style scoped>
.chart-container {
  height: 100%;
  width: 100%;
}
</style>
