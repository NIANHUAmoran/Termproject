package com.example.termproject.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 读者类型
 *
 * @author 张传伟
 * @date 2023-10-17 12:48:15
 */
@Data
@TableName("readertype")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "读者类型")
public class ReadertypeEntity extends Model<ReadertypeEntity> {


	/**
	* 类型编号
	*/
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="类型编号")
    private Long typeid;

	/**
	* 类型名称
	*/
    @Schema(description="类型名称")
    private String typename;

	/**
	* 说明
	*/
    @Schema(description="说明")
    private String demo;
}