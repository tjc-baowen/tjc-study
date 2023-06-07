package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TBible;
import com.ruoyi.system.service.ITBibleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 圣经Controller
 * 
 * @author ruoyi
 * @date 2023-05-25
 */
@RestController
@RequestMapping("/system/bible")
public class TBibleController extends BaseController
{
    @Autowired
    private ITBibleService tBibleService;

    /**
     * 查询圣经列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TBible tBible)
    {
        startPage();
        List<TBible> list = tBibleService.selectTBibleList(tBible);
        return getDataTable(list);
    }

    /**
     * 导出圣经列表
     */
    @Log(title = "圣经", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TBible tBible)
    {
        List<TBible> list = tBibleService.selectTBibleList(tBible);
        ExcelUtil<TBible> util = new ExcelUtil<TBible>(TBible.class);
        util.exportExcel(response, list, "圣经数据");
    }

    /**
     * 获取圣经详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(tBibleService.selectTBibleById(id));
    }

    /**
     * 新增圣经
     */
    @Log(title = "圣经", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TBible tBible)
    {
        return toAjax(tBibleService.insertTBible(tBible));
    }

    /**
     * 修改圣经
     */
    @Log(title = "圣经", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TBible tBible)
    {
        return toAjax(tBibleService.updateTBible(tBible));
    }

    /**
     * 删除圣经
     */
    @Log(title = "圣经", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(tBibleService.deleteTBibleByIds(ids));
    }
}
