package com.kdps.common;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1,常量 2，环境变量
 * 
 * @author xuefeng
 *
 */
public class ConstantPool {
	private static Logger _log = LoggerFactory.getLogger(ConstantPool.class);
	
	//2017-2-4
	public static final String HDFS = "hdfs://192.168.206.151/:9000/";
	
	public static Properties prop;

//	static {
//
//		prop= new Properties();
//		InputStream in = null;
//		try {
//			String rootPath = ConstantPool.class.getResource("/").toURI()
//					.getPath();
//
//			in = new BufferedInputStream(new FileInputStream(rootPath
//					+ "kdps-test-config.properties"));
//			prop.load(in);
//
//			HADOOP_CONF_DIR = prop.getProperty("HADOOP_CONF_DIR",
//					System.getenv("HADOOP_CONF_DIR"));
//
//			HADOOP_HOME = prop.getProperty("HADOOP_HOME",
//					System.getenv("HADOOP_HOME"));
//			System.setProperty(
//					"hadoop.home.dir",
//					prop.getProperty("HADOOP_HOME",
//							System.getenv("HADOOP_HOME")));
//
//			// HDFS=prop.getProperty("HDFS");
//			HDFS_SUPER_USER = prop.getProperty("HDFS_SUPER_USER");
//			USER_HOME=prop.getProperty("USER_HOME");
////			USER_HOME = GlobalVar.getConfig().get("fs.defaultFS") + "/"
////					+ prop.getProperty("USER_HOME");
//			USER_GROUP = prop.getProperty("USER_GROUP");
//
//			JAR_PATH_LOCAL = prop.getProperty("JAR_PATH_LOCAL");
//			DEMO_JOB = prop.getProperty("DEMO_JOB");
//			DEMO_WF = prop.getProperty("DEMO_WF");
//			InitResourcePath = prop.getProperty("InitResourcePath");
//			TMP = prop.getProperty("TMP");
//			HiveServer2Url=prop.getProperty("HiveServer2Url");
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (URISyntaxException e) {
//
//			e.printStackTrace();
//		} finally {
//			try {
//				in.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
	

	// 解析控制台输出获取app_id 2016-10-17淘汰 原因方案修改为：通过API获取
	// public static final CharSequence YarnIdFlag =
	// "impl.YarnClientImpl: Submitted application";

	// 还要改core-site.xml
	// public static String HDFS = "hdfs://v-rn-hdtest.sz.kingdee.net:8020/";
	public static String HDFS_SUPER_USER;

	private static String USER_HOME;
	public static String USER_GROUP;
	

	// public static final String HADOOP_HOME = "/var/opt/hadoop/current";
	public static String TMP;
	public static String JAR_PATH_LOCAL;
	short mode = 700;

	
	public static void main(String[] args) {

	}

	public static String getUserHome() {
		return USER_HOME;
	}

}
