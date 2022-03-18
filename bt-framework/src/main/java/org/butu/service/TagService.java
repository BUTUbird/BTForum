package org.butu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.butu.model.entity.Post;
import org.butu.model.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
public interface TagService extends IService<Tag> {

    List<Tag> insertTags(List<String> tags);

    Page<Post> selectPostByTagId(Page<Post> objectPage, String id);

    Tag selectTagById(String id);
}
