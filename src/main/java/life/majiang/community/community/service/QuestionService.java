package life.majiang.community.community.service;

import life.majiang.community.community.dto.PaginationDTO;
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

    /**
     * @param page      前端传入的 页号
     * @param size      一页要显示的 数量
     * @return life.majiang.community.community.dto.PaginationDTO 封装好的分页信息
     */
    public PaginationDTO list(Integer page, Integer size) {
        int totalCount = questionMapper.count();

        // 计算下，一共有几页
        int totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = (totalCount / size) + 1;
        }

        // 如果传过来的 page，超过了范围，就设置成最大或最小的
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        // ---------------> 重点 <--------------
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        // 查询问题
        List<Question> quesions = questionMapper.list(offset, size);

        for (Question question: quesions) {
            // 查询问题发起人
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            // 快速拷贝属性值到 questionDTO（使用反射）
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);

        return paginationDTO;
    }
}
