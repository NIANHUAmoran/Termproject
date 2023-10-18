package com.example.termproject;

import com.pig4cloud.pig.common.feign.annotation.EnablePigFeignClients;
import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import com.pig4cloud.pig.common.swagger.annotation.EnablePigDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnablePigDoc("termproject")
@EnablePigFeignClients(basePackages = {"com.pig4cloud.pig", "com.example.termproject"})
@EnablePigResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class TermprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TermprojectApplication.class, args);
	}

}
