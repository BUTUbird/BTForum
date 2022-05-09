package org.butu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author BUTUbird
 * @since 2022-04-15
 */
@Getter
@Setter
@TableName("bt_message")
@Builder
@ApiModel(value = "Message对象", description = "")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("接收用户id")
    @TableField("to_user_id")
    private String toUserId;

    @ApiModelProperty("发送用户id")
    @TableField("from_user_id")
    private String fromUserId;

    @ApiModelProperty("发送用户id")
    @TableField("from_user_name")
    private String fromUserName;

    @ApiModelProperty("消息内容")
    @TableField("content")
    private String content;

    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("消息状态 0 未读  1 已读")
    @TableField("read_status")
    private String readStatus;

    @ApiModelProperty("是否删除 0未删除 1删除")
    @TableField("del_flag")
    private Integer delFlag;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;


}
