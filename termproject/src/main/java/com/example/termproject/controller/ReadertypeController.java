package com.example.termproject.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.example.termproject.entity.ReadertypeEntity;
import com.example.termproject.service.ReadertypeService;
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
 * 读者类型
 *
 * @author 张传伟
 * @date 2023-10-17 12:48:15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/readertype" )
@Tag(description = "readertype" , name = "读者类型管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class ReadertypeController {

    private final  ReadertypeService readertypeService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param readertype 读者类型
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('termproject_readertype_view')" )
    public R getReadertypePage(@ParameterObject Page page, @ParameterObject ReadertypeEntity readertype) {
        LambdaQueryWrapper<ReadertypeEntity> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(StrUtil.isNotBlank(readertype.getTypename()),ReadertypeEntity::getTypename,readertype.getTypename());
		wrapper.eq(StrUtil.isNotBlank(readertype.getDemo()),ReadertypeEntity::getDemo,readertype.getDemo());
        return R.ok(readertypeService.page(page, wrapper));
    }


    /**
     * 通过id查询读者类型
     * @param typeid id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{typeid}" )
//    @PreAuthorize("@pms.hasPermission('termproject_readertype_view')" )
    public R getById(@PathVariable("typeid" ) Long typeid) {
        return R.ok(readertypeService.getById(typeid));
    }

    /**
     * 新增读者类型
     * @param readertype 读者类型
     * @return R
     */
    @Operation(summary = "新增读者类型" , description = "新增读者类型" )
    @SysLog("新增读者类型" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('termproject_readertype_add')" )
    public R save(@RequestBody ReadertypeEntity readertype) {
        return R.ok(readertypeService.save(readertype));
    }

    /**
     * 修改读者类型
     * @param readertype 读者类型
     * @return R
     */
    @Operation(summary = "修改读者类型" , description = "修改读者类型" )
    @SysLog("修改读者类型" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('termproject_readertype_edit')" )
    public R updateById(@RequestBody ReadertypeEntity readertype) {
        return R.ok(readertypeService.updateById(readertype));
    }

    /**
     * 通过id删除读者类型
     * @param ids typeid列表
     * @return R
     */
    @Operation(summary = "通过id删除读者类型" , description = "通过id删除读者类型" )
    @SysLog("通过id删除读者类型" )
    @DeleteMapping
//    @PreAuthorize("@pms.hasPermission('termproject_readertype_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(readertypeService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param readertype 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
//    @PreAuthorize("@pms.hasPermission('termproject_readertype_export')" )
    public List<ReadertypeEntity> export(ReadertypeEntity readertype,Long[] ids) {
        return readertypeService.list(Wrappers.lambdaQuery(readertype).in(ArrayUtil.isNotEmpty(ids), ReadertypeEntity::getTypeid, ids));
    }
}