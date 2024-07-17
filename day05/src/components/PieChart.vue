<template>
  <div ref="pieChart" class="chart"></div>
</template>

<script setup>
import * as echarts from 'echarts';
import { onMounted, ref, reactive, watch } from 'vue';
import axios from 'axios';

const pieChart = ref(null);
const chartData = reactive([]);

const fetchData = async () => {
  try {
    const response = await axios.get('http://localhost:8080/students/top5-workplace');
    chartData.splice(0, chartData.length, ...response.data.map(item => ({
      value: item.count,
      name: item.workplace
    })));
  } catch (error) {
    console.error('Failed to fetch data:', error);
  }
};

onMounted(() => {
  fetchData();

  watch(chartData, (newData) => {
    const myChart = echarts.init(pieChart.value);
    const option = {
      title: {
        text: 'Top 5 工作城市',
        left: 'center',
        top: '85%'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        top: '5%',
        left: 'center'
      },
      series: [
        {
          name: '工作城市',
          type: 'pie',
          radius: ['40%', '70%'],
          avoidLabelOverlap: false,
          itemStyle: {
            borderRadius: 10,
            borderColor: '#fff',
            borderWidth: 2
          },
          label: {
            show: false,
            position: 'center'
          },
          emphasis: {
            label: {
              show: true,
              fontSize: 40,
              fontWeight: 'bold'
            }
          },
          labelLine: {
            show: false
          },
          data: newData
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
