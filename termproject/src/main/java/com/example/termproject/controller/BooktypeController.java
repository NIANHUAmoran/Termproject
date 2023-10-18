package com.example.termproject.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.example.termproject.entity.BooktypeEntity;
import com.example.termproject.service.BooktypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 藏书类型表
 *
 * @author 张传伟
 * @date 2023-10-17 12:47:40
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/booktype" )
@Tag(description = "booktype" , name = "藏书类型表管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class BooktypeController {

    private final  BooktypeService booktypeService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param booktype 藏书类型表
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('termproject_booktype_view')" )
    public R getBooktypePage(@ParameterObject Page page, @ParameterObject BooktypeEntity booktype) {
        LambdaQueryWrapper<BooktypeEntity> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(StrUtil.isNotBlank(booktype.getTypename()),BooktypeEntity::getTypename,booktype.getTypename());
		wrapper.eq(StrUtil.isNotBlank(booktype.getDemo()),BooktypeEntity::getDemo,booktype.getDemo());
        return R.ok(booktypeService.page(page, wrapper));
    }


    /**
     * 通过id查询藏书类型表
     * @param typeid id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{typeid}" )
//    @PreAuthorize("@pms.hasPermission('termproject_booktype_view')" )
    public R getById(@PathVariable("typeid" ) Long typeid) {
        return R.ok(booktypeService.getById(typeid));
    }

    /**
     * 新增藏书类型表
     * @param booktype 藏书类型表
     * @return R
     */
    @Operation(summary = "新增藏书类型表" , description = "新增藏书类型表" )
    @SysLog("新增藏书类型表" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('termproject_booktype_add')" )
    public R save(@RequestBody BooktypeEntity booktype) {
        return R.ok(booktypeService.save(booktype));
    }

    /**
     * 修改藏书类型表
     * @param booktype 藏书类型表
     * @return R
     */
    @Operation(summary = "修改藏书类型表" , description = "修改藏书类型表" )
    @SysLog("修改藏书类型表" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('termproject_booktype_edit')" )
    public R updateById(@RequestBody BooktypeEntity booktype) {
        return R.ok(booktypeService.updateById(booktype));
    }

    /**
     * 通过id删除藏书类型表
     * @param ids typeid列表
     * @return R
     */
    @Operation(summary = "通过id删除藏书类型表" , description = "通过id删除藏书类型表" )
    @SysLog("通过id删除藏书类型表" )
    @DeleteMapping
//    @PreAuthorize("@pms.hasPermission('termproject_booktype_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(booktypeService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param booktype 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
//    @PreAuthorize("@pms.hasPermission('termproject_booktype_export')" )
    public List<BooktypeEntity> export(BooktypeEntity booktype,Long[] ids) {
        return booktypeService.list(Wrappers.lambdaQuery(booktype).in(ArrayUtil.isNotEmpty(ids), BooktypeEntity::getTypeid, ids));
    }
}