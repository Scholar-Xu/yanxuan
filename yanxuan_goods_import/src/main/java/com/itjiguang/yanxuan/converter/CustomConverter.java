package com.itjiguang.yanxuan.converter;

import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;

/**
 * 完成数据类型从BigDecimal到Double的转换
 */
public class CustomConverter implements Converter<BigDecimal, String> {
    @Override
    public String convert(BigDecimal source) {
        if(source == null){
            return null;
        }
        return source.toPlainString();
    }
}
