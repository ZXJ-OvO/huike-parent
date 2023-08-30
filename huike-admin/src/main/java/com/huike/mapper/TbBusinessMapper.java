package com.huike.mapper;

import com.huike.domain.business.TbBusiness;
import com.huike.domain.clues.vo.IndexStatisticsVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商机Mapper接口
 *
 * @date 2021-04-25
 */
public interface TbBusinessMapper {
    /**
     * 查询商机
     *
     * @param id 商机ID
     * @return 商机
     */
    public TbBusiness selectTbBusinessById(Long id);

    /**
     * 统计商机数
     *
     * @param beginCreateTime
     * @param endCreateTime
     * @return
     */
    public int businessNumsFromClue(@Param("beginCreateTime") String beginCreateTime, @Param("endCreateTime") String endCreateTime);


    /**
     * 查询商机列表
     *
     * @param tbBusiness 商机
     * @return 商机集合
     */
    public List<TbBusiness> selectTbBusinessList(TbBusiness tbBusiness);


    public List<TbBusiness> selectTbBusinessPool(TbBusiness tbBusiness);


    /**
     * 新增商机
     *
     * @param tbBusiness 商机
     * @return 结果
     */
    public int insertTbBusiness(TbBusiness tbBusiness);

    /**
     * 修改商机
     *
     * @param tbBusiness 商机
     * @return 结果
     */
    public int updateTbBusiness(TbBusiness tbBusiness);

    /**
     * 删除商机
     *
     * @param id 商机ID
     * @return 结果
     */
    public int deleteTbBusinessById(Long id);

    /**
     * 批量删除商机
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbBusinessByIds(Long[] ids);

    /**
     * 跟新商机的状态和时间
     *
     * @param id
     * @param status
     * @return
     */
    public int resetNextTimeAndStatus(@Param("id") Long id, @Param("status") String status);


    public int setTransfer(@Param("id") Long id, @Param("status") String status);

    public void updateBusinessEndTimeById(@Param("id") Long id, @Param("endTime") Date endTime);

    public int countAllBusiness(@Param("beginCreateTime") String beginCreateTime, @Param("endCreateTime") String endCreateTime);

    public List<Map<String, Object>> countAllContractByUser(@Param("indexVo") IndexStatisticsVo vo);

    /**
     * 根据手机号查询商机
     *
     * @param phone
     * @return
     */
    public TbBusiness selectTbBusinessByPhone(String phone);
}
