package com.example.practiceback.service;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<Map<String, Object>> getEmploymentRateByMajor();
    List<Map<String, Object>> countWorkplaceByJobbed();
    List<Map<String, Object>> countJobByJobbed();
    List<Map<String, Object>> countAgeByJobbed();
    List<Map<String, Object>> countSalaryByJobbed();
    List<Map<String, Object>> top5WorkplacesByJobbed();
    int getTotalStudents();  // 新增方法
    int getJobbedStudents(); // 新增方法
}
