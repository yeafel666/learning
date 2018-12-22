package com.yeafel.learning.converter;

import com.yeafel.learning.dataobject.Courseware;
import com.yeafel.learning.dto.CoursewareDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * identity: Administrator
 * Created By Yeafel
 * 2018/11/14 20:52
 * Do or Die，To be a better man!
 */
public class Courseware2CoursewareDTOConverter {

    public static CoursewareDTO convert(Courseware courseware){

        CoursewareDTO coursewareDTO = new CoursewareDTO();
        BeanUtils.copyProperties(courseware,coursewareDTO);
        return coursewareDTO;
    }

    public static List<CoursewareDTO> convert(List<Courseware> coursewareList){
        return coursewareList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
