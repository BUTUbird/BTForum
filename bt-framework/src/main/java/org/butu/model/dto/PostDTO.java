package org.butu.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author BUTU
 */
@Data
public class PostDTO implements Serializable {
    private static final long serialVersionUID = -5957433707110390852L;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签
     */
    private List<String> tags;

}
