package org.butu.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Getter
@Setter
@TableName("bt_comment")
@ApiModel(value = "Comment对象", description = "评论表")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId("id")
    private String id;

    @ApiModelProperty("内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("作者ID")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("topic_id")
    @TableField("topic_id")
    private String topicId;

    @ApiModelProperty("发布时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @TableField("modify_time")
    private LocalDateTime modifyTime;


}
