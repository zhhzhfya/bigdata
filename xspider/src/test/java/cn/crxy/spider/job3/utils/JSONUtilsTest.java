package cn.crxy.spider.job3.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class JSONUtilsTest {

	@Test
	public void test() {
		Map<String, String> values = new HashMap<String, String>();
		values.put("name", "zhagnsna");
		values.put("age", "22");
		final String jsonString = JSONUtils.parseMap(values );
		System.out.println(jsonString);
	}

}
