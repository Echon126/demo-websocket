package com.example.thymeleaf;

import com.example.thymeleaf.entity.User;
import com.example.thymeleaf.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Test
    public void contextLoads() {
    }

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void setData() {
        CountDownLatch downLatch = new CountDownLatch(1);
        User user = null;
      /*  for (int i = 0; i < 100000; i++) {
            user = new User();
            user.setAge(10+i);
            user.setUserName("zhansan"+i);
            this.redisUtil.set(user.getAge().toString(),user.toString());
        }*/
       /* for (int i1 = 0; i1 < 1000; i1++) {
            System.out.println(redisUtil.get("10" + i1));
        }*/

        for (int i = 0; i < 2; i++) {
            executorService.execute(new Res(redisUtil,downLatch));
        }
        downLatch.countDown();//计数器減一  所有线程释放 并发访问
    }

    public class Res implements Runnable{
        private RedisUtil redisUtil;
        private CountDownLatch downLatch;

        public Res(RedisUtil redisUtil, CountDownLatch downLatch) {
            this.redisUtil = redisUtil;
            this.downLatch = downLatch;
        }

        @Override
        public void run() {
            try {
                downLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i1 = 0; i1 < 1000; i1++) {
                System.out.println(redisUtil.set("10" + i1,"xczxcz"));
            }
        }
    }

}
