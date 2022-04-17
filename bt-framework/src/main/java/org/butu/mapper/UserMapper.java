package org.butu.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.butu.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.butu.model.vo.ProfileVO;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    Page<User> searchkey(@Param("page") Page<User> page, @Param("key") String key);

    List<User> getFans(@Param("id") String id);
}
