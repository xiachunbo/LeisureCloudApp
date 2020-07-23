package com.drops;

import com.drops.tools.SingletonPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.servlet.MultipartConfigElement;


@SpringBootApplication(scanBasePackages = {"com.drops"}, exclude = {DataSourceAutoConfiguration.class})
@EnableWebMvc
public class Application implements CommandLineRunner {
    @Value("${master.url}")
    private String url;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory config = new MultipartConfigFactory();
        config.setMaxFileSize("80MB");
        config.setMaxRequestSize("100MB");
        return config.createMultipartConfig();
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            String[] nodes = this.url.split(",");
            for (String node : nodes) {
                SingletonPool.nodePool.add(node.split(":")[0]+":"+ Integer.parseInt(node.split(":")[1]));
            }
            System.out.println( SingletonPool.nodePool);
        }
        catch (Exception e) {
            System.out.println("失败->" + e.toString());
        }
    }
}
