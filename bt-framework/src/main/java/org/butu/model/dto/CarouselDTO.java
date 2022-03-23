package org.butu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: BTForum
 * @description: 轮播图前端实体类
 * @packagename: org.butu.model.dto
 * @author: BUTUbird
 * @date: 2022-03-23 16:31
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarouselDTO {
    private String name;
    private String url;
}
