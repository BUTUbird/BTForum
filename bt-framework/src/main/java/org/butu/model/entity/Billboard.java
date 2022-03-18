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
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 全站公告
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-01
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@TableName("bt_billboard")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Billboard对象", description = "全站公告")
public class Billboard implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("公告")
    @TableField("content")
    private String content;

    @ApiModelProperty("公告时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("1：展示中，0：过期")
    @TableField("shows")
    private boolean shows;


}
