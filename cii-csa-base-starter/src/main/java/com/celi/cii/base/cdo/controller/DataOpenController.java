package com.celi.cii.base.cdo.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.celi.cii.common.entity.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Map;

@Slf4j
public class DataOpenController {

    @Value("${cii.open-api.user}")
    private String defaultUserId;

    /**
     * 数据开放服务器 ip，如 http://192.168.1.205:9010
     */
    @Value("${cii.open-api.server}")
    private String dateOpenApiServer;

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    @RequestMapping("${cii.open-api.prefix}/**")
    public Object handle(HttpServletRequest request,
                         @RequestParam(required = false) Map<String, String> params,
                         @RequestBody(required = false) Map<String, Object> body) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();

             //生成 uri
            URIBuilder builder = new URIBuilder(dateOpenApiServer + request.getRequestURI());
            if (params != null) {
                params.forEach(builder::addParameter);
            }
            // 加入用户、租户信息
            if (!StpUtil.isLogin()) {
                builder.addParameter("userId", defaultUserId);
            } else {
                builder.addParameter("userId", StpUtil.getLoginIdAsString());
            }
            URI uri = builder.build();

            // 建造者模式生成 request
            Request.Builder requestBuilder = new Request.Builder()
                    .url(uri.toString());

            // 增加 version 以指向指定微服务
            requestBuilder.addHeader("version", "celi");

            // 增加 masterKey、appKey
            copyRequestHeader(request, requestBuilder, "masterKey");
            copyRequestHeader(request, requestBuilder, "appKey");

            // 处理 post 请求
            if (RequestMethod.POST.name().equals(request.getMethod())) {
                okhttp3.RequestBody requestBody;
                if (MapUtil.isNotEmpty(body)) {
                    requestBody = okhttp3.RequestBody.create(MEDIA_TYPE_JSON, JSON.toJSONString(body));
                } else {
                    requestBody = okhttp3.RequestBody.create(MEDIA_TYPE_JSON, "");
                }
                requestBuilder.post(requestBody);
            }

            Response response = okHttpClient.newCall(requestBuilder.build()).execute();

            // string 方法会触发关闭响应体，所以不需要在 finally 中进行资源回收
            return response.body().string();
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseDTO.errorMessage(e.getMessage());
        }
    }

    /**
     * 复制请求头中属性
     *
     * @param request        请求
     * @param requestBuilder 请求 Builder
     * @param headerName     请求头参数名
     */
    private void copyRequestHeader(HttpServletRequest request, Request.Builder requestBuilder, String headerName) {
        String header = request.getHeader(headerName);
        if (StrUtil.isNotEmpty(header)) {
            requestBuilder.addHeader(headerName, header);
        }
    }
}
