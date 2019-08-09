package com.zzh.multiDatasource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiDatasourceApplicationTests {

	@Autowired
	RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {

		Map<String, String> m1 = new HashMap<>();
		Map<String, String> m2 = new HashMap();
		m1.put("1", "a_db");
		m1.put("2", "b_db");
		m1.put("3", "c_db");
		m2.put("1", "127.0.0.1");
		m2.put("2", "127.0.0.1");
		m2.put("3", "127.0.0.1");

		redisTemplate.opsForHash().putAll("dbconf", m1);
		redisTemplate.opsForHash().putAll("dbip", m2);


	}

}
