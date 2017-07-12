package spark.crxy.cdh.client;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Job2Run {
	public static void main(String[] args) {
		Logger _log = Logger.getLogger(Thread.currentThread().getStackTrace()[0]
				.getClassName());
		
		String appName = "test";
		SparkConf conf = new SparkConf().setAppName(appName);
		JavaSparkContext sc = new JavaSparkContext(conf);
		List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
		JavaRDD<Integer> distData = sc.parallelize(data);
		List<Integer> array = distData.toArray();
		for (Integer integer : array) {
			_log.info(integer);
		}
	}

}
