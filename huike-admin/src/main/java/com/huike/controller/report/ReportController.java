package com.huike.controller.report;

import com.huike.controller.core.BaseController;
import com.huike.domain.clues.TbActivity;
import com.huike.domain.clues.TbClue;
import com.huike.domain.common.AjaxResult;
import com.huike.domain.contract.TbContract;
import com.huike.domain.report.vo.ActivityStatisticsVo;
import com.huike.domain.report.vo.LineChartVo;
import com.huike.domain.report.vo.VulnerabilityMapVo;
import com.huike.page.TableDataInfo;
import com.huike.service.IReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(tags = "数据统计")
@RestController
@RequestMapping("/report")
public class ReportController extends BaseController {

    @Autowired
    private IReportService reportService;

    /**
     * 合同统计
     *
     * @param beginCreateTime
     * @param endCreateTime
     * @return
     */
    @ApiOperation("合同统计")
    @GetMapping("/contractStatistics/{beginCreateTime}/{endCreateTime}")
    public LineChartVo contractStatistics(@PathVariable String beginCreateTime, @PathVariable String endCreateTime) {
        return reportService.contractStatistics(beginCreateTime, endCreateTime);
    }

    /**
     * 销售统计
     *
     * @param beginCreateTime
     * @param endCreateTime
     * @return
     */
    @ApiOperation("销售统计")
    @GetMapping("/salesStatistics/{beginCreateTime}/{endCreateTime}")
    public LineChartVo salesStatistics(@PathVariable String beginCreateTime, @PathVariable String endCreateTime) {
        return reportService.salesStatistics(beginCreateTime, endCreateTime);
    }

    /**
     * 学科分布统计
     *
     * @param beginCreateTime
     * @param endCreateTime
     * @return
     */
    @ApiOperation("学科分布统计")
    @GetMapping("/subjectStatistics/{beginCreateTime}/{endCreateTime}")
    public AjaxResult subjectStatistics(@PathVariable String beginCreateTime, @PathVariable String endCreateTime) {
        return AjaxResult.success(reportService.subjectStatistics(beginCreateTime, endCreateTime));
    }

    /**
     * 线索统计
     *
     * @param beginCreateTime
     * @param endCreateTime
     * @return
     */
    @ApiOperation("线索统计")
    @GetMapping("/cluesStatistics/{beginCreateTime}/{endCreateTime}")
    public LineChartVo cluesStatistics(@PathVariable String beginCreateTime, @PathVariable String endCreateTime) {
        return reportService.cluesStatistics(beginCreateTime, endCreateTime);
    }

    /**
     * 渠道统计
     *
     * @param beginCreateTime
     * @param endCreateTime
     * @return
     */
    @ApiOperation("渠道统计")
    @GetMapping("/chanelStatistics/{beginCreateTime}/{endCreateTime}")
    public AjaxResult chanelStatistics(@PathVariable String beginCreateTime, @PathVariable String endCreateTime) {
        return AjaxResult.success(reportService.chanelStatistics(beginCreateTime, endCreateTime));
    }


    /**
     * 活动统计
     *
     * @param beginCreateTime
     * @param endCreateTime
     * @return
     */
    @ApiOperation("活动统计")
    @GetMapping("/activityStatistics/{beginCreateTime}/{endCreateTime}")
    public AjaxResult activityStatistics(@PathVariable String beginCreateTime, @PathVariable String endCreateTime) {
        return AjaxResult.success(reportService.activityStatistics(beginCreateTime, endCreateTime));
    }

    /**
     * 查询合同列表
     */
    @ApiOperation("查询合同列表")
    @GetMapping("/contractStatisticsList")
    public TableDataInfo contractStatisticsList(TbContract contract) {
        startPage();
        List<TbContract> list = reportService.contractReportList(contract);
        return getDataTable(list);
    }


    /**
     * 销售统计部门报表
     *
     * @param beginCreateTime
     * @param endCreateTime
     * @return
     */
    @ApiOperation("销售统计部门报表")
    @GetMapping("/deptStatisticsList/{beginCreateTime}/{endCreateTime}")
    public TableDataInfo deptStatisticsList(@PathVariable String beginCreateTime, @PathVariable String endCreateTime) {
        startPage();
        List<Map<String, Object>> list = reportService.deptStatisticsList(beginCreateTime, endCreateTime);
        return getDataTablePage(list);
    }

    /**
     * 销售统计渠道报表
     *
     * @param beginCreateTime
     * @param endCreateTime
     * @return
     */
    @ApiOperation("销售统计渠道报表")
    @GetMapping("/channelStatisticsList/{beginCreateTime}/{endCreateTime}")
    public TableDataInfo channelStatisticsList(@PathVariable String beginCreateTime, @PathVariable String endCreateTime) {
        startPage();
        List<Map<String, Object>> list = reportService.channelStatisticsList(beginCreateTime, endCreateTime);
        return getDataTablePage(list);
    }

    /**
     * 销售统计归属人报表
     *
     * @param beginCreateTime
     * @param endCreateTime
     * @return
     */
    @ApiOperation("销售统计归属人报表")
    @GetMapping("/ownerShipStatisticsList/{beginCreateTime}/{endCreateTime}")
    public TableDataInfo ownerShipStatisticsList(@PathVariable String beginCreateTime, @PathVariable String endCreateTime) {
        startPage();
        List<Map<String, Object>> list = reportService.ownerShipStatisticsList(beginCreateTime, endCreateTime);
        return getDataTablePage(list);
    }


    /**
     * 线索统计报表
     *
     * @param clue
     * @return
     */
    @ApiOperation("线索统计报表")
    @GetMapping("/cluesStatisticsList")
    public TableDataInfo cluesStatisticsList(TbClue clue) {
        startPage();
        List<TbClue> list = reportService.cluesStatisticsList(clue);
        return getDataTable(list);
    }

    /**
     * 漏斗图数据
     *
     * @param beginCreateTime
     * @param endCreateTime
     * @return
     */
    @ApiOperation("漏斗图数据")
    @GetMapping("/getVulnerabilityMap/{beginCreateTime}/{endCreateTime}")
    public AjaxResult getVulnerabilityMap(@PathVariable String beginCreateTime, @PathVariable String endCreateTime) {
        VulnerabilityMapVo vulnerabilityMapDTO = reportService.getVulnerabilityMap(beginCreateTime, endCreateTime);
        return AjaxResult.success(vulnerabilityMapDTO);
    }

    /**
     * 活动渠道统计
     *
     * @param activity
     * @return
     */
    @ApiOperation("活动渠道统计")
    @GetMapping("/activityStatisticsList")
    public TableDataInfo activityStatisticsList(TbActivity activity) {
        List<ActivityStatisticsVo> list = reportService.activityStatisticsList(activity);
        return getDataTablePage(list);
    }

}
