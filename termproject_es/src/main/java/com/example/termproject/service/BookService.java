package com.example.termproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.termproject.entity.Book;

import java.util.List;

public interface BookService {


    IPage<Book> localPage(Page<Book> page, Book book);

    Book localSave(Book book);

    Book localUpdateById(Book book);

    List<String> localRemoveBatchByIds(String[] ids);

    List<String> localRemoveBatchByIds(List<Book> bookList);

}