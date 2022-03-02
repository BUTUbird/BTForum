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
 * 用户关注
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Getter
@Setter
@TableName("bt_follow")
@ApiModel(value = "Follow对象", description = "用户关注")
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("被关注人ID")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty("关注人ID")
    @TableField("follower_id")
    private String followerId;


}
