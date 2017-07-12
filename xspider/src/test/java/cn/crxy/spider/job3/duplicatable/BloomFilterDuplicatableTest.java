package cn.crxy.spider.job3.duplicatable;

import org.junit.Test;

public class BloomFilterDuplicatableTest {

	@Test
	public void test() {
		final BloomFilterDuplicatable bloomFilterDuplicatable = new BloomFilterDuplicatable();
		final String name = "吴超";
		bloomFilterDuplicatable.add(name);
		System.out.println(bloomFilterDuplicatable.is(name));
	}

}
