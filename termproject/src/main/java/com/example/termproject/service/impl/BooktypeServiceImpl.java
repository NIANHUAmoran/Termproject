package com.example.termproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.termproject.entity.BooktypeEntity;
import com.example.termproject.mapper.BooktypeMapper;
import com.example.termproject.service.BooktypeService;
import org.springframework.stereotype.Service;
/**
 * 藏书类型表
 *
 * @author 张传伟
 * @date 2023-10-17 12:47:40
 */
@Service
public class BooktypeServiceImpl extends ServiceImpl<BooktypeMapper, BooktypeEntity> implements BooktypeService {
}