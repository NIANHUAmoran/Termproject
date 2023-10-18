package com.example.termproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.termproject.entity.ReaderEntity;
import com.example.termproject.mapper.ReaderMapper;
import com.example.termproject.service.ReaderService;
import org.springframework.stereotype.Service;
/**
 * 读者表
 *
 * @author 张传伟
 * @date 2023-10-17 11:18:34
 */
@Service
public class ReaderServiceImpl extends ServiceImpl<ReaderMapper, ReaderEntity> implements ReaderService {
}