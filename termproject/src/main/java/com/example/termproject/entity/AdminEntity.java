package com.example.termproject.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图书管理员
 *
 * @author 张传伟
 * @date 2023-10-16 16:01:01
 */
@Data
@TableName("admin")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "图书管理员")
public class AdminEntity extends Model<AdminEntity> {


	/**
	* 管理员编号
	*/
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="管理员编号")
    private Long id;

	/**
	* 管理员帐号
	*/
    @Schema(description="管理员帐号")
    private String username;

	/**
	* 帐号密码
	*/
    @Schema(description="帐号密码")
    private String password;
}