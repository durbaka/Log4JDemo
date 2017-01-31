package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class restRedis {

	@Autowired
	private StringRedisTemplate template;
	
	@RequestMapping("/redis")
	public String redishello() throws Exception {
		
		
		
		ValueOperations<String, String> ops = this.template.opsForValue();
		String key = "spring.boot.redis.test3";
		if (!this.template.hasKey(key)) {
			ops.set(key, "foo-Test-szjrrhszje");
		}
		System.out.println("Found key " + key + ", value=" + ops.get(key));
		return "success!!!!";
	}
	
	
}
