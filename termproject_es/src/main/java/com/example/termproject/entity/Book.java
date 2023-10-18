package com.example.termproject.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.dromara.easyes.annotation.IndexField;
import org.dromara.easyes.annotation.IndexId;
import org.dromara.easyes.annotation.IndexName;
import org.dromara.easyes.annotation.rely.IdType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 图书列表
 *
 * @author 张传伟
 * @date 2023-10-12 23:44:05
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@IndexName(value = "zcwbook")
public class Book {


	private String bookid;

	/**
	* bookID
	 * MySQL：Long id
	 * ES：String id
	*/
	@IndexId(type = IdType.CUSTOMIZE)
    private String id;

	/**
	* 书籍名称
	*/
	@IndexField
	private String bookname;

	/**
	* 书籍一作
	*/
	@IndexField
	private String author1;

	/**
	* 书籍二作
	*/
	@IndexField
    private String author2;

	/**
	* 书籍三作
	*/
	@IndexField
	private String author3;

	/**
	 * 出版日期
	 */
	@IndexField
	private LocalDate pubdate;

	/**
	 * 出版社
	 */
	@IndexField
	private String publish;

	/**
	 * 图片地址
	 */
	@IndexField
	private String photo;

	/**
	 * 内容简介
	 */
	@IndexField
	private String content;

	/**
	 * 价格
	 */
	@IndexField
	private BigDecimal price;

	/**
	 * 书籍ISBN码
	 */
	@IndexField
	private String isbn;

	/**
	 * 学科类型
	 */
	@IndexField
	private Long bookclass;

	/**
	 * 藏书类型
	 */
	@IndexField
	private Long booktype;
}