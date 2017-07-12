package xspider;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {
		final Jedis jedis = new Jedis("122.0.67.172", 6379);
		jedis.select(0);
		final String name = jedis.get("name");
		System.out.println(name);


	}

}
