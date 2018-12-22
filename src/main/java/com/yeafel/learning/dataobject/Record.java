package com.yeafel.learning.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * identity: Administrator
 * Created By Yeafel
 * 2018/11/22 20:35
 * Do or Die，To be a better man!
 */
@Entity
@Data
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    /** 用户id .*/
    private Long userId;

    /** 课件id. */
    private Long coursewareId;

    /** 当前时间 .*/
    private Double videoCurrentTime;

}
