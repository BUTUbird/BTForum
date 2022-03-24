package org.butu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.butu.model.vo.p_echartsVO;

import java.util.List;

/**
 * @program: BTForum
 * @description: echarts
 * @packagename: org.butu.mapper
 * @author: BUTUbird
 * @date: 2022-03-24 21:49
 **/
@Mapper
public interface EchartsMapper {

    public List<p_echartsVO> selectPostsByUser();
}
