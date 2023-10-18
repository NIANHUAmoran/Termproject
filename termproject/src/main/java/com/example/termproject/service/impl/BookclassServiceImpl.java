package com.example.termproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.termproject.entity.BookclassEntity;
import com.example.termproject.mapper.BookclassMapper;
import com.example.termproject.service.BookclassService;
import org.springframework.stereotype.Service;
/**
 * 学科类型表
 *
 * @author 张传伟
 * @date 2023-10-17 12:40:10
 */
@Service
public class BookclassServiceImpl extends ServiceImpl<BookclassMapper, BookclassEntity> implements BookclassService {
}