package org.butu.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.butu.mapper.TagMapper;
import org.butu.model.entity.Post;
import org.butu.mapper.PostMapper;
import org.butu.model.entity.PostTag;
import org.butu.model.entity.Tag;
import org.butu.model.vo.PostVO;
import org.butu.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.butu.service.PostTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 话题表 服务实现类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Autowired
    private PostTagService postTagService;
    @Autowired
    private TagMapper tagMapper;
    @Override
    public Page<PostVO> getList(Page<PostVO> page, String tab) {
        // 查询话题
        Page<PostVO> iPage = this.baseMapper.selectListAndPage(page, tab);
        // 查询话题的标签
        setTopicTags(iPage);
        return iPage;
    }
    private void setTopicTags(Page<PostVO> iPage) {
        iPage.getRecords().forEach(postTag -> {
            List<PostTag> postTags = postTagService.selectByTopicId(postTag.getId());
            if (!postTags.isEmpty()) {
                List<String> tagIds = postTags.stream().map(PostTag::getTagId).collect(Collectors.toList());
                List<Tag> tags = tagMapper.selectBatchIds(tagIds);
                postTag.setTags(tags);
            }
        });
    }
}
