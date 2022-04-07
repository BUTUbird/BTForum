package org.butu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: BTForum
 * @description: 子评论
 * @packagename: org.butu.model.dto
 * @author: BUTUbird
 * @date: 2022-04-07 13:15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildCommentDTO {
    private String content_parent_id;

    private String child_comment_content;
}
