package com.huike.mapper;

import com.huike.domain.clues.TbActivity;
import com.huike.domain.clues.TbClueTrackRecord;
import com.huike.domain.clues.vo.MailReportVO;
import com.huike.domain.clues.vo.TbTrackMsgVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 活动管理Mapper接口
 */
public interface TbActivityMapper {
    /**
     * 查询活动管理
     *
     * @param id 活动管理ID
     * @return 活动管理
     */
    public TbActivity selectTbActivityById(Long id);


    public TbActivity selectTbActivityByCode(@Param("code") String code);


    /**
     * 查询活动管理列表
     *
     * @param tbActivity 活动管理
     * @return 活动管理集合
     */
    public List<TbActivity> selectTbActivityList(TbActivity tbActivity);


    public List<String> selectAllCode();

    /**
     * 新增活动管理
     *
     * @param tbActivity 活动管理
     * @return 结果
     */
    public int insertTbActivity(TbActivity tbActivity);

    /**
     * 修改活动管理
     *
     * @param tbActivity 活动管理
     * @return 结果
     */
    public int updateTbActivity(TbActivity tbActivity);

    /**
     * 删除活动管理
     *
     * @param id 活动管理ID
     * @return 结果
     */
    public int deleteTbActivityById(Long id);

    /**
     * 批量删除活动管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbActivityByIds(Long[] ids);

    List<TbClueTrackRecord> selectTbClueTrackList();

    /**
     * 跟进线索
     *
     * @return
     */
    List<TbTrackMsgVO> selectTbClueByStatusAndLatest();

    List<TbTrackMsgVO> selectTbBusinessByStatusAndLatest();

    List<MailReportVO> selectThisReportData();

}
