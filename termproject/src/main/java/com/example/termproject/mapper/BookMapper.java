package com.example.termproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.termproject.entity.BookEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<BookEntity> {


}