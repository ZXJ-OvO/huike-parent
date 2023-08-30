package com.huike.service;

import com.huike.domain.clues.TbCourse;

import java.util.List;

/**
 * 课程管理Service接口
 */
public interface ITbCourseService {
    /**
     * 查询课程管理
     *
     * @param id 课程管理ID
     * @return 课程管理
     */
    public TbCourse selectTbCourseById(Long id);

    /**
     * 查询课程管理列表
     *
     * @param tbCourse 课程管理
     * @return 课程管理集合
     */
    public List<TbCourse> selectTbCourseList(TbCourse tbCourse);

    /**
     * 新增课程管理
     *
     * @param tbCourse 课程管理
     * @return 结果
     */
    public int insertTbCourse(TbCourse tbCourse);

    /**
     * 修改课程管理
     *
     * @param tbCourse 课程管理
     * @return 结果
     */
    public int updateTbCourse(TbCourse tbCourse);

    /**
     * 批量删除课程管理
     *
     * @param ids 需要删除的课程管理ID
     * @return 结果
     */
    public int deleteTbCourseByIds(Long[] ids);

    /**
     * 删除课程管理信息
     *
     * @param id 课程管理ID
     * @return 结果
     */
    public int deleteTbCourseById(Long id);
}
