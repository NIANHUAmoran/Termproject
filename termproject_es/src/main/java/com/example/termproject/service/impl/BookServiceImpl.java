package com.example.termproject.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.termproject.entity.Book;
import com.example.termproject.mapper.BookMapper;
import com.example.termproject.service.BookService;
import org.dromara.easyes.core.biz.EsPageInfo;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 图书列表
 *
 * @author 张传伟
 * @date 2023-10-12 23:44:05
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public IPage<Book> localPage(Page<Book> page, Book book) {
        LambdaEsQueryWrapper<Book> wrapper = new LambdaEsQueryWrapper<>();
        // 构造条件
        wrapper.eq(Book::getBookname, book.getBookname());
        wrapper.eq(Book::getAuthor1, book.getAuthor1());
        wrapper.eq(Book::getAuthor2, book.getAuthor2());
        wrapper.eq(Book::getAuthor3, book.getAuthor3());
        wrapper.eq(Book::getPubdate, book.getPubdate());
        wrapper.eq(Book::getPublish, book.getPublish());
        wrapper.eq(Book::getPhoto, book.getPhoto());
        wrapper.eq(Book::getPrice, book.getPrice());
        wrapper.eq(Book::getIsbn, book.getIsbn());
        wrapper.eq(Book::getBookclass, book.getBookclass());
        wrapper.eq(Book::getBooktype, book.getBooktype());


        // 当前页
        int current = (int) page.getCurrent();
        // 每页大小
        int size = (int) page.getSize();

        // 查询
        EsPageInfo<Book> documentEsPageInfo = bookMapper.pageQuery(wrapper, current, size);

        // 转化：将ES的分页转化为Pig的分页
        Page<Book> result = new Page<>(documentEsPageInfo.getPageNum(), documentEsPageInfo.getPageSize());

        // 设置查询数据
        result.setRecords(documentEsPageInfo.getList());

        // 返回结果
        return result;
    }

    @Override
    public Book localSave(Book book) {
        book.setId(book.getBookid());
        bookMapper.insert(book);
        return book;
    }

    @Override
    public Book localUpdateById(Book book) {
        book.setId(book.getBookid());
        bookMapper.updateById(book);
        return book;
    }

    @Override
    public List<String> localRemoveBatchByIds(String[] ids) {
        ArrayList<String> idList = CollUtil.toList(ids);
        bookMapper.deleteBatchIds(idList);
        return idList;
    }

    @Override
    public List<String> localRemoveBatchByIds(List<Book> bookList) {
        List<String> idList = bookList.stream()
                .peek(item -> item.setId(item.getBookid()))
                .map(Book::getId)
                .collect(Collectors.toList());
        bookMapper.deleteBatchIds(idList);
        return idList;
    }


}