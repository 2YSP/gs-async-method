package cn.sp.service;

import cn.sp.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: 2YSP
 * @Description: GitHub 查找服务
 * @Date: Created in 2018/1/24
 */
@Service
public class GitHubLookUpService {

    private static final Logger logger = LoggerFactory.getLogger(GitHubLookUpService.class);

    private final RestTemplate restTemplate;

    public GitHubLookUpService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<User> findUser(String user)throws InterruptedException{
        logger.info("Looking for :"+user);
        String url = String.format("https://api.github.com/users/%s",user);
        User result = restTemplate.getForObject(url, User.class);
        //休眠一秒
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(result);
    }
}
