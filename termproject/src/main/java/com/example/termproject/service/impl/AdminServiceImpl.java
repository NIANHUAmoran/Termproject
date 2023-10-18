package com.example.termproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.termproject.entity.AdminEntity;
import com.example.termproject.mapper.AdminMapper;
import com.example.termproject.service.AdminService;
import org.springframework.stereotype.Service;
/**
 * 图书管理员
 *
 * @author 张传伟
 * @date 2023-10-16 16:01:01
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> implements AdminService {
}