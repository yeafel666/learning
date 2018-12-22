package com.yeafel.learning.service;

import com.yeafel.learning.dto.CoursewareDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * identity: Administrator
 * Created By Yeafel
 * 2018/11/14 21:10
 * Do or Die，To be a better man!
 */
public interface CoursewareService{

    /**
     * 插入课件
     * @param coursewareDTO
     * @return
     */
    CoursewareDTO create(CoursewareDTO coursewareDTO);

    /**
     *  根据课件id查询某个课件
     * @param coursewareId
     * @return
     */
    CoursewareDTO findByCoursewareId(Long coursewareId );

    /**
     * 根据课件名查询某个课件
     * @param courseName
     * @return
     */
    CoursewareDTO findByCoursewareName(String courseName);


    /**
     * 查询所有课件
     * @param pageable
     * @return
     */
    Page<CoursewareDTO> findAll(Pageable pageable);



    /**
     * 删除某个课件
     * @param coursewareId
     */
    void deleteByCoursewareId(Long coursewareId);

    /**
     * 服从layui查询接口
     * @param coursewareName
     * @param courseName
     * @param pageable
     * @return
     */
    Page<CoursewareDTO> findByCondition(String coursewareName,String courseName, Pageable pageable);
    /**
     * 服从layui查询接口，求总数
     * @param coursewareName
     * @return
     */
    Integer countCoursewareForPage(String coursewareName,String courseName);


    /**
     * 更新课件信息
     * @param coursewareDTO
     * @return
     */
    CoursewareDTO updateCourseware(CoursewareDTO coursewareDTO);




}
