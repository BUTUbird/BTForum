package org.butu.model.vo;

import lombok.Data;

/**
 * @program: BTForum
 * @description: 浏览量
 * @packagename: org.butu.model.vo
 * @author: BUTUbird
 * @date: 2022-03-28 22:24
 **/
@Data
public class ViewVO {
    //p.id,u.username,p.title,p.view as 'views'
    private String id;
    private String username;
    private String title;
    private Integer views;
}
