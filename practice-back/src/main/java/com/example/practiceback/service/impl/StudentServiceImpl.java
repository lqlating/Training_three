package com.example.practiceback.service.impl;

import com.example.practiceback.mapper.StudentMapper;
import com.example.practiceback.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Map<String, Object>> getEmploymentRateByMajor() {
        return studentMapper.getEmploymentRateByMajor();
    }

    @Override
    public List<Map<String, Object>> countWorkplaceByJobbed() {
        return studentMapper.countWorkplaceByJobbed();
    }

    @Override
    public List<Map<String, Object>> countJobByJobbed() {
        return studentMapper.countJobByJobbed();
    }

    @Override
    public List<Map<String, Object>> countAgeByJobbed() {
        return studentMapper.countAgeByJobbed();
    }

    @Override
    public List<Map<String, Object>> countSalaryByJobbed() {
        return studentMapper.countSalaryByJobbed();
    }

    @Override
    public List<Map<String, Object>> top5WorkplacesByJobbed() {
        return studentMapper.top5WorkplacesByJobbed();
    }

    @Override
    public int getTotalStudents() {
        return studentMapper.getTotalStudents();
    }

    @Override
    public int getJobbedStudents() {
        return studentMapper.getJobbedStudents();
    }
}
