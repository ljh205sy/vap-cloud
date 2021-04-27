package com.vrv.vap.admin;

import com.vrv.vap.vapservice.EnableRyFeignClients;
import com.vrv.vap.vapservice.VapCloudApplication;
import org.springframework.boot.SpringApplication;

/**
 * @author wh1107066
 */
@VapCloudApplication
@EnableRyFeignClients
public class ApiAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiAdminApplication.class, args);
    }

}
