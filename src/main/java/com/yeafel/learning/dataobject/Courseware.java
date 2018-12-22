package com.yeafel.learning.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by kangyifan on 2018/11/5 14:06
 */

@Entity
@Data
public class Courseware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    /** 封面图片地址 .*/
    private String coursewareImg;
}
