package org.butu.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {

    private String id;

    private String content;

    private String topicId;

    private String userId;

    private String avatar;

    private String username;

    private Date createTime;

}
