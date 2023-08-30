package com.huike.test;

import com.huike.utils.BcryptUtils;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class AppTest {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void bcryptEncodePassword() {
        for (int i = 0; i < 10; i++) {
            String gensalt = BCrypt.gensalt();
            System.out.println(gensalt);
            String pass = BCrypt.hashpw("123456", gensalt);
            System.out.println(pass);
        }
    }

    @Test
    public void checkPassword() {
        boolean checkpw = BCrypt.checkpw("123456", "$2a$10$5RJVltp4fSM4H.5YCinn.e9U.hDtNUiuZnL0PtYAqvAXVZkRcBC6K");
        System.out.println(checkpw);
    }

    @Test
    public void getPassword2() {
        System.out.println(BcryptUtils.hashPassword("123456"));
    }

    @Test
    public void checkPassword2() {
        boolean checkPassword = BcryptUtils.checkPassword("123456", "$2a$10$KLiyLLT6/pjiNta/VixFf.y4fy28VXCw75vHtaFAVDwKNut/PO0pe");
        System.out.println(checkPassword);
    }

    @Test
    public void testRedisOps() {
        redisTemplate.opsForValue().set("Key1", "Value1");
        Object value = redisTemplate.opsForValue().get("Key1");
        System.out.println(value);
    }
}
