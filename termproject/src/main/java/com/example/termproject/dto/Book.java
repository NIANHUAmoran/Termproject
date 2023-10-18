package com.example.termproject.dto;

import lombok.*;
/**
 * 图书列表（来自于es项目 termproject_es）
 *
 * @author 张传伟
 * @since 2023-10-12 23:44:05
 */
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Book {

    /**
     * 图书ID
     * MySQL：Long id
     * ES：String id
     */
    private Long bookid;
    private String bookname;
    private String author1;
    private String author2;
    private String author3;
    private LocalDate pubdate;
    private String publish;
    private String photo;
    private String content;
    private BigDecimal price;
    private String isbn;
    private Long bookclass;
    private Long booktype;
}