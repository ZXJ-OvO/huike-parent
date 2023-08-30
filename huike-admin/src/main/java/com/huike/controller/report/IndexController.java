package com.huike.controller.report;


import com.huike.domain.clues.vo.IndexStatisticsVo;
import com.huike.domain.common.AjaxResult;
import com.huike.service.IReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(tags = "数据统计")
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IReportService reportService;


    /**
     * 首页概要信息统计
     *
     * @param request
     * @return
     */
    @ApiOperation("首页概要信息统计")
    @GetMapping
    public AjaxResult contractStatistics(IndexStatisticsVo request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        request.setBeginCreateTime(request.getBeginCreateTime() + " 00:00:00");
        request.setEndCreateTime(request.getEndCreateTime() + " 23:59:59");
        Map<String, Object> result = reportService.getcontractsBasicInfo(request, sdf.format(new Date()));
        return AjaxResult.success(result);
    }

    /**
     * 首页--基础数据统计
     * @param beginCreateTime
     * @param endCreateTime
     * @return
     */
    /*@GetMapping("/getBaseInfo")
    public AjaxResult getBaseInfo(@RequestParam("beginCreateTime") String beginCreateTime,
                                  @RequestParam("endCreateTime") String endCreateTime){
        return AjaxResult.success(reportService.getBaseInfo(beginCreateTime,endCreateTime));
    }*/

    /**
     * 首页--获取今日简报数据
     * @return
     */
    /*@GetMapping("/getTodayInfo")
    public AjaxResult getTodayInfo(){
        return AjaxResult.success(reportService.getTodayInfo(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
    }*/

    /**
     * 首页--获取待办数据
     * @return
     */
    /*@GetMapping("/getTodoInfo")
    public AjaxResult getTodoInfo(@RequestParam("beginCreateTime") String beginCreateTime,
                                  @RequestParam("endCreateTime") String endCreateTime){
        return AjaxResult.success(reportService.getTodoInfo(beginCreateTime,endCreateTime));
    }*/


    /**
     * 线索转换龙虎榜
     *
     * @param request
     * @return
     */
    @ApiOperation("线索转换龙虎榜")
    @GetMapping("/salesStatistic")
    public AjaxResult salesStatistics(IndexStatisticsVo request) {
        request.setBeginCreateTime(request.getBeginCreateTime() + " 00:00:00");
        request.setEndCreateTime(request.getEndCreateTime() + " 23:59:59");
        List<Map<String, Object>> list = reportService.clueChangeStatisticsForIndex(request);
        return AjaxResult.success(list);
    }


    /**
     * 商机转换龙虎榜
     *
     * @param request
     * @return
     */
    @ApiOperation("商机转换龙虎榜")
    @GetMapping("/businessChangeStatistics")
    public AjaxResult businessChangeStatistics(IndexStatisticsVo request) {
        request.setBeginCreateTime(request.getBeginCreateTime() + " 00:00:00");
        request.setEndCreateTime(request.getEndCreateTime() + " 23:59:59");
        List<Map<String, Object>> list = reportService.businessChangeStatisticsForIndex(request);
        return AjaxResult.success(list);
    }
}
