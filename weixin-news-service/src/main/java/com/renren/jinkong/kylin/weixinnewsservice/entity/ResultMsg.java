package com.renren.jinkong.kylin.weixinnewsservice.entity;

import lombok.Data;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/7/2 10:44
 */
@Data
public class ResultMsg<T> {
    private int status;
    private T data;
    private String msg;

    public enum ResultType {
        SUCCESS(200),
        FAILURE(100);

        private int value;

        ResultType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}
