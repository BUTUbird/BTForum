package org.butu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.butu.model.dto.PostDTO;
import org.butu.model.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import org.butu.model.entity.User;
import org.butu.model.vo.PostVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 话题表 服务类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
public interface PostService extends IService<Post> {


    Page<PostVO> getList(Page<PostVO> page, String tab);

    Post create(PostDTO dto, User user);

    Map<String, Object> viewPost(String id);

    List<Post> getRecommend(String id);
}
