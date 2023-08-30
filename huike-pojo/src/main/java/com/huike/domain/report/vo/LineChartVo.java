package com.huike.domain.report.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 折线图
 */
@Data
public class LineChartVo {

    private List<String> xAxis = new ArrayList<>();
    private List<LineSeriesVo> series = new ArrayList<>();

}
