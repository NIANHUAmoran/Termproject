package com.example.termproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.termproject.entity.BookEntity;
import com.example.termproject.mapper.BookMapper;
import com.example.termproject.service.BookService;
import org.springframework.stereotype.Service;
/**
 * 图书表
 *
 * @author 张传伟
 * @date 2023-10-17 19:23:15
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, BookEntity> implements BookService {
}