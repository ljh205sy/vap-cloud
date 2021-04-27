package com.vrv.vap.zuul;

import com.vrv.vap.vapservice.EnableRyFeignClients;
import com.vrv.vap.vapservice.VapCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author wh1107066
 */
@EnableZuulProxy
@VapCloudApplication
@EnableRyFeignClients
public class VapZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(VapZuulApplication.class, args);
    }

}
