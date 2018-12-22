package com.yeafel.learning.dto;

import lombok.Data;

/**
 * identity: Administrator
 * Created By Yeafel
 * 2018/11/14 20:50
 * Do or Die，To be a better man!
 */
@Data
public class CoursewareDTO {

    private Long coursewareId;

    private Long courseId;

    /** 播放进度 .*/
    private String duration;

    /** 课件名 .*/
    private String coursewareName;

    /** 课件地址. */
    private String coursewarePath;

    /** 评论 .*/
    private String remark;

    private String courseName;

    /** 封面图片地址 .*/
    private String coursewareImg;
}
