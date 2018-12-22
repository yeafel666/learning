package com.yeafel.learning.dto;

import lombok.Data;

/**
 * @author Yeafel
 * 2018/11/24 17:24
 * Do or Die，To be a better man!
 */
@Data
public class RecordDTO {

    private Long recordId;

    /** 用户id .*/
    private Long userId;

    /**姓名 */
    private String userName;

    /** 课件id. */
    private Long coursewareId;

    /** 视频名 .*/
    private String coursewareName;

    /** 当前时间 .*/
    private Double videoCurrentTime;

}
