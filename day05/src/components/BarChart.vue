<template>
  <div ref="barChart" class="chart"></div>
</template>

<script setup>
import * as echarts from 'echarts';
import { onMounted, ref, watch } from 'vue';
import axios from 'axios';

const barChart = ref(null);
const chartData = ref([]);

const fetchData = async () => {
  try {
    const response = await axios.get('http://localhost:8080/students/job');
    chartData.value = response.data.map(item => ({
      job: item.job,
      count: item.count
    }));
  } catch (error) {
    console.error('Failed to fetch data:', error);
  }
};

onMounted(() => {
  fetchData();

  watch(chartData, (newData) => {
    const myChart = echarts.init(barChart.value);
    const option = {
      title: {
        text: '工作行业',
        left: 'center',
        top: '5%'
      },
      xAxis: {
        type: 'category',
        data: newData.map(item => item.job)
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: newData.map(item => item.count),
          type: 'bar'
        }
      ]
    };
    myChart.setOption(option);
  });
});
</script>

<style scoped>
.chart {
  height: 100%;
}
</style>
