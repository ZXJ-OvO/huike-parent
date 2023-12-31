package com.huike.service.impl;

import com.huike.domain.clues.TbRulePool;
import com.huike.mapper.TbRulePoolMapper;
import com.huike.service.ISysDictDataService;
import com.huike.service.ITbRulePoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 线索池规则Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-16
 */
@Service
public class TbRulePoolServiceImpl implements ITbRulePoolService {
    @Autowired
    private TbRulePoolMapper tbRulePoolMapper;

    @Autowired
    private ISysDictDataService sysDictDataService;

    /**
     * 查询线索池规则
     *
     * @param id 线索池规则ID
     * @return 线索池规则
     */
    @Override
    public TbRulePool selectTbRulePoolById(Long id) {
        return tbRulePoolMapper.selectTbRulePoolById(id);
    }

    @Override
    public TbRulePool selectTbRulePoolByType(String type) {
        return tbRulePoolMapper.selectTbRulePoolByType(type);
    }

    /**
     * 查询线索池规则列表
     *
     * @param tbRulePool 线索池规则
     * @return 线索池规则
     */
    @Override
    public List<TbRulePool> selectTbRulePoolList(TbRulePool tbRulePool) {
        return tbRulePoolMapper.selectTbRulePoolList(tbRulePool);
    }

    /**
     * 新增线索池规则
     *
     * @param tbRulePool 线索池规则
     * @return 结果
     */
    @Override
    public int insertTbRulePool(TbRulePool tbRulePool) {
        return tbRulePoolMapper.insertTbRulePool(tbRulePool);
    }

    /**
     * 修改线索池规则
     *
     * @param tbRulePool 线索池规则
     * @return 结果
     */
    @Override
    public int updateTbRulePool(TbRulePool tbRulePool) {
        return tbRulePoolMapper.updateTbRulePool(tbRulePool);
    }

    /**
     * 批量删除线索池规则
     *
     * @param ids 需要删除的线索池规则ID
     * @return 结果
     */
    @Override
    public int deleteTbRulePoolByIds(Long[] ids) {
        return tbRulePoolMapper.deleteTbRulePoolByIds(ids);
    }

    /**
     * 删除线索池规则信息
     *
     * @param id 线索池规则ID
     * @return 结果
     */
    @Override
    public int deleteTbRulePoolById(Long id) {
        return tbRulePoolMapper.deleteTbRulePoolById(id);
    }
}
