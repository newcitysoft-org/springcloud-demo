package com.renren.jinkong.kylin.weixinnewsservice.api;

import com.alibaba.fastjson.JSONObject;
import com.renren.jinkong.kylin.weixinnewsservice.entity.ResultMsg;
import com.renren.jinkong.kylin.weixinnewsservice.entity.TemplateData;
import com.renren.jinkong.kylin.weixinnewsservice.util.HttpUtil;

import java.io.IOException;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/7/2 10:55
 */
public class TemplateApi {

    private static final String URL_SEND_API = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    public static ResultMsg<String> send(String accessToken, TemplateData data) throws IOException {
        ResultMsg<String> resultMsg = new ResultMsg<>();
        String json = JSONObject.toJSONString(data);

        String result = HttpUtil.postJson(URL_SEND_API + accessToken, json);
        JSONObject jsonObject = JSONObject.parseObject(result);

        Integer errcode = jsonObject.getInteger("errcode");
        if(errcode != 0) {
            resultMsg.setStatus(ResultMsg.ResultType.FAILURE.getValue());
            resultMsg.setMsg(jsonObject.getString("errmsg"));
        } else {
            resultMsg.setStatus(ResultMsg.ResultType.SUCCESS.getValue());
            resultMsg.setData(jsonObject.getString("msgid"));
            resultMsg.setMsg(jsonObject.getString("errmsg"));
        }

        return resultMsg;
    }

    public static void main(String[] args) {
        try {
            ResultMsg<String> resultMsg = AccessTokenApi.getToken("wx6080c31355b4073e", "5224a224c6912d26c0b6ade3247e8440");
            String token = resultMsg.getData();
            TemplateData data = new TemplateData();

            data.setTouser("oSBJluDyIsVYTKY-dNfWU-BwrI7s");
            data.setTemplate_id("4Lun6flnjWQ_poIUQdmzhpQ145Nk0Hg7n-9HNs8WKe8");
            data.setUrl("https://www.baidu.com");

            JSONObject body = new JSONObject();

            JSONObject body1 = new JSONObject();
            body1.put("value", "恭喜你购买成功！");
            body1.put("color", "#173177");

            body.put("first", body1);

            JSONObject body2 = new JSONObject();
            body2.put("value", "巧克力");
            body2.put("color", "#173177");

            body.put("keyword1", body2);

            JSONObject body3 = new JSONObject();
            body3.put("value", "39.8元");
            body3.put("color", "#173177");

            body.put("keyword2", body3);

            JSONObject body4 = new JSONObject();
            body4.put("value", "2014年9月22日");
            body4.put("color", "#173177");

            body.put("keyword3", body4);

            JSONObject body5 = new JSONObject();
            body5.put("value", "欢迎再次购买！");
            body5.put("color", "#173177");

            body.put("remark", body5);

            data.setData(body);

            ResultMsg<String> send = send(token, data);
            System.out.println(JSONObject.toJSONString(data));

            System.out.println(JSONObject.toJSONString(send));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
