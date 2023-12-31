package com.huike.mapper;

import com.huike.domain.clues.vo.IndexStatisticsVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 首页统计分析的Mapper
 *
 * @author Administrator
 */
public interface ReportMapper {
    /**=========================================基本数据========================================*/
    /**
     * 获取线索数量
     *
     * @param beginCreateTime 开始时间
     * @param endCreateTime   结束时间
     * @param username        用户名
     * @return
     */
    Integer getCluesNum(@Param("startTime") String beginCreateTime,
                        @Param("endTime") String endCreateTime,
                        @Param("username") String username);

    /**
     * 获取商机数量
     *
     * @param beginCreateTime 开始时间
     * @param endCreateTime   结束时间
     * @param username        用户名
     * @return
     */
    Integer getBusinessNum(@Param("startTime") String beginCreateTime,
                           @Param("endTime") String endCreateTime,
                           @Param("username") String username);

    /**
     * 获取合同数量
     *
     * @param beginCreateTime 开始时间
     * @param endCreateTime   结束时间
     * @param username        用户名
     * @return
     */
    Integer getContractNum(@Param("startTime") String beginCreateTime,
                           @Param("endTime") String endCreateTime,
                           @Param("username") String username);

    /**
     * 获取合同金额
     *
     * @param beginCreateTime 开始时间
     * @param endCreateTime   结束时间
     * @param username        用户名
     * @return
     */
    Double getSalesAmount(@Param("startTime") String beginCreateTime,
                          @Param("endTime") String endCreateTime,
                          @Param("username") String username);

    /**=========================================今日简报========================================*/
    /**
     * 首页--今日简报--今日线索数量
     *
     * @param today    今日
     * @param username 用户名
     * @return
     */
    Integer getTodayCluesNum(@Param("now") String today, @Param("username") String username);

    /**
     * 首页--今日简报--今日商机数量
     *
     * @param today    今日
     * @param username 用户名
     * @return
     */
    Integer getTodayBusinessNum(@Param("now") String today, @Param("username") String username);

    /**
     * 首页--今日简报--今日合同数量
     *
     * @param today    今日
     * @param username 用户名
     * @return
     */
    Integer getTodayContractNum(@Param("now") String today, @Param("username") String username);

    /**
     * 首页--今日简报--今日销售金额
     *
     * @param today    今日
     * @param username 用户名
     * @return
     */
    Double getTodaySalesAmount(@Param("now") String today, @Param("username") String username);


    /**=========================================待办========================================*/
    /**
     * 首页--今日待办--待分配线索数量
     *
     * @param beginCreateTime 开始时间
     * @param endCreateTime   结束时间
     * @param username        用户名
     * @return
     */
    Integer getToallocatedCluesNum(@Param("startTime") String beginCreateTime,
                                   @Param("endTime") String endCreateTime,
                                   @Param("username") String username);

    /**
     * 首页--今日待办--待分配商机数量
     *
     * @param beginCreateTime 开始时间
     * @param endCreateTime   结束时间
     * @param username        用户名
     * @return
     */
    Integer getToallocatedBusinessNum(@Param("startTime") String beginCreateTime,
                                      @Param("endTime") String endCreateTime,
                                      @Param("username") String username);

    /**
     * 首页--今日待办--待跟进线索数量
     *
     * @param beginCreateTime
     * @param endCreateTime
     * @param username
     * @return
     */
    Integer getTofollowedCluesNum(@Param("startTime") String beginCreateTime,
                                  @Param("endTime") String endCreateTime,
                                  @Param("username") String username);

    /**
     * 首页--今日待办--待跟进商机数量
     *
     * @param beginCreateTime 开始时间
     * @param endCreateTime   结束时间
     * @param username        用户名
     * @return
     */
    Integer getTofollowedBusinessNum(@Param("startTime") String beginCreateTime,
                                     @Param("endTime") String endCreateTime,
                                     @Param("username") String username);


    /**
     * 获取基础信息
     *
     * @param request
     * @param now
     * @param username
     * @return
     */
    public Map getBaseData(@Param("indexVo") IndexStatisticsVo request,
                           @Param("now") String now, @Param("username") String username);

    /**
     * 获取今日简报数据
     *
     * @param request
     * @param now
     * @param username
     * @return
     */
    public Map getTodayData(@Param("indexVo") IndexStatisticsVo request,
                            @Param("now") String now, @Param("username") String username);


    /**
     * 获取待办任务
     *
     * @param request
     * @param now
     * @param username
     * @return
     */
    public Map getTodoData(@Param("indexVo") IndexStatisticsVo request,
                           @Param("now") String now, @Param("username") String username);

}
