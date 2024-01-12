package com.example.fdppoc.configuration;

import com.example.fdppoc.code.BaseRange;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToBaseRangeConverter());
    }
    @Slf4j
    static class StringToBaseRangeConverter implements Converter<String, BaseRange> {
        @Override
        public BaseRange convert(String s) {
            try {
                return BaseRange.valueOf(s.toUpperCase());
            }catch(IllegalArgumentException e){
                log.error("BaseRange 변환 오류 발생 : {}", s);
                throw new IllegalArgumentException(e);
            }
        }

    }
}
