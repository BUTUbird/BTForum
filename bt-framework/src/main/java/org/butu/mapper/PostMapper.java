package org.butu.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.butu.model.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.butu.model.vo.PostVO;

import java.util.List;

/**
 * <p>
 * 话题表 Mapper 接口
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {
    /**
     * 分页查询首页话题列表
     * <p>
     *
     * @param page
     * @param tab
     * @return
     */
    Page<PostVO> selectListAndPage(@Param("page") Page<PostVO> page, @Param("tab") String tab);

    List<Post> selectRecommend(String id);
}
