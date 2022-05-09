package org.butu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.butu.model.entity.Word;
import org.butu.mapper.WordMapper;
import org.butu.service.WordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.butu.utils.WordFilter.WordFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 敏感词库 服务实现类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-04-02
 */
@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements WordService {
    @Autowired
    private WordMapper wordMapper;
    @Autowired
    private WordFilter wordFilter;
    @Override
    public void insertTxt(String path) {
        List<String> list = wordFilter.readWordsFile(path);
        for (String s : list) {
            Word word = Word.builder()
                    .word(s)
                    .build();
            LambdaQueryWrapper<Word> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Word::getWord, s);
            Word word1 = wordMapper.selectOne(queryWrapper);
            if (word1 == null) {
                wordMapper.insert(word);
            }else {
                System.out.println(word.getWord() + "已存在");
            }
        }
    }
}
