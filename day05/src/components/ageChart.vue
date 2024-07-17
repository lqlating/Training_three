<template>
  <div ref="pieChart" class="chart-container"></div>
</template>

<script setup>
import * as echarts from 'echarts';
import { ref, onMounted, watch } from 'vue';
import axios from 'axios';

const pieChart = ref(null);
const chartInstance = ref(null);
const chartData = ref([]);

const fetchData = async () => {
  try {
    const response = await axios.get('http://localhost:8080/students/age');
    chartData.value = response.data.map(item => ({
      value: item.count,
      name: `${item.age}岁`
    }));
  } catch (error) {
    console.error('Failed to fetch data:', error);
  }
};

const initChart = () => {
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }

  chartInstance.value = echarts.init(pieChart.value);
  const option = {
    title: {
      text: '年龄分布',
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        name: '年龄分布',
        type: 'pie',
        radius: '50%',
        data: chartData.value,
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  };

  chartInstance.value.setOption(option);
};

onMounted(() => {
  fetchData();
  initChart();

  watch(chartData, () => {
    if (chartInstance.value) {
      chartInstance.value.setOption({
        series: [{
          data: chartData.value
        }]
      });
    }
  });
});
</script>

<style scoped>
.chart-container {
  height: 100%;
  width: 100%;
}
</style>
