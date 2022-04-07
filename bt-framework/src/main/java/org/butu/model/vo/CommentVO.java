package org.butu.model.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentVO {

    private String id;

    private String content;

    private String topicId;

    private String userId;

    private String avatar;

    private Integer roleId;

    private String username;

    private Date createTime;

    private String parentId;

    private String parentUserName;

    private List<CommentVO> children;
}

