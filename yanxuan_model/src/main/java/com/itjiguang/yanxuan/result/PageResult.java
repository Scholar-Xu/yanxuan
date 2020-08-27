package com.itjiguang.yanxuan.result;

import java.io.Serializable;
import java.util.List;

/**
 * 用来封装响应的分页结果
 */
public class PageResult<T> implements Serializable {

    // 总记录数
    private Long total;
    // 当前页显示的所有数据
    private List<T> result;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
