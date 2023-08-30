package com.huike.domain.report.vo;

import lombok.Data;

@Data
public class IndexVo {

    private Integer cluesNum = 100;  //线索数目
    private Integer businessNum = 100;  //商机数目
    private Integer contractNum = 100;  //合同数目
    private Double salesAmount = 100.00;  //销售金额

    private Integer todayCluesNum = 0;  //今日线索数目
    private Integer todayBusinessNum = 0;  //今日商机数目
    private Integer todayContractNum = 0;  //今日合同数目
    private Double todaySalesAmount = 1.0;  //今日销售金额

    //待办
    private Integer transferNum = 0;  //转派
    private Integer tofollowedCluesNum = 0;  //待跟进线索数目
    private Integer tofollowedBusinessNum = 0;  //待跟进商机数目
    private Integer toallocatedCluesNum = 0;  //待分配线索数目
    private Integer toallocatedBusinessNum = 0;  //待分配商机数目

}
