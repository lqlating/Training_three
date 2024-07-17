<template>
  <div ref="barChart" style="width: 100%; height: 100%;"></div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import * as echarts from 'echarts';
import axios from 'axios';

const barChart = ref(null);
const chartInstance = ref(null);
const chartData = ref([]);

// 获取数据的函数
const fetchData = async () => {
  try {
    const response = await axios.get('http://localhost:8080/students/employmentRateByMajor');
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

  chartInstance.value = echarts.init(barChart.value);
  updateChart();
};

// 更新图表的函数
const updateChart = () => {
  if (chartInstance.value) {
    const option = {
      title: {
        text: '专业就业率',
        left: 'center',
        top: '5%'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        }
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        top: '20%', // 确保图表不会与标题重叠
        containLabel: true
      },
      xAxis: [
        {
          type: 'category',
          data: chartData.value.map(item => item.major),
          axisTick: {
            alignWithLabel: true
          },
          axisLabel: {
            interval: 0, // 显示所有标签
            rotate: 30, // 旋转角度
            fontSize: 10 // 字体大小
          }
        }
      ],
      yAxis: [
        {
          type: 'value'
        }
      ],
      series: [
        {
          name: 'Employment Rate',
          type: 'bar',
          barWidth: '60%',
          data: chartData.value.map(item => item.employment_rate)
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
