package com.example.termproject.mapper;

import com.example.termproject.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.easyes.core.core.BaseEsMapper;

@Mapper
public interface BookMapper extends BaseEsMapper<Book> {


}