package com.marcus.springcloud.MovieApplication.util;

import io.micrometer.core.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class UrlUtil {

    private static String getQueryStringByMap(Map<String,Object> map){
        if(MapUtils.isEmpty(map)){
            return "";
        }
        List<String> paramPairs = map.keySet()
                .stream()
                .map(key->String.format("%s=%s",key,map.get(key)))
                .collect(Collectors.toList());
        return String.join("&",paramPairs);
    }

    public static String getTargetUrl(String baseUrl, @Nullable Object params){
        if(params==null){
            return baseUrl;
        }

        if(!(params instanceof Map)){
            throw new IllegalArgumentException("参数非法，参数应该是个json");
        }
        Map map = (Map) params;
        String queryString =  getQueryStringByMap(map);
        String targetUrl = baseUrl+"?"+queryString;
        log.info("targetUrl={},queryString={}",targetUrl,queryString);
        return targetUrl;
    }
}
