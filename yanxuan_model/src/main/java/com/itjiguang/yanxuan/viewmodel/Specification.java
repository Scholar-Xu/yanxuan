package com.itjiguang.yanxuan.viewmodel;

import com.itjiguang.yanxuan.model.GoodsSpec;
import com.itjiguang.yanxuan.model.GoodsSpecOption;

import java.util.List;

/**
 * 用来封装规格的信息
 */
public class Specification extends GoodsSpec {

    private List<GoodsSpecOption> optionList ;

    public List<GoodsSpecOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<GoodsSpecOption> optionList) {
        this.optionList = optionList;
    }
}
