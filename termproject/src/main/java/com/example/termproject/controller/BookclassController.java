package com.example.termproject.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.example.termproject.entity.BookclassEntity;
import com.example.termproject.service.BookclassService;
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
 * 学科类型表
 *
 * @author 张传伟
 * @date 2023-10-17 12:40:10
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookclass" )
@Tag(description = "bookclass" , name = "学科类型表管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class BookclassController {

    private final  BookclassService bookclassService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param bookclass 学科类型表
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('termproject_bookclass_view')" )
    public R getBookclassPage(@ParameterObject Page page, @ParameterObject BookclassEntity bookclass) {
        LambdaQueryWrapper<BookclassEntity> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(StrUtil.isNotBlank(bookclass.getClassname()),BookclassEntity::getClassname,bookclass.getClassname());
		wrapper.eq(StrUtil.isNotBlank(bookclass.getDemo()),BookclassEntity::getDemo,bookclass.getDemo());
        return R.ok(bookclassService.page(page, wrapper));
    }


    /**
     * 通过id查询学科类型表
     * @param classid id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{classid}" )
//    @PreAuthorize("@pms.hasPermission('termproject_bookclass_view')" )
    public R getById(@PathVariable("classid" ) Long classid) {
        return R.ok(bookclassService.getById(classid));
    }

    /**
     * 新增学科类型表
     * @param bookclass 学科类型表
     * @return R
     */
    @Operation(summary = "新增学科类型表" , description = "新增学科类型表" )
    @SysLog("新增学科类型表" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('termproject_bookclass_add')" )
    public R save(@RequestBody BookclassEntity bookclass) {
        return R.ok(bookclassService.save(bookclass));
    }

    /**
     * 修改学科类型表
     * @param bookclass 学科类型表
     * @return R
     */
    @Operation(summary = "修改学科类型表" , description = "修改学科类型表" )
    @SysLog("修改学科类型表" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('termproject_bookclass_edit')" )
    public R updateById(@RequestBody BookclassEntity bookclass) {
        return R.ok(bookclassService.updateById(bookclass));
    }

    /**
     * 通过id删除学科类型表
     * @param ids classid列表
     * @return R
     */
    @Operation(summary = "通过id删除学科类型表" , description = "通过id删除学科类型表" )
    @SysLog("通过id删除学科类型表" )
    @DeleteMapping
//    @PreAuthorize("@pms.hasPermission('termproject_bookclass_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(bookclassService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param bookclass 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
//    @PreAuthorize("@pms.hasPermission('termproject_bookclass_export')" )
    public List<BookclassEntity> export(BookclassEntity bookclass,Long[] ids) {
        return bookclassService.list(Wrappers.lambdaQuery(bookclass).in(ArrayUtil.isNotEmpty(ids), BookclassEntity::getClassid, ids));
    }
}