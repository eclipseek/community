package life.majiang.community.community.service;

import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhangyq<p>
 * Date: 23:29 2020/4/18 <p>
 * Description: <p>
 */

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        // 查询所有问题
        List<Question> quesions = questionMapper.list();
        for (Question question: quesions) {
            // 查询问题发起人
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            // 快速拷贝属性值到 questionDTO（使用反射）
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        return questionDTOS;
    }
}
