package com.renren.jinkong.kylin.weixinnewsservice.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 模板消息数据对象
 *
 * @author lixin.tian@renren-inc.com
 * @date 2018/7/2 10:55
 */
@Data
public class TemplateData {
    private String touser;
    private String template_id;
    private String url;
    private JSONObject data;
}
