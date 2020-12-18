package com.drops;

import com.drops.nettyclient.NettyClient;
import com.drops.service.AsyncThreadService;
import com.github.pagehelper.PageHelper;
import java.nio.charset.Charset;
import java.util.Properties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = {"com.drops"}, exclude = {DataSourceAutoConfiguration.class})
@MapperScan({"com.drops.mapper.*"})
@ComponentScan(basePackages = {"com.drops"})
@EnableWebMvc
@EnableCaching
@EnableAsync//开启异步
@EnableScheduling
public class Application
        implements CommandLineRunner {
    @Autowired
    private RestTemplateBuilder restTemplateBuilider;
    @Autowired
    private AsyncThreadService threadService;

    @Value("${master.url}")
    private String url;

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Application.class, args);
        System.out.println("【Schedule定时任务微服务】已启动.");
    }

    @Bean
    public PageHelper getPageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    @Bean
    public RestTemplate restTemplate() {
        StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(m).build();
        return restTemplate;
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }

    public void run(String... strings) throws Exception {
        try {
            if(this.url !=null && !"".equals(this.url)){
                String[] nodes = this.url.split(",");
                for (String node : nodes) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            NettyClient nettyClient = new NettyClient(node.split(":")[0], Integer.parseInt(node.split(":")[1]));
                            try {
                                nettyClient.connect();
                            } catch (Exception e) {
                                //e.printStackTrace();
                            }
                        }
                    }).start();
                    System.out.println("链接建立完成");
                }
            }
            //for (int i = 0;i<1;i++)
                //threadService.doReturn(i);

        }
        catch (Exception e) {
            System.out.println("失败->" + e.toString());
        }

    }
}