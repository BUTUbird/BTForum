package org.butu.service;

import org.butu.model.entity.Word;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 敏感词库 服务类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-04-02
 */
public interface WordService extends IService<Word> {

    void insertTxt(String path);
}
