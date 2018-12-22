package com.yeafel.learning.service.impl;


import com.yeafel.learning.dataobject.Course;
import com.yeafel.learning.enums.ResultEnum;
import com.yeafel.learning.exception.LearningException;
import com.yeafel.learning.repository.CourseRepository;
import com.yeafel.learning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public Course create(Course course) {
        return  courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        Course result;
        if (course.getCourseId()!= null){
              result = courseRepository.save(course);
        }else {
            throw new LearningException(ResultEnum.COURSE_NOT_EXIST);
        }
        return  result;
    }

    @Override
    public Page<Course> findCourseIfCourseNameIsNotNull(String courseName, Pageable pageable) {

        Page<Course> coursePage = courseRepository.findCourseIfCourseNameIsNotNull(courseName,pageable);

        return coursePage;
    }

    @Override
    public Integer countCourseForPage(String courseName) {
        Integer count = courseRepository.countCourseForPage(courseName);

        return count;
    }

    @Override
    public Course findByCourseId(Long courseId) {

        Course course = courseRepository.findByCourseId(courseId);
        if (course == null){
            throw new LearningException(ResultEnum.USER_NOT_EXIST);
        }

        return course;
    }

    @Override
    public void deleteByCourseId(Long courseId) {
        courseRepository.deleteByCourseId(courseId);
    }
}
