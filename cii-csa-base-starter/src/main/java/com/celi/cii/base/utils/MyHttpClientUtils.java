package com.celi.cii.base.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.celi.cii.base.enums.RequestMethodEnum;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MyHttpClientUtils {

    /**
     * 统一调用方法
     * @param uri
     * @param method
     * @param paramsMap
     * @param bodyMap
     * @param headerMap
     * @return
     */
    public static String executeMethod(String uri, String method, Map<String, Object> paramsMap,
                                       Map<String, Object> bodyMap, Map<String,Object> headerMap,
                                       int timeout) {
        String responseStr = "";
        // 获得Http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //设置参数params
        String params = getParams(paramsMap);

        // 获取路径
        String fullUrl = getFullUrl(uri, params);

        //设置body参数
        StringEntity entity = getStringEntity(bodyMap);

        HttpRequestBase httpRequestBase = null;
        if(RequestMethodEnum.METHOD_GET.getCode().equalsIgnoreCase(method)) {
            //get请求
            HttpGet httpGet = new HttpGet(fullUrl);
            httpRequestBase = httpGet;
        } else if(RequestMethodEnum.METHOD_POST.getCode().equalsIgnoreCase(method)) {
            //post请求
            HttpPost httpPost = new HttpPost(fullUrl);
            //设置请求体
            httpPost.setEntity(entity);
            httpRequestBase = httpPost;
        } else if(RequestMethodEnum.METHOD_PUT.getCode().equalsIgnoreCase(method)) {
            //put请求
            HttpPut httpPut = new HttpPut(fullUrl);
            //设置请求体
            httpPut.setEntity(entity);
            httpRequestBase = httpPut;
        } else if(RequestMethodEnum.METHOD_DELETE.getCode().equalsIgnoreCase(method)) {
            //delete请求
            HttpDelete httpDelete = new HttpDelete(fullUrl);
            httpRequestBase = httpDelete;
        }
        if(httpRequestBase != null) {
            //设置请求头
            setHeaders(httpRequestBase, headerMap);

            // 配置信息
            RequestConfig requestConfig = getRequestConfig(timeout);
            httpRequestBase.setConfig(requestConfig);
        }

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)请求
            response = httpClient.execute(httpRequestBase);
            // 从响应模型中获取响应实体
            responseStr = getResponseContent(response);
        } catch (Exception e) {
        } finally {
            closeHttpClientConn(httpClient, response);
        }
        return responseStr;
    }

    /**
     * 拼接全路径
     * @param uri
     * @param params
     * @return
     */
    private static String getFullUrl(String uri, String params) {
        String fullUrl = uri;
        if(StrUtil.isNotBlank(params)) {
            fullUrl = uri + "?" + params;
        }
        return fullUrl;
    }

    /**
     * 设置body参数
     * @param bodyMap
     * @return
     */
    private static StringEntity getStringEntity(Map<String, Object> bodyMap) {
        JSONObject jsonObject = getJSONObject(bodyMap);
        String jsonString = jsonObject.toJSONString();
        // 参数
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        entity.setContentType("application/json");
        return entity;
    }

    /**
     * 拼接参数
     * @param paramsMap
     * @return
     */
    private static String getParams(Map<String, Object> paramsMap) {
        StringBuffer params = new StringBuffer();
        if (!paramsMap.isEmpty()) {
            Set<String> paramsSet = paramsMap.keySet();
            if (CollectionUtil.isNotEmpty(paramsSet)) {
                Iterator<String> iterator = paramsSet.iterator();
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    Object value = paramsMap.get(next);
                    try {
                        // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
                        params.append(next);
                        params.append("=");
                        params.append(URLEncoder.encode(String.valueOf(value), "UTF-8"));
                        params.append("&");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return params.toString();
    }

    /**
     * 转成jsonObject对象
     * @param bodyMap
     * @return
     */
    private static JSONObject getJSONObject(Map<String, Object> bodyMap) {
        JSONObject jsonObject = new JSONObject();
        if(!bodyMap.isEmpty()) {
            Set<String> keys = bodyMap.keySet();
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                Object value = bodyMap.get(next);
                jsonObject.put(next, value);
            }
        }
        return jsonObject;
    }

    private static RequestConfig getRequestConfig(int timeout) {
        RequestConfig requestConfig = RequestConfig.custom()
                // 设置连接超时时间(单位毫秒)
                .setConnectTimeout(timeout)
                // 设置请求超时时间(单位毫秒)
                .setConnectionRequestTimeout(timeout)
                // socket读写超时时间(单位毫秒)
                .setSocketTimeout(timeout)
                // 设置是否允许重定向(默认为true)
                .setRedirectsEnabled(true).build();
        return requestConfig;
    }

    private static void closeHttpClientConn(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        try {
            // 释放资源
            if (httpClient != null) {
                httpClient.close();
            }
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取响应内容
     * @param response
     * @return
     */
    private static String getResponseContent(CloseableHttpResponse response) {
        String responseStr = null;
        try {
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
//            byte[] bytes = EntityUtils.toByteArray(responseEntity);
//            responseStr = new String(bytes, "UTF-8");
            if (responseEntity != null) {
                responseStr = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
                System.out.println("响应内容为:" + responseStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseStr;
    }

    public static void setHeaders(HttpRequestBase http, Map<String, Object> headerMap) {
        // 设置ContentType
        http.setHeader("Content-Type", "application/json;charset=utf8");
        if (headerMap != null) {
            Set<String> headerSet = headerMap.keySet();
            if (CollectionUtil.isNotEmpty(headerSet)) {
                Iterator<String> iterator = headerSet.iterator();
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    Object value = headerMap.get(next);
                    http.setHeader(next, String.valueOf(value));
                }
            }
        }
    }
}
