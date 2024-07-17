<template>
  <div class="wrapper">
    <header>
      <div class="left">学生就业数据统计</div>
      <div class="right">
        <i class="fa fa-clock-o"></i>
        {{ currentTime }}
      </div>
    </header>
    <div class="main">
      <aside>
        <!-- 左一 各行就业人数-->
        <div class="grid-box" style="background-color: white;">
          <BarChart />
        </div>
        <!-- 左二 前五的工作城市-->
        <div style="background-color: lightblue;">
          <PieChart />
        </div>
      </aside>
      <section>
        <div class="map" style="background-color: #002244;">
          <ChinaChart />
        </div>
        <div class="statistics">
          <h3>学生就业统计实时数据</h3>
          <ul>
            <li>
              <i class="fa fa-user-o"></i>
              <div>
                <h4>{{ studentData.totalStudents }}</h4>
                <p>总学生人数</p>
              </div>
            </li>
            <li>
              <i class="fa fa-briefcase"></i>
              <div>
                <h4>{{ studentData.jobbedStudents }}</h4>
                <p>已经就业人数</p>
              </div>
            </li>
            <li>
              <i class="fa fa-line-chart"></i>
              <div>
                <h4 class="rate">{{ studentData.jobbedPercentage }}%</h4>
                <p>就业率</p>
              </div>
            </li>
          </ul>
        </div>
      </section>
      <article>
        <!-- 右一 年龄分布-->
        <div class="right-section" style="background-color: lightcoral;">
          <PieChart2 />
        </div>
        <!-- 右二 各专业就业率-->
        <div class="right-section" style="background-color: lightgreen;">
          <workRate />
        </div>
        <!-- 右三 薪资统计-->
        <div class="right-section" style="background-color: lightgoldenrodyellow;">
          <SalaryChart />
        </div>
      </article>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import PieChart from '@/components/PieChart.vue';
import GaugeChart from '@/components/GaugeChart.vue';
import ChinaChart from '@/components/ChinaChart.vue';
import BarChart from '@/components/BarChart.vue';
import PieChart2 from '@/components/ageChart.vue';
import SalaryChart from '@/components/SalaryChart.vue';
import workRate from '@/components/workRate.vue';
import axios from 'axios';

const currentTime = ref('');
const studentData = ref({
  totalStudents: 0,
  jobbedStudents: 0,
  jobbedPercentage: 0
});

function updateTime() {
  const now = new Date();
  const options = {
    timeZone: 'Asia/Shanghai',
    hour12: false,
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit'
  };
  currentTime.value = now.toLocaleString('zh-CN', options);
}

function fetchStudentData() {
  axios.get('http://localhost:8080/students/statistics')
    .then(response => {
      studentData.value = response.data;
    })
    .catch(error => {
      console.error('Error fetching student data:', error);
    });
}

onMounted(() => {
  updateTime();
  setInterval(updateTime, 1000);
  fetchStudentData();
  setInterval(fetchStudentData, 60000); // 每分钟更新一次数据
});
</script>

<style scoped>
.wrapper {
  width: 100%;
  height: 100%;
  background-color: #001432;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

header {
  width: 100%;
  font-weight: 300;
  color: #aaf9fc;
  background-color: #002244; /* 深色背景 */
  position: relative;
  display: flex;
  justify-content: center; /* 居中对齐 */
  align-items: center;
  padding: 0.5rem; /* 添加内边距，使内容居中对齐 */
}

header .left {
  font-size: 1.5rem; /* 根据需要调整字体大小 */
  font-weight: bold; /* 加粗 */
  text-align: center; /* 居中 */
}

header .right {
  position: absolute; /* 绝对定位 */
  right: 20px; /* 距离右边20px */
  font-size: 1rem; /* 根据需要调整字体大小 */
  display: flex;
  align-items: center;
}

header .right i {
  margin-right: 0.5rem; /* 图标与文本之间的间距 */
}

.main {
  flex: 1;
  width: 100%;
  display: flex;
}

.main aside,
.main article {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.main aside div,
.main article div {
  flex: 1;
}

.main section {
  flex: 0 0 44%;
  display: flex;
  flex-direction: column;
}

.main section .map {
  flex: 3;
}

.main section .statistics {
  flex: 1;
  overflow: hidden;
}

.main aside .grid-box ul {
  width: 100%;
  height: 100%;
  display: flex;
}

.main aside .grid-box ul li {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.main aside .grid-box ul li h4 {
  flex: 1;
  text-align: center;
  color: #fff;
  font-size: 14px;
}

.main aside .grid-box ul li div {
  flex: 4;
}

.main section .statistics h3 {
  text-align: center;
  font-size: 14px;
  color: #fff;
  margin-top: 20px;
}

.main section .statistics ul {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
  color: #fff;
}

.main section .statistics ul li {
  display: flex;
  align-items: center;
}

.main section .statistics ul li .fa {
  font-size: 36px;
  color: #32ccee;
  margin-right: 10px;
}

.main section .statistics ul li div h4 {
  font-size: 26px;
}

.main section .statistics ul li div p {
  font-size: 14px;
  margin-top: 3px;
}

/* 新增样式，确保右边三块区域高度均等 */
.right-section {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
}

.rate {
  margin-left: -25px;
}
</style>
