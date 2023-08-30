package com.huike.service;

import com.huike.domain.contract.TbContract;

import java.util.List;

/**
 * 合同Service接口
 *
 * @author ruoyi
 * @date 2021-05-19
 */
public interface ITbContractService {
    /**
     * 查询合同
     *
     * @param id 合同ID
     * @return 合同
     */
    public TbContract selectTbContractById(Long id);


    public List<TbContract> selectTbContract(TbContract queryConditon);

    /**
     * 新增合同
     *
     * @param tbContract 合同
     * @return 结果
     */
    public int insertTbContract(TbContract tbContract);


    public int changeContract(Long businessId, TbContract tbContract);

    /**
     * 修改合同
     *
     * @param tbContract 合同
     * @return 结果
     */
    public int updateTbContract(TbContract tbContract);


}
