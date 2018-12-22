package com.yeafel.learning.repository;

import com.yeafel.learning.dataobject.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *  用户学习进度记录保存
 * @author Yeafel
 * 2018/11/22 21:41
 * Do or Die，To be a better man!
 */
public interface RecordRepository extends JpaRepository<Record,Long> {

    /**
     *  根据视频id和用户id去查找某个人的学习记录
     * @param coursewareId
     * @return
     */
    Record findByCoursewareIdAndUserId(Long coursewareId,Long userId);


    /**
     *   查找数据库里面所有的学习记录
     *  @return
     */
    List<Record> findRecordsByRecordIdIsNotNull();



    Page<Record> findRecordsByUserId(Long userId, Pageable pageable);



    Integer countRecordsByUserId(Long userId);


    void deleteByRecordId(Long recordId);



}
