package com.huike.domain.contract.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TransferVo {

    private Long userId;
    private String userName;
    private String phonenumber;
    private int clueNum;
    private int businessNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
