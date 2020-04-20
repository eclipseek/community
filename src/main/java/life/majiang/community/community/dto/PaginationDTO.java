package life.majiang.community.community.dto;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: zhangyq<p>
 * Date: 21:29 2020/4/19 <p>
 * Description: <p>
 */

@Data
public class PaginationDTO {

    private List<QuestionDTO> questions;
    // 分页组件上，是否展示上一页按钮（已经是第一页，就不展示）
    private boolean showPrevious;
    // 分页组件上，是否展示第一页按钮（当分页组件上，没有第一页时才展示）
    private boolean showFirstPage;
    // 分页组件上，是否显示下一页按钮（已经是最后一页，就不展示）
    private boolean showNext;
    // 分页组件上，是否展示最后一页按钮（当分页组件上，没有最后一页时才展示）
    private boolean showEndPage;
    // 当前页 号
    private Integer page;
    // 返回给前端要展示的页号
    private List<Integer> pages = new LinkedList<>();
    // 总共有多少页
    private Integer totalPage;

    public void setPagination(int totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;

        // 放入当前页
        pages.add(page);
        // 计算要放入的页号。
        for (int i = 1; i <= 3; i++) {
            // 当前页左边的三页
            if (page -i > 0) {
                pages.add(0, page - i);
            }

            // 当前页右边的三页
            if (page + i <= totalPage){
                pages.add(page + i);
            }
        }

        // 是否展示上一页
        if (page == 1) {
            // 已经是第一页了，就不展示上一页
            showPrevious = false;
        } else {
            // 否则，展示上一页
            showPrevious = true;
        }

        // 是否展示下一页
        if (page == totalPage) {
            // 已经是最后一页了，就不展示下一页
            showNext = false;
        } else {
            // 否则，展示下一页
            showNext = true;
        }

        // 是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        // 是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
