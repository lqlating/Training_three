<template>
  <div ref="pieChart" style="width: 100%; height: 100%;"></div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import * as echarts from 'echarts';
import axios from 'axios';

const pieChart = ref(null);
const chartInstance = ref(null);
const chartData = ref([]);

// 获取数据的函数
const fetchData = async () => {
  try {
    const response = await axios.get('http://localhost:8080/students/salary');
    chartData.value = response.data;
  } catch (error) {
    console.error('Failed to fetch data:', error);
  }
};

// 初始化图表的函数
const initChart = () => {
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }

  chartInstance.value = echarts.init(pieChart.value);
  updateChart();
};

// 更新图表的函数
const updateChart = () => {
  if (chartInstance.value) {
    const option = {
      tooltip: {
        trigger: 'item'
      },
      legend: {
        top: '5%',
        left: 'center'
      },
      series: [
        {
          name: 'Salary Range',
          type: 'pie',
          radius: ['40%', '70%'],
          center: ['50%', '70%'],
          startAngle: 180,
          endAngle: 360,
          data: chartData.value.map(item => ({
            value: item.count,
            name: item.salary_range
          }))
        }
      ]
    };

    chartInstance.value.setOption(option);
  }
};

// 在组件挂载时获取数据并初始化图表
onMounted(() => {
  fetchData();
  initChart();
});

// 监听 chartData 的变化并更新图表
watch(chartData, updateChart);
</script>

<style scoped>
div {
  width: 100%;
  height: 100%;
}
</style>
