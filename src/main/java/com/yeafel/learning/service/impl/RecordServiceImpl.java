package com.yeafel.learning.service.impl;

import com.yeafel.learning.dataobject.Courseware;
import com.yeafel.learning.dataobject.Record;
import com.yeafel.learning.dataobject.User;
import com.yeafel.learning.dto.RecordDTO;
import com.yeafel.learning.dto.UserDTO;
import com.yeafel.learning.enums.ResultEnum;
import com.yeafel.learning.exception.LearningException;
import com.yeafel.learning.repository.CoursewareRepository;
import com.yeafel.learning.repository.RecordRepository;
import com.yeafel.learning.repository.UserRepository;
import com.yeafel.learning.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.yeafel.learning.converter.Record2RecordDTOConverter.convert;

/**
 * @author Yeafel
 * 2018/11/22 21:47
 * Do or Die，To be a better man!
 */
@Service
@Transactional
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoursewareRepository coursewareRepository;

    @Override
    public void updateRecord(Record record) {
        if (record.getRecordId()!= null){
            recordRepository.save(record);
        }else {
            throw new LearningException(ResultEnum.RECORD_NOT_EXIST);
        }
    }

    @Override
    public Record create(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public Record findByCoursewareIdAndUserId(Long coursewareId,Long userId) {
        return recordRepository.findByCoursewareIdAndUserId(coursewareId,userId);
    }

    @Override
    public List<Record> findRecordsByRecordIdIsNotNull() {
        return recordRepository.findRecordsByRecordIdIsNotNull();
    }

    @Override
    public Page<RecordDTO> findRecordsByUserId(Long userId, Pageable pageable) {

        Page<Record> recordPage = recordRepository.findRecordsByUserId(userId,pageable);

        List<RecordDTO> recordDTOList = convert(recordPage.getContent());

        for (RecordDTO recordDTO : recordDTOList){
            User user = userRepository.findByUserId(recordDTO.getUserId());
            Courseware courseware = coursewareRepository.findByCoursewareId(recordDTO.getCoursewareId());
            recordDTO.setUserName(user.getUsername());
            recordDTO.setCoursewareName(courseware.getCoursewareName());
        }

        Page<RecordDTO> userDTOPage = new PageImpl<RecordDTO>(recordDTOList,pageable,recordPage.getTotalElements());


        return userDTOPage;
    }

    @Override
    public Integer countRecordsByUserId(Long userId) {
        return recordRepository.countRecordsByUserId(userId);
    }

    @Override
    public void deleteByRecordId(Long recordId) {
        recordRepository.deleteByRecordId(recordId);
    }


}
