package com.example.practiceback.controller;

import com.example.practiceback.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //各专业就业率
    @GetMapping("/employmentRateByMajor")
    public List<Map<String, Object>> getEmploymentRateByMajor() {
        return studentService.getEmploymentRateByMajor();
    }

    //各地已有工作学生的分布
    @GetMapping("/workplace")
    public List<Map<String, Object>> countWorkplaceByJobbed() {
        return studentService.countWorkplaceByJobbed();
    }

    //学生就业的行业及其数量
    @GetMapping("/job")
    public List<Map<String, Object>> countJobByJobbed() {
        return studentService.countJobByJobbed();
    }

    //已工作学生的年龄分布
    @GetMapping("/age")
    public List<Map<String, Object>> countAgeByJobbed() {
        return studentService.countAgeByJobbed();
    }

    //工资分布
    @GetMapping("/salary")
    public List<Map<String, Object>> countSalaryByJobbed() {
        return studentService.countSalaryByJobbed();
    }

    @GetMapping("/top5-workplace")
    public List<Map<String, Object>> top5WorkplacesByJobbed() {
        return studentService.top5WorkplacesByJobbed();
    }

    @GetMapping("/statistics")
    public Map<String, Object> getStudentStatistics() {
        int totalStudents = studentService.getTotalStudents();
        int jobbedStudents = studentService.getJobbedStudents();
        double jobbedPercentage = (double) jobbedStudents / totalStudents * 100;

        return Map.of(
                "totalStudents", totalStudents,
                "jobbedStudents", jobbedStudents,
                "jobbedPercentage", jobbedPercentage
        );
    }
}
