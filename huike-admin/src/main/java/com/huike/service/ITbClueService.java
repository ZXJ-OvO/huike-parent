package com.huike.service;

import com.huike.domain.clues.TbClue;
import com.huike.domain.clues.TbClueTrackRecord;
import com.huike.domain.clues.dto.ImportResultDTO;
import com.huike.domain.clues.vo.TbClueExcelVo;
import com.huike.domain.clues.vo.TbTrackMsgVO;

import java.util.List;
import java.util.Map;

/**
 * 线索管理Service接口
 *
 * @author ruoyi
 * @date 2021-04-02
 */
public interface ITbClueService {
    /**
     * 查询线索管理
     *
     * @param id 线索管理ID
     * @return 线索管理
     */
    public TbClue selectTbClueById(Long id);

    /**
     * 查询线索管理列表
     *
     * @param tbClue 线索管理
     * @return 线索管理集合
     */
    public List<TbClue> selectTbClueList(TbClue tbClue);


    public List<TbClue> selectTbCluePool(TbClue tbClue);

    /**
     * 新增线索管理
     *
     * @param tbClue 线索管理
     * @return 结果
     */
    public int insertTbClue(TbClue tbClue);

    /**
     * 修改线索管理
     *
     * @param tbClue 线索管理
     * @return 结果
     */
    public int updateTbClue(TbClue tbClue);

    /**
     * 批量删除线索管理
     *
     * @param ids 需要删除的线索管理ID
     * @return 结果
     */
    public int deleteTbClueByIds(Long[] ids);

    /**
     * 删除线索管理信息
     *
     * @param id 线索管理ID
     * @return 结果
     */
    public int deleteTbClueById(Long id);

    /**
     * 导入线索数据
     *
     * @param clueList
     * @return 结果
     */
    public Map<String, Integer> importClues(List<TbClue> clueList);


    public String assign(Long[] clueIds, Long userId);


    public String gain(Long[] clueIds, Long userId);


    /**
     * 伪线索
     *
     * @param id
     * @param reason
     * @param remark
     * @return
     */
    public int falseClue(Long id, String reason, String remark);


    /**
     * 修改下次跟进时间及线索状态
     *
     * @param clueId
     * @param status
     * @return
     */
    public int updateStatus(Long clueId, String status);

    /**
     * 批量导入
     *
     * @param list
     */
    public Map<String, Integer> addTbClue(List<TbClueExcelVo> list);

    /**
     * 判断用户手机号是否存在
     *
     * @param phone
     * @return
     */
    public boolean checkCluePhoneExis(String phone);

    /**
     * 批量导入数据
     *
     * @param data
     * @return
     */
    ImportResultDTO importCluesData(TbClueExcelVo data);

    List<TbClueTrackRecord> selectTbClueTrackList();

    void selectTbClueByStatusAndLatest();

    void sendMsg(TbTrackMsgVO tbTrackMsgVO, String html, String title);

    void selectTbBusinessByStatusAndLatest();

    void selectThisReportData();

}
