package org.butu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 敏感词库
 * </p>
 *
 * @author BUTUbird
 * @since 2022-04-02
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("bt_word")
@ApiModel(value = "Word对象", description = "敏感词库")
public class Word implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("敏感词")
    @TableField("word")
    private String word;


}
