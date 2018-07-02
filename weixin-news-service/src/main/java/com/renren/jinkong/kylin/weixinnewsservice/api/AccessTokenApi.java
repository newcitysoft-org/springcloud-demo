package com.renren.jinkong.kylin.weixinnewsservice.api;

import com.alibaba.fastjson.JSONObject;
import com.renren.jinkong.kylin.weixinnewsservice.entity.ResultMsg;
import com.renren.jinkong.kylin.weixinnewsservice.util.HttpUtil;

import java.io.IOException;

/**
 * 获取access_token
 * 接口文档地址：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183
 *
 * @author lixin.tian@renren-inc.com
 * @date 2018/7/2 10:32
 */
public class AccessTokenApi {
    private static final String URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    public static ResultMsg<String> getToken(String appId, String secret) throws IOException {
        ResultMsg<String> resultMsg = new ResultMsg<>();
        String url = String.format(URL, appId, secret);

        String result = HttpUtil.doGet(url);
        JSONObject jsonObject = JSONObject.parseObject(result);

        Integer errcode = jsonObject.getInteger("errcode");
        if(errcode != null) {
            resultMsg.setStatus(ResultMsg.ResultType.FAILURE.getValue());
            resultMsg.setMsg(jsonObject.getString("errmsg"));
        } else {
            resultMsg.setStatus(ResultMsg.ResultType.SUCCESS.getValue());
            resultMsg.setData(jsonObject.getString("access_token"));
            resultMsg.setMsg("获取成功");
        }

        return resultMsg;
    }

    public static void main(String[] args) {
        try {
            ResultMsg<String> resultMsg = getToken("wx6080c31355b4073e", "5224a224c6912d26c0b6ade3247e8440");

            System.out.println(JSONObject.toJSONString(resultMsg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
