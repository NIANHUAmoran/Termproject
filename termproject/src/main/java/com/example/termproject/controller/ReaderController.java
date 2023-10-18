package com.example.termproject.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.example.termproject.entity.ReaderEntity;
import com.example.termproject.service.ReaderService;
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
 * 读者表
 *
 * @author 张传伟
 * @date 2023-10-17 11:18:34
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/reader" )
@Tag(description = "reader" , name = "读者表管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class ReaderController {

    private final  ReaderService readerService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param reader 读者表
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('termproject_reader_view')" )
    public R getReaderPage(@ParameterObject Page page, @ParameterObject ReaderEntity reader) {
        LambdaQueryWrapper<ReaderEntity> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(StrUtil.isNotBlank(reader.getName()),ReaderEntity::getName,reader.getName());
		wrapper.eq(StrUtil.isNotBlank(reader.getTelephone()),ReaderEntity::getTelephone,reader.getTelephone());
		wrapper.eq(StrUtil.isNotBlank(reader.getEmail()),ReaderEntity::getEmail,reader.getEmail());
		wrapper.eq(StrUtil.isNotBlank(reader.getDept()),ReaderEntity::getDept,reader.getDept());
		wrapper.eq(Objects.nonNull(reader.getEntitlement()),ReaderEntity::getEntitlement,reader.getEntitlement());
		wrapper.eq(Objects.nonNull(reader.getReadertype()),ReaderEntity::getReadertype,reader.getReadertype());
		wrapper.eq(StrUtil.isNotBlank(reader.getDemo()),ReaderEntity::getDemo,reader.getDemo());
        return R.ok(readerService.page(page, wrapper));
    }


    /**
     * 通过id查询读者表
     * @param readerid id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{readerid}" )
//    @PreAuthorize("@pms.hasPermission('termproject_reader_view')" )
    public R getById(@PathVariable("readerid" ) Long readerid) {
        return R.ok(readerService.getById(readerid));
    }

    /**
     * 新增读者表
     * @param reader 读者表
     * @return R
     */
    @Operation(summary = "新增读者表" , description = "新增读者表" )
    @SysLog("新增读者表" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('termproject_reader_add')" )
    public R save(@RequestBody ReaderEntity reader) {
        return R.ok(readerService.save(reader));
    }

    /**
     * 修改读者表
     * @param reader 读者表
     * @return R
     */
    @Operation(summary = "修改读者表" , description = "修改读者表" )
    @SysLog("修改读者表" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('termproject_reader_edit')" )
    public R updateById(@RequestBody ReaderEntity reader) {
        return R.ok(readerService.updateById(reader));
    }

    /**
     * 通过id删除读者表
     * @param ids readerid列表
     * @return R
     */
    @Operation(summary = "通过id删除读者表" , description = "通过id删除读者表" )
    @SysLog("通过id删除读者表" )
    @DeleteMapping
//    @PreAuthorize("@pms.hasPermission('termproject_reader_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(readerService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param reader 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
//    @PreAuthorize("@pms.hasPermission('termproject_reader_export')" )
    public List<ReaderEntity> export(ReaderEntity reader,Long[] ids) {
        return readerService.list(Wrappers.lambdaQuery(reader).in(ArrayUtil.isNotEmpty(ids), ReaderEntity::getReaderid, ids));
    }
}