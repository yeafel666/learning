package com.yeafel.learning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.yeafel.learning.dataobject.Course;
import com.yeafel.learning.dataobject.Courseware;
import com.yeafel.learning.dto.CoursewareDTO;
import com.yeafel.learning.enums.ResultEnum;
import com.yeafel.learning.exception.LearningException;
import com.yeafel.learning.repository.CourseRepository;
import com.yeafel.learning.repository.CoursewareRepository;
import com.yeafel.learning.service.CoursewareService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.yeafel.learning.converter.Courseware2CoursewareDTOConverter.convert;

/**
 * identity: Administrator
 * Created By Yeafel
 * 2018/11/14 21:21
 * Do or Die，To be a better man!
 */
@Service
@Transactional
public class CoursewareServiceImpl implements CoursewareService {

    @Autowired
    private CoursewareRepository coursewareRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public CoursewareDTO create(CoursewareDTO coursewareDTO) {

        Courseware courseware = new Courseware();

        Course course = courseRepository.findByCourseName(coursewareDTO.getCourseName());

        BeanUtils.copyProperties(coursewareDTO,courseware);

        //先把DTO的值拷贝过来，再给course设置值，否则被覆盖为空值。
        courseware.setCourseId(course.getCourseId());

        Courseware courseware1 = coursewareRepository.save(courseware);
        return convert(courseware1);
    }

    @Override
    public CoursewareDTO findByCoursewareId(Long coursewareId) {
        Courseware courseware = coursewareRepository.findByCoursewareId(coursewareId);

        if (courseware == null){
            throw new LearningException(ResultEnum.COURSEWARE_NOT_EXIST);
        }

        CoursewareDTO coursewareDTO = convert(courseware);
        Course course = courseRepository.findByCourseId(coursewareDTO.getCourseId());
        coursewareDTO.setCourseName(course.getCourseName());

        return coursewareDTO;

    }

    @Override
    public CoursewareDTO findByCoursewareName(String courseName) {
        Courseware courseware = coursewareRepository.findByCoursewareName(courseName);
        if (courseware == null){
            throw new LearningException(ResultEnum.COURSEWARE_NOT_EXIST);
        }

        CoursewareDTO coursewareDTO = convert(courseware);

        return coursewareDTO;

    }

    @Override
    public Page<CoursewareDTO> findAll(Pageable pageable) {
        Page<Courseware> coursewarePage =  coursewareRepository.findAll(pageable);

        List<CoursewareDTO> coursewareDTOList = convert(coursewarePage.getContent());

        Page<CoursewareDTO> coursewareDTOPage = new PageImpl<CoursewareDTO>(coursewareDTOList,pageable,coursewarePage.getTotalElements());

        return coursewareDTOPage;
    }


    @Override
    public void deleteByCoursewareId(Long coursewareId) {
         coursewareRepository.deleteByCoursewareId(coursewareId);
    }

    @Override
    public Page<CoursewareDTO> findByCondition(String coursewareName,String courseName, Pageable pageable) {

        Long cid = null;

        Course course = courseRepository.findByCourseName(courseName);

        if (course != null){
            cid = course.getCourseId();
        }

        Page<Courseware> coursewarePage = coursewareRepository.findByCondition(coursewareName,cid,pageable);

        List<CoursewareDTO> coursewareDTOList = convert(coursewarePage.getContent());

        for (CoursewareDTO coursewareDTO : coursewareDTOList){
            Course course1 = courseRepository.findByCourseId(coursewareDTO.getCourseId());
            coursewareDTO.setCourseName(course1.getCourseName());
        }

        Page<CoursewareDTO> coursewareDTOPage = new PageImpl<CoursewareDTO>(coursewareDTOList,pageable,coursewarePage.getTotalElements());

        return coursewareDTOPage;

    }

    @Override
    public Integer countCoursewareForPage(String coursewareName,String courseName) {
        Long cid = null;
        Course course = courseRepository.findByCourseName(courseName);
        if (course != null){
            cid = course.getCourseId();
        }
        return coursewareRepository.countCoursewareForPage(coursewareName,cid);

    }

    @Override
    public CoursewareDTO updateCourseware(CoursewareDTO coursewareDTO) {
      Courseware courseware;
        if (coursewareDTO.getCoursewareId() != null){
            courseware = coursewareRepository.findByCoursewareId(coursewareDTO.getCoursewareId());
        }else {
            throw new LearningException(ResultEnum.COURSEWARE_NOT_EXIST);
        }

        /**
         *      属性拷贝，从src到target。  倘若src里面有空值，则不拷贝过去
         */
        BeanUtil.copyProperties(coursewareDTO,courseware,true, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        Courseware courseware1 = coursewareRepository.save(courseware);
        return convert(courseware1);
    }
}
