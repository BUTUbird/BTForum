package org.butu.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("bt_user")
@Accessors(chain = true)
@Builder
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId("id")
    private String id;

    @ApiModelProperty("用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty("用户昵称")
    @TableField("alias")
    private String alias;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("手机")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty("积分")
    @TableField("score")
    private Integer score;

    @ApiModelProperty("token")
    @TableField("token")
    private String token;

    @ApiModelProperty("个人简介")
    @TableField("bio")
    private String bio;

    @ApiModelProperty("是否激活，1：是，0：否")
    @TableField("active")
    private Boolean active;

    @ApiModelProperty("状态，1：使用，0：停用")
    @TableField("status")
    private Boolean status;

    @ApiModelProperty("用户角色")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty("加入时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField("modify_time")
    private LocalDateTime modifyTime;


}
