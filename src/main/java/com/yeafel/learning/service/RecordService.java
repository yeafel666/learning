package com.yeafel.learning.service;

import com.yeafel.learning.dataobject.Record;
import com.yeafel.learning.dto.RecordDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Yeafel
 * 2018/11/22 21:44
 * Do or Die，To be a better man!
 */
public interface RecordService  {

    void updateRecord(Record record);

    Record create(Record record);

    /**
     *  根据视频id与用户id去查找某个人的学习记录
     * @param coursewareId
     * @return
     */
    Record findByCoursewareIdAndUserId(Long coursewareId,Long userId);


    /**
     *   查找数据库里面所有的学习记录
     *  @return
     */
    List<Record> findRecordsByRecordIdIsNotNull();


    Page<RecordDTO> findRecordsByUserId(Long userId, Pageable pageable);


    Integer countRecordsByUserId(Long userId);


    void deleteByRecordId(Long recordId);


}
