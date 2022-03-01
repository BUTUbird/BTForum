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
 * 广告推广表
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Getter
@Setter
@TableName("bt_promotion")
@ApiModel(value = "Promotion对象", description = "广告推广表")
public class Promotion implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("广告标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("广告链接")
    @TableField("link")
    private String link;

    @ApiModelProperty("说明")
    @TableField("description")
    private String description;


}
