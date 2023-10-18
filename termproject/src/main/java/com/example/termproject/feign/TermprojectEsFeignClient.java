package com.example.termproject.feign;

import com.pig4cloud.pig.common.core.util.R;
import com.example.termproject.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@FeignClient(contextId = "termprojectEsFeignClient", value = "termproject-es")
public interface TermprojectEsFeignClient {

    @PostMapping("/book")
    R<BookEntity> save(@RequestBody BookEntity book);

    @PutMapping("/book")
    R<BookEntity> updateById(@RequestBody BookEntity book);

    @DeleteMapping("/book")
    R<List<Long>> removeById(@RequestBody Long[] ids);

}
