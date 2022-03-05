package org.butu.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 话题表
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Getter
@Setter
@TableName("bt_post")
@Builder
@Accessors(chain = true)
@ApiModel(value = "Post对象", description = "话题表")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableField("id")
    private String id;

    @ApiModelProperty("标题")
    @TableId("title")
    private String title;

    @ApiModelProperty("markdown内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("作者ID")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("评论统计")
    @TableField("comments")
    private Integer comments;

    @ApiModelProperty("收藏统计")
    @TableField("collects")
    private Integer collects;

    @ApiModelProperty("浏览统计")
    @TableField("view")
    private Integer view;

    @ApiModelProperty("是否置顶，1-是，0-否")
    @TableField("top")
    private Boolean top;

    @ApiModelProperty("是否加精，1-是，0-否")
    @TableField("essence")
    private Boolean essence;

    @ApiModelProperty("专栏ID")
    @TableField("section_id")
    private Integer sectionId;

    @ApiModelProperty("发布时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField("modify_time")
    private LocalDateTime modifyTime;


}
