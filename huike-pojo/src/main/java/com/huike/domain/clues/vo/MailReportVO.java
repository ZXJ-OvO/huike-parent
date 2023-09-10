package com.huike.domain.clues.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 跟进线索
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailReportVO {
    private LocalDate date;
    private Integer newClueCount;
    private Integer newBusinessCount;
    private Integer newContractCount;
    private Integer salesCount;
}
