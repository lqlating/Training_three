package com.example.practiceback.mapper;

import com.example.practiceback.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    @Select("SELECT major, COUNT(*) AS employed_count, " +
            "SUM(CASE WHEN jobbed = 1 THEN 1 ELSE 0 END) / COUNT(*) AS employment_rate " +
            "FROM student GROUP BY major")
    List<Map<String, Object>> getEmploymentRateByMajor();

    @Select("SELECT workplace, COUNT(*) AS count FROM student WHERE jobbed = 1 GROUP BY workplace")
    List<Map<String, Object>> countWorkplaceByJobbed();

    @Select("SELECT job, COUNT(*) AS count FROM student WHERE jobbed = 1 GROUP BY job")
    List<Map<String, Object>> countJobByJobbed();

    @Select("SELECT age, COUNT(*) AS count FROM student WHERE jobbed = 1 GROUP BY age")
    List<Map<String, Object>> countAgeByJobbed();

    @Select("SELECT " +
            "CASE " +
            "WHEN salary < 3000 THEN '<3k' " +
            "WHEN salary BETWEEN 3000 AND 6000 THEN '3k-6k' " +
            "WHEN salary BETWEEN 6000 AND 8000 THEN '6k-8k' " +
            "WHEN salary BETWEEN 8000 AND 10000 THEN '8k-10k' " +
            "ELSE '>10k' " +
            "END AS salary_range, " +
            "COUNT(*) AS count " +
            "FROM student " +
            "WHERE jobbed = 1 " +
            "GROUP BY salary_range")
    List<Map<String, Object>> countSalaryByJobbed();

    @Select("SELECT workplace, COUNT(*) AS count " +
            "FROM student " +
            "WHERE jobbed = 1 " +
            "GROUP BY workplace " +
            "ORDER BY count DESC " +
            "LIMIT 5")
    List<Map<String, Object>> top5WorkplacesByJobbed();

    // 新增方法
    @Select("SELECT COUNT(*) FROM student")
    int getTotalStudents();

    @Select("SELECT COUNT(*) FROM student WHERE jobbed = 1")
    int getJobbedStudents();
}
