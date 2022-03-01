package org.butu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 每日赠言
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-01
 */
@Getter
@Setter
@TableName("bt_tip")
@ApiModel(value = "Tip对象", description = "每日赠言")
public class Tip implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("作者")
    @TableField("author")
    private String author;

    @ApiModelProperty("1：使用，0：过期")
    @TableField("type")
    private Integer type;


}
