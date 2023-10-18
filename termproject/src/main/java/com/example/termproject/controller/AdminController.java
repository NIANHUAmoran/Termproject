package com.example.termproject.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.example.termproject.entity.AdminEntity;
import com.example.termproject.service.AdminService;
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
 * 图书管理员
 *
 * @author 张传伟
 * @date 2023-10-16 16:01:01
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin" )
@Tag(description = "admin" , name = "图书管理员" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class AdminController {

    private final  AdminService adminService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param admin 图书管理员
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('termproject_admin_view')" )
    public R getAdminPage(@ParameterObject Page page, @ParameterObject AdminEntity admin) {
        LambdaQueryWrapper<AdminEntity> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(StrUtil.isNotBlank(admin.getUsername()),AdminEntity::getUsername,admin.getUsername());
		wrapper.eq(StrUtil.isNotBlank(admin.getPassword()),AdminEntity::getPassword,admin.getPassword());
        return R.ok(adminService.page(page, wrapper));
    }


    /**
     * 通过id查询图书管理员
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('termproject_admin_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(adminService.getById(id));
    }

    /**
     * 新增图书管理员
     * @param admin 图书管理员
     * @return R
     */
    @Operation(summary = "新增图书管理员" , description = "新增图书管理员" )
    @SysLog("新增图书管理员" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('termproject_admin_add')" )
    public R save(@RequestBody AdminEntity admin) {
        return R.ok(adminService.save(admin));
    }

    /**
     * 修改图书管理员
     * @param admin 图书管理员
     * @return R
     */
    @Operation(summary = "修改图书管理员" , description = "修改图书管理员" )
    @SysLog("修改图书管理员" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('termproject_admin_edit')" )
    public R updateById(@RequestBody AdminEntity admin) {
        return R.ok(adminService.updateById(admin));
    }

    /**
     * 通过id删除图书管理员
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除图书管理员" , description = "通过id删除图书管理员" )
    @SysLog("通过id删除图书管理员" )
    @DeleteMapping
//    @PreAuthorize("@pms.hasPermission('termproject_admin_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(adminService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param admin 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
//    @PreAuthorize("@pms.hasPermission('termproject_admin_export')" )
    public List<AdminEntity> export(AdminEntity admin,Long[] ids) {
        return adminService.list(Wrappers.lambdaQuery(admin).in(ArrayUtil.isNotEmpty(ids), AdminEntity::getId, ids));
    }
}