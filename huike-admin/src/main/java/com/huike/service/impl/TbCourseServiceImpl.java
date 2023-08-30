package com.huike.service.impl;

import com.huike.domain.clues.TbCourse;
import com.huike.mapper.TbCourseMapper;
import com.huike.service.ITbCourseService;
import com.huike.utils.DateUtils;
import com.huike.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程管理Service业务层处理
 */
@Service
public class TbCourseServiceImpl implements ITbCourseService {
    @Autowired
    private TbCourseMapper tbCourseMapper;

    /**
     * 查询课程管理
     *
     * @param id 课程管理ID
     * @return 课程管理
     */
    @Override
    public TbCourse selectTbCourseById(Long id) {
        return tbCourseMapper.selectTbCourseById(id);
    }

    /**
     * 查询课程管理列表
     *
     * @param tbCourse 课程管理
     * @return 课程管理
     */
    @Override
    public List<TbCourse> selectTbCourseList(TbCourse tbCourse) {
        return tbCourseMapper.selectTbCourseList(tbCourse);
    }

    /**
     * 新增课程管理
     *
     * @param tbCourse 课程管理
     * @return 结果
     */
    @Override
    public int insertTbCourse(TbCourse tbCourse) {
        tbCourse.setCreateTime(DateUtils.getNowDate());
        tbCourse.setCode(StringUtils.getRandom(8));
        return tbCourseMapper.insertTbCourse(tbCourse);
    }

    /**
     * 修改课程管理
     *
     * @param tbCourse 课程管理
     * @return 结果
     */
    @Override
    public int updateTbCourse(TbCourse tbCourse) {
        return tbCourseMapper.updateTbCourse(tbCourse);
    }

    /**
     * 批量删除课程管理
     *
     * @param ids 需要删除的课程管理ID
     * @return 结果
     */
    @Override
    public int deleteTbCourseByIds(Long[] ids) {
        return tbCourseMapper.deleteTbCourseByIds(ids);
    }

    /**
     * 删除课程管理信息
     *
     * @param id 课程管理ID
     * @return 结果
     */
    @Override
    public int deleteTbCourseById(Long id) {
        return tbCourseMapper.deleteTbCourseById(id);
    }
}
