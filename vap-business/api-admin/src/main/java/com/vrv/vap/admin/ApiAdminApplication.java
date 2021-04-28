package com.vrv.vap.admin;

import com.vrv.vap.vapservice.EnableRyFeignClients;
import com.vrv.vap.vapservice.VapCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wh1107066
 */
@VapCloudApplication
@EnableRyFeignClients
@EnableTransactionManagement
public class ApiAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiAdminApplication.class, args);
    }

}
