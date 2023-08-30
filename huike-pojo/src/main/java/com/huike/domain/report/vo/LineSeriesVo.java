package com.huike.domain.report.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LineSeriesVo {

    private String name;

    private List<Object> data = new ArrayList<>();

}
