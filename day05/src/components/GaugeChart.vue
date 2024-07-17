<script setup>
import { ref, onMounted } from "vue";
import * as echarts from "echarts";

const gaugeContainer = ref(null);   //<div ref="gaugeContainer" 


const gaugeProps = defineProps({
  "itemColor":{
    type: String,
    default:"#ff0000",
    required:false
  },
  "labelColor":{
    type: String,
    default:"#ffff00",
    required:false
  },
  "data":{
    type: Number,
    default:0,
    required:true
  }
});

let myChart = null;
onMounted(() => {

    let option = {
        backgroundColor: "#001432",
        series: [
            {
                type: "gauge",
                itemStyle: {
                    color: gaugeProps.itemColor
                },
                progress: {
                    show: true,
                    width: 4,    //进度圆环的宽度
                },
                axisLine: {
                    lineStyle: {
                        width: 6,   //进度圆环的宽度
                    },
                },
                axisTick: {
                    show: false,
                },
                splitLine: {
                    length: 0,     //拆分线长度
                    lineStyle: {
                        width: 0,     //拆分线宽度
                        color: "#999",
                    },
                },
                axisLabel: {
                    distance: 3,    //拆分线标签间距
                    color: "#FFF",
                    fontSize: 10,
                },
                anchor: {
                    show: true,
                    showAbove: true,
                    size: 10,
                    itemStyle: {
                        borderWidth: 10,
                    },
                },
                title: {
                    show: false,
                },
                detail: {
                    valueAnimation: true,
                    fontSize: 20,
                    offsetCenter: [0, "90%"],
                    color: gaugeProps.labelColor
                },
                data: [
                    {
                        value: gaugeProps.data
                    }
                ]
            }
        ]
    }

    myChart = echarts.init(gaugeContainer.value, "dark", {
        renderer: "canvas",
        useDirtyRect: false,
    });
    myChart.setOption(option);


}) 
</script>

<template>

    <div ref="gaugeContainer" class="chart-container">

    </div>

</template>

<style scoped>
.chart-container {
    height: 100%;
    width: 100%;
}
</style>
