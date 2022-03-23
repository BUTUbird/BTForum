package org.butu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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

/**
 * <p>
 * 轮播图
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-23
 */
@Builder
@Getter
@Setter
@TableName("bt_carousel")
@ApiModel(value = "Carousel对象", description = "轮播图")
public class Carousel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("图片地址")
    @TableField("url")
    private String url;

    @ApiModelProperty("信息")
    @TableField("name")
    private String name;

    @ApiModelProperty("状态，1：显示 2隐藏")
    @TableField("shows")
    private boolean shows;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;


}
