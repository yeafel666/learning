package com.yeafel.learning.dataobject;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 *  上传进度实体类
 * Created by kangyifan on 2018/11/13 10:37
 */
@Component
@Data
public class Progress {

    private Long readBytes = 0L; //到目前为止读取文件的比特数

    private Long allBytes = 0L;    //文件总大小

    private Integer currentIndex;      //目前正在读取第几个文件


}
