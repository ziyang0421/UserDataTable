package project.domain;

import java.util.List;

/**
 * Created by ZIYANG on 2020/4/1.
 * 页面的特有属性类，完成分页操作
 */
public class PageBean<User> {
    private int totalCount;     // 总记录数
    private int totalPage;  // 总页数
    private int currentPage;    // 当前页数
    private int rows;   // 每页有的记录数
    private List<User> list;    // 当前页面的用户信息集合

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                ", list=" + list +
                '}';
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
