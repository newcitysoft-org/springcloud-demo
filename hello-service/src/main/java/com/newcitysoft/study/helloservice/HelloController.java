package com.newcitysoft.study.helloservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/6/26 15:19
 */
@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();

        System.out.println("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());

        return "Hello World!";
    }
}
