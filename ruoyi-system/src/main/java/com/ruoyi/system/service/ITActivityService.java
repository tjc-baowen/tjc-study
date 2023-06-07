package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TActivity;

/**
 * 活动Service接口
 * 
 * @author 丁方军
 * @date 2023-05-24
 */
public interface ITActivityService 
{
    /**
     * 查询活动
     * 
     * @param id 活动主键
     * @return 活动
     */
    public TActivity selectTActivityById(Long id);

    /**
     * 查询活动列表
     * 
     * @param tActivity 活动
     * @return 活动集合
     */
    public List<TActivity> selectTActivityList(TActivity tActivity);

    /**
     * 新增活动
     * 
     * @param tActivity 活动
     * @return 结果
     */
    public int insertTActivity(TActivity tActivity);

    /**
     * 修改活动
     * 
     * @param tActivity 活动
     * @return 结果
     */
    public int updateTActivity(TActivity tActivity);

    /**
     * 批量删除活动
     * 
     * @param ids 需要删除的活动主键集合
     * @return 结果
     */
    public int deleteTActivityByIds(Long[] ids);

    /**
     * 删除活动信息
     * 
     * @param id 活动主键
     * @return 结果
     */
    public int deleteTActivityById(Long id);
}
