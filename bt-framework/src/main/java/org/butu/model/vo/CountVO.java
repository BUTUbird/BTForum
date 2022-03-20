package org.butu.model.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: BTForum
 * @description: 系统统计
 * @packagename: org.butu.model.vo
 * @author: BUTUbird
 * @date: 2022-03-20 15:29
 **/
@Accessors(chain = true)
@Builder
@Data
public class CountVO {
    private Integer users;
    private Integer posts;
    private Integer tags;
    private Integer line;
    private Integer comments;
}
