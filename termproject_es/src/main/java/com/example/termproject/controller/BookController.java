package com.example.termproject.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.termproject.entity.Book;
import com.example.termproject.service.BookService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *图书借阅系统
 *
 * @author 张传伟
 * @since 2023-10-16 23:44:05
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
@Tag(description = "book", name = "图书列表")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class BookController {

    private final BookService bookService;

    /**
     * 分页查询
     *
     * @param page    分页对象
     * @param book 图书列表
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    public R<IPage<Book>> getCompanyPage(@ParameterObject Page<Book> page, @ParameterObject Book book) {
        return R.ok(bookService.localPage(page, book));
    }

    /**
     * 图书列表新增
     *
     * @param book 图书列表
     * @return R
     */
    @Operation(summary = "图书列表新增", description = "图书列表新增")
    @SysLog("图书列表新增")
    @PostMapping
    public R<Book> save(@RequestBody Book book) {
        return R.ok(bookService.localSave(book));
    }

    /**
     * 图书列表修改
     *
     * @param book 图书列表
     * @return R
     */
    @Operation(summary = "图书列表修改", description = "图书列表修改")
    @SysLog("图书列表修改")
    @PutMapping
    public R<Book> updateById(@RequestBody Book book) {
        return R.ok(bookService.localUpdateById(book));
    }

    /**
     * 通过id删除图书
     *
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除图书", description = "通过id删除图书")
    @SysLog("通过id删除图书")
    @DeleteMapping
    public R<List<String>> removeById(@RequestBody String[] ids) {
        return R.ok(bookService.localRemoveBatchByIds(ids));
    }
}