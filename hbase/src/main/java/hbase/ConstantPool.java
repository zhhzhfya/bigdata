package hbase;



public class ConstantPool {
	

	//生产
	
	//任务类型
	public static final String CMD = "cmd";
	public static final String MapReduce = "mapreduce";
	public static final String Spark = "spark";
	
	//解析控制台输出获取app_id 2016-10-17淘汰  原因方案修改为：通过API获取
	//public static final CharSequence YarnIdFlag = "impl.YarnClientImpl: Submitted application";
	
	//还要改core-site.xml
	public static final String HDFS = "hdfs://v-rn-hdtest.sz.kingdee.net:8020/";
	public static final String HDFS_SUPER_USER = "kduser";
	
	public static final String USER_HOME = HDFS+"user/";
	public static final String USER_GROUP = "TEST_GROUP";
	//static final String PATH = "hdfs://192.168.206.151/:9000/";
	
	
	//public static final String HADOOP_HOME = "/var/opt/hadoop/current";
	public static final String TMP = "/tmp/hadoop-yarn/staging/";
	public static final String JAR_PATH_LOCAL = "/opt/user_kdps_lib/";
	short mode=774;
	
	
	//测试
	//linux 
	//public static final String HADOOP_HOME = "/opt/hadoop/current";
	public static final String InitResourcePath = "/opt/kdps_init_resource/";
	
	//windows
	public static final String HADOOP_HOME = "D:\\hadoop-common\\hadoop\\2.7.2";
	//工作流执行类型
	
	public static final int SpecifyTime = 1;
	public static final int Interval = 2;
	public static final int Periodicity = 3;
	
}
