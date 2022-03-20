package org.butu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.butu.mapper.*;
import org.butu.model.entity.*;
import org.butu.model.vo.CountVO;
import org.butu.service.BillboardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

/**
 * <p>
 * 全站公告 服务实现类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-01
 */
@Service
public class BillboardServiceImpl extends ServiceImpl<BillboardMapper, Billboard> implements BillboardService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public void insertOne(String content) {
        Billboard billboard = Billboard.builder().content(content).createTime(new Date()).shows(true).build();
        this.baseMapper.insert(billboard);
    }

    @Override
    public CountVO statistics() {
        return CountVO.builder()
                //文章总数
                .posts(postMapper.selectCount(new LambdaQueryWrapper<Post>().ne(Post::getId, 0)).intValue())
                //用户人数
                .users(userMapper.selectCount(new LambdaQueryWrapper<User>().ne(User::getId, 0)).intValue())
                //用户在线人数
                .line(userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getLive, 1)).intValue())
                //标签总数
                .tags(tagMapper.selectCount(new LambdaQueryWrapper<Tag>().ne(Tag::getId, 0)).intValue())
                //用户在线人数
                .comments(commentMapper.selectCount(new LambdaQueryWrapper<Comment>().ne(Comment::getId, 0)).intValue())
                .build();


    }
}
