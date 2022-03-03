package org.butu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.butu.model.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import org.butu.model.vo.PostVO;

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
}
