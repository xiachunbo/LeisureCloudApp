package com.drops;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.drops.nettyclient.NettyClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication(scanBasePackages = {"com.drops"}, exclude = {DataSourceAutoConfiguration.class})
@MapperScan({"com.drops.mapper.*"})
@EnableWebMvc
@EnableCaching
@EnableScheduling
public class Application
        implements CommandLineRunner {
    @Autowired
    private RestTemplateBuilder builder;
    @Value("${master.url}")
    private String url;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public RestTemplate restTemplate() {
        return this.builder.build();
    }


    public void run(String... strings) throws Exception {
        try {
            String[] nodes = this.url.split(",");
            for (String node : nodes) {
                NettyClient nettyClient = new NettyClient(node.split(":")[0], Integer.parseInt(node.split(":")[1]));
                nettyClient.connect();
                System.out.println("链接建立完成");
            }
        }
        catch (Exception e) {
            System.out.println("失败->" + e.toString());
        }

    }
}
