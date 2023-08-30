package com.huike.domain.clues.vo;

import lombok.Data;

@Data
public class IndexStatisticsVo {

    private String beginCreateTime;
    private String endCreateTime;
    private Long deptId;
    private Long[] deptIds;

}
