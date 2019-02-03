package com.marcus.springcloud.MovieApplication.util;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.file.WatchEvent;

public class WebClientUtil {
    private static WebClient buildWebClient(){
        return WebClient.create();
    }

    public static <T> Mono<T> get(String baseUrl,Object params,Class<T> returnType){
        return WebClientUtil.buildWebClient()
                .get()
                .uri(UrlUtil.getTargetUrl(baseUrl,params))
                .retrieve()
                .bodyToMono(returnType);
    }

    public static <T> Mono<T> get(String targetUrl,Object[] urlVariables,Class<T> returnType){
        return WebClientUtil.buildWebClient()
                .get()
                .uri(targetUrl,urlVariables)
                .retrieve()
                .bodyToMono(returnType);
    }

    public static <T> Mono<T> postJSON(String baseUrl,Object request,Class<T> returnType){
        Mono<Object> mono = Mono.just(request);
        return WebClientUtil.buildWebClient()
                .post()
                .uri(baseUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(mono,Object.class)
                .retrieve()
                .bodyToMono(returnType);
    }

    public static <T> Mono<T> postForm(String baseUrl,MultiValueMap<String,String> formData,Class<T> returnType){
        return WebClientUtil.buildWebClient()
                .post()
                .uri(baseUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(returnType);

    }

}
