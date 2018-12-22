package com.yeafel.learning.repository;

import com.yeafel.learning.dataobject.Courseware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * identity: Administrator
 * Created By Yeafel
 * 2018/11/14 20:56
 * Do or Die，To be a better man!
 */
public interface CoursewareRepository  extends JpaRepository<Courseware,Long> {

    /**
     *  根据课件id查询某个课件
     * @param coursewareId
     * @return
     */
    Courseware findByCoursewareId(Long coursewareId);

    /**
     * 根据课件名查询某个课件
     * @param courseName
     * @return
     */
    Courseware findByCoursewareName(String courseName);

    /**
     * 计算总课件数
     * @return
     */
    Integer countCoursewaresByCoursewareIdIsNotNull();

    /**
     * 删除某个课件
     * @param coursewareId
     */
    void deleteByCoursewareId(Long coursewareId);

    /**
     *   服从layui查询接口
     * @param coursewareName
     * @param pageable
     * @return
     */
    @Transactional
    @Query(value = "select * from courseware where if(?1 !='',courseware_name=?1,1=1) and if(?2 !='',course_id=?2,2=2) ",nativeQuery = true)
    Page<Courseware> findByCondition(String coursewareName,Long courseId, Pageable pageable);

    /**
     * 服从layui查询接口，求总数
     * @param coursewareName
     * @return
     */
    @Transactional
    @Query(value = "select count(*) from courseware where if(?1 !='',courseware_name=?1,1=1) and if(?2 !='',course_id=?2,2=2) ",nativeQuery = true)
    Integer countCoursewareForPage(String coursewareName,Long courseId);
}
