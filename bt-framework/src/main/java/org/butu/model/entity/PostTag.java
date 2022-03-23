package org.butu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: BTForum
 * @description: 文章标签
 * @packagename: org.butu.model.entity
 * @author: BUTUbird
 * @date: 2022-03-23 16:04
 **/
@Data
@TableName("bt_post_tag")
@Accessors(chain = true)
public class PostTag implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("tag_id")
    private String tagId;

    @TableField("topic_id")
    private String topicId;
}
