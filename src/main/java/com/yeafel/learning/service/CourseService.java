package com.yeafel.learning.service;


import com.yeafel.learning.dataobject.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CourseService {

    Course create(Course course);

    Course updateCourse(Course course);

    /** layui查询结果 .*/
    Page<Course> findCourseIfCourseNameIsNotNull(String courseName, Pageable pageable);


    /** 查询总数，可传入名称 .*/
    Integer countCourseForPage(String courseName);

    Course findByCourseId(Long courseId);

    /** 删除课程 */
    void deleteByCourseId(Long courseId);
}
