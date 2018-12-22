package com.yeafel.learning.converter;

import com.yeafel.learning.dataobject.Record;
import com.yeafel.learning.dto.RecordDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yeafel
 * 2018/11/24 17:43
 * Do or Die，To be a better man!
 */
public class Record2RecordDTOConverter {

    public static RecordDTO convert(Record record){

        RecordDTO recordDTO = new RecordDTO();
        BeanUtils.copyProperties(record,recordDTO);
        return recordDTO;
    }

    public static List<RecordDTO> convert(List<Record> recordList){
        return recordList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
