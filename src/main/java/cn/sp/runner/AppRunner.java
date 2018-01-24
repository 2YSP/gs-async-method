package cn.sp.runner;

import cn.sp.bean.User;
import cn.sp.service.GitHubLookUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: 2YSP
 * @Description: 
 * @Date: Created in 2018/1/24
 */
@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);


    private final GitHubLookUpService gitHubLookUpService;

    public AppRunner(GitHubLookUpService gitHubLookUpService){
        this.gitHubLookUpService = gitHubLookUpService;
    }

    @Override
    public void run(String... strings) throws Exception {
        long start = System.currentTimeMillis();

        CompletableFuture<User> page1 = gitHubLookUpService.findUser("PivotalSoftware");
        CompletableFuture<User> page2 = gitHubLookUpService.findUser("CloudFoundry");
        CompletableFuture<User> page3 = gitHubLookUpService.findUser("Spring-Projects");

        //wait until they are all done
        CompletableFuture.allOf(page1,page2,page3).join();

        //Print results
        logger.info("Elapsed time:"+(System.currentTimeMillis()-start));
        logger.info("-->"+page1.get());
        logger.info("-->"+page2.get());
        logger.info("-->"+page3.get());
    }
}
