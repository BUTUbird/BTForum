package org.butu.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @program: BTForum
 * @description: 用户信息
 * @packagename: org.butu.model.vo
 * @author: BUTUbird
 * @date: 2022-04-16 00:19
 **/
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    @ApiModelProperty("用户ID")

    private String id;

    @ApiModelProperty("用户名")

    private String username;

    @ApiModelProperty("用户昵称")

    private String alias;

    private Integer followers;

    @ApiModelProperty("头像")

    private String avatar;

    @ApiModelProperty("邮箱")

    private String email;

    @ApiModelProperty("手机")

    private String mobile;

    @ApiModelProperty("积分")

    private Integer score;

    @ApiModelProperty("token")

    private String token;

    @ApiModelProperty("个人简介")

    private String bio;

    @ApiModelProperty("是否激活，1：是，0：否")

    private Boolean active;

    @ApiModelProperty("状态，1：使用，0：停用")

    private Boolean status;

    @ApiModelProperty("用户角色")

    private Integer roleId;

    @ApiModelProperty("加入时间")

    private Date createTime;

    @ApiModelProperty("修改时间")

    private LocalDateTime modifyTime;

    @ApiModelProperty("在线状态")

    private Boolean live;
}
