package com.example.termproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.termproject.entity.ReadertypeEntity;
import com.example.termproject.mapper.ReadertypeMapper;
import com.example.termproject.service.ReadertypeService;
import org.springframework.stereotype.Service;
/**
 * 读者类型
 *
 * @author 张传伟
 * @date 2023-10-17 12:48:15
 */
@Service
public class ReadertypeServiceImpl extends ServiceImpl<ReadertypeMapper, ReadertypeEntity> implements ReadertypeService {
}