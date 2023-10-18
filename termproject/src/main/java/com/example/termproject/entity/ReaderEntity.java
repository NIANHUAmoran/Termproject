package com.example.termproject.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 读者表
 *
 * @author 张传伟
 * @date 2023-10-17 11:18:34
 */
@Data
@TableName("reader")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "读者表")
public class ReaderEntity extends Model<ReaderEntity> {


	/**
	* 读者编号
	*/
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="读者编号")
    private Long readerid;

	/**
	* 读者姓名
	*/
    @Schema(description="读者姓名")
    private String name;

	/**
	* 联系电话
	*/
    @Schema(description="联系电话")
    private String telephone;

	/**
	* 邮箱地址
	*/
    @Schema(description="邮箱地址")
    private String email;

	/**
	* 所在院系
	*/
    @Schema(description="所在院系")
    private String dept;

	/**
	* 借阅权限
	*/
    @Schema(description="借阅权限")
    private Integer entitlement;

	/**
	* 读者类型
	*/
    @Schema(description="读者类型")
    private Long readertype;

	/**
	* 说明
	*/
    @Schema(description="说明")
    private String demo;
}