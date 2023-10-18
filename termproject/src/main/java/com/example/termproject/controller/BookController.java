package com.example.termproject.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.example.termproject.entity.BookEntity;
import com.example.termproject.feign.TermprojectEsFeignClient;
import com.example.termproject.service.BookService;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 图书列表
 *
 * @author 张传伟
 * @date 2023-10-12 23:44:05
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/book" )
@Tag(description = "book" , name = "图书列表" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class BookController {

    private final  BookService bookService;

    @Autowired
    private TermprojectEsFeignClient termprojectEsFeignClient;

    /**
     * 分页查询
     * @param page 分页对象
     * @param book 图书表
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('termproject_book_view')" )
    public R getBookPage(@ParameterObject Page page, @ParameterObject BookEntity book) {
        LambdaQueryWrapper<BookEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StrUtil.isNotBlank(book.getBookname()),BookEntity::getBookname,book.getBookname());
        wrapper.eq(StrUtil.isNotBlank(book.getAuthor1()),BookEntity::getAuthor1,book.getAuthor1());
        wrapper.eq(StrUtil.isNotBlank(book.getAuthor2()),BookEntity::getAuthor2,book.getAuthor2());
        wrapper.eq(StrUtil.isNotBlank(book.getAuthor3()),BookEntity::getAuthor3,book.getAuthor3());
        wrapper.eq(Objects.nonNull(book.getPubdate()),BookEntity::getPubdate,book.getPubdate());
        wrapper.eq(StrUtil.isNotBlank(book.getPublish()),BookEntity::getPublish,book.getPublish());
        wrapper.eq(StrUtil.isNotBlank(book.getPhoto()),BookEntity::getPhoto,book.getPhoto());
        wrapper.eq(StrUtil.isNotBlank(book.getContent()),BookEntity::getContent,book.getContent());
        wrapper.eq(Objects.nonNull(book.getPrice()),BookEntity::getPrice,book.getPrice());
        wrapper.eq(StrUtil.isNotBlank(book.getIsbn()),BookEntity::getIsbn,book.getIsbn());
        wrapper.eq(Objects.nonNull(book.getBookclass()),BookEntity::getBookclass,book.getBookclass());
        wrapper.eq(Objects.nonNull(book.getBooktype()),BookEntity::getBooktype,book.getBooktype());
        return R.ok(bookService.page(page, wrapper));
    }

    /**
     * 通过id查询图书
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    public R<BookEntity> getById(@PathVariable("id" ) String id) {
        BookEntity book = bookService.getById(id);
        return R.ok(book);
    }

    /**
     * 新增图书
     * @param book 图书
     * @return R
     */
    @Operation(summary = "新增图书" , description = "新增图书" )
    @SysLog("新增图书" )
    @PostMapping
    public R<BookEntity> save(@RequestBody BookEntity book) {
//        if (book.getAuthor2() == null) {
//            book.setAuthor2(null);
//        }
//        if (book.getAuthor3() == null) {
//            book.setAuthor3(null);
//        }
        // 需要先插入MySQL，因为要获取主键
        boolean save = bookService.save(book);
        // 再插入ES
        termprojectEsFeignClient.save(book);
        // 返回数据
        return R.ok(book);
    }

    /**
     * 修改图书
     * @param book 图书管理
     * @return R
     */
    @Operation(summary = "修改图书" , description = "修改图书" )
    @SysLog("修改图书" )
    @PutMapping
    public R<BookEntity> updateById(@RequestBody BookEntity book) {
        // 修改MySQL
        boolean b = bookService.updateById(book);
        // 修改ES
        termprojectEsFeignClient.updateById(book);

        // 返回结果
        return R.ok(book);
    }

    /**
     * 通过id删除图书
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除图书" , description = "通过id删除图书" )
    @SysLog("通过id删除图书" )
    @DeleteMapping
    public R<Boolean> removeById(@RequestBody Long[] ids) {
        // 删除MySQL中的数据
        boolean b = bookService.removeBatchByIds(CollUtil.toList(ids));
        // 删除ES中的数据
        termprojectEsFeignClient.removeById(ids);

        // 返回结果
        return R.ok(b);
    }


    /**
     * 导出excel 表格
     * @param book 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    public List<BookEntity> export(BookEntity book,Long[] ids) {
        return bookService.list(Wrappers.lambdaQuery(book).in(ArrayUtil.isNotEmpty(ids), BookEntity::getBookid, ids));
    }
}