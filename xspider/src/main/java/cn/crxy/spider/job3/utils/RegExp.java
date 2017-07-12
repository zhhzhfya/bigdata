package cn.crxy.spider.job3.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {
	public static String parse(String express, String content) {
		final Pattern pattern = Pattern.compile(express);
		final Matcher matcher = pattern.matcher(content);
		if(matcher.find()) {
			return matcher.group(0);
		}
		return null;
	}
}
