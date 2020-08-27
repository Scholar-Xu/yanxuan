package com.itjiguang.yanxuan.search.api;

import java.util.Map;

public interface ISearchService {
    /**
     * 根据条件查询solr服务器中的信息
     * @param queryParams
     * @return
     */
    Map<String,Object> query(Map queryParams);
}
