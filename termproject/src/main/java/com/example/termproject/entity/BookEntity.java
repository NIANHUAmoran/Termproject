package com.example.termproject.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 图书表
 *
 * @author 张传伟
 * @date 2023-10-17 19:23:15
 */
@Data
@TableName("book")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "图书表")
public class BookEntity extends Model<BookEntity> {


	/**
	* 书籍编号
	*/
    @TableId(type = IdType.AUTO)
    @Schema(description="书籍编号")
    private Long bookid;

	/**
	* 书籍名称
	*/
    @Schema(description="书籍名称")
    private String bookname;

	/**
	* 书籍一作
	*/
    @Schema(description="书籍一作")
    private String author1;

	/**
	* 书籍二作
	*/
    @Schema(description="书籍二作")
    private String author2;

	/**
	* 书籍三作
	*/
    @Schema(description="书籍三作")
    private String author3;

	/**
	* 出版日期
	*/
    @Schema(description="出版日期")
    private LocalDate pubdate;

	/**
	* 出版社
	*/
    @Schema(description="出版社")
    private String publish;

	/**
	* 图片地址
	*/
    @Schema(description="图片地址")
    private String photo;

	/**
	* 内容简介
	*/
    @Schema(description="内容简介")
    private String content;

	/**
	* 价格
	*/
    @Schema(description="价格")
    private BigDecimal price;

	/**
	* 书籍ISBN码
	*/
    @Schema(description="书籍ISBN码")
    private String isbn;

	/**
	* 学科类型
	*/
    @Schema(description="学科类型")
    private Long bookclass;

	/**
	* 藏书类型
	*/
    @Schema(description="藏书类型")
    private Long booktype;
}