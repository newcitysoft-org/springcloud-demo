package com.renren.jinkong.kylin.weixinnewsservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.renren.jinkong.kylin.weixinnewsservice.api.AccessTokenApi;
import com.renren.jinkong.kylin.weixinnewsservice.api.TemplateApi;
import com.renren.jinkong.kylin.weixinnewsservice.entity.ResultMsg;
import com.renren.jinkong.kylin.weixinnewsservice.entity.TemplateData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/7/2 13:00
 */
@RestController
@RequestMapping("/news")
@Component
public class NewsController {
    @Value("${appId}")
    private String appId;

    @Value("${appSecret}")
    private String appSecret;

    @PostMapping(value = "send")
    public ResultMsg<String> send(@RequestParam(value = "appId", defaultValue = "") String appId,
                                  @RequestParam(value = "appSecret", defaultValue = "") String appSecret,
                                  @RequestParam(value = "tempId") String tempId,
                                  @RequestParam(value = "openId") String openId,
                                  @RequestParam(value = "tempBody") String tempBody,
                                  @RequestParam(value = "url") String url) {

        if(StringUtils.isBlank(appId)) {
            appId = this.appId;
        }

        if(StringUtils.isBlank(appSecret)) {
            appSecret = this.appSecret;
        }

        ResultMsg<String> resultMsg = new ResultMsg<>();

        try {
            resultMsg = AccessTokenApi.getToken(appId, appSecret);
            if(resultMsg.getStatus() != ResultMsg.ResultType.FAILURE.getValue()) {
                String token = resultMsg.getData();

                TemplateData data = new TemplateData();

                data.setTouser(openId);
                data.setTemplate_id(tempId);
                data.setData(JSONObject.parseObject(tempBody));
                data.setUrl(url);

                resultMsg = TemplateApi.send(token, data);
            }
        } catch (IOException e) {
            e.printStackTrace();
            resultMsg.setStatus(ResultMsg.ResultType.FAILURE.getValue());
            resultMsg.setMsg(e.getMessage());
            resultMsg.setData(null);
        }

        return resultMsg;
    }
}
