package cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TimeZone;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class TActivity {
	

	public static class TActivityMap extends Mapper<Object, Text, IntWritable, Text>{
		private final int TID_INDEX = 13;
		private final int IP_INDEX = 17;
		private final int TIME_INDEX = 11;//18
		private int MAX_INDEX = 19;
		protected void setup(Context context) throws IOException{
		}
		
        public void map( Object key, Text value, Context context)
                throws IOException,InterruptedException{
        	IntWritable outKey = new IntWritable();
        	Text outValue = new Text();
        	String dataString = value.toString();
        	String[] dataArray = null;
        	String ipString = null;
        	long ipLong = 0l;
        	int tid = 0;
        	String dateTime = null;
        	try {
        		dataArray = dataString.split( "\01", -1);
        		if ( dataArray.length >= MAX_INDEX) {
        			ipString = dataArray[IP_INDEX];
        			dateTime = dataArray[ TIME_INDEX];
        			tid = Integer.valueOf(dataArray[ TID_INDEX]);
        			if( IpUtil.isIpv4( ipString)) {
        				ipLong = IpUtil.strIPToLng( ipString);
        			}
        		}
        		outKey.set( tid);
        		outValue.set(new StringBuilder().append( ipLong).append( '\01').append( dateTime).toString());
        	} catch( Exception e) {
        		return ;
        	}
			context.write(outKey, outValue);
        }
	}
	public static class TActivityReducer extends Reducer<IntWritable, Text, Text, Text>  {
	    public void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
	    	Text outKey = new Text();
	    	Text outValue = new Text();
	    	long todayTimeStamp = 0l;
	    	long day30TimeStamp = 0l;
	    	long day90TimeStamp = 0l;
	    	long day180TimeStamp = 0l;
	    	int logNum30 = 0;
	    	int logNum90 = 0;
	    	int logNum180 = 0;
	    	long maxTimeStamp = 0l;
	    	long minTimeStamp = Long.MAX_VALUE;
	    	long maxIp = 0l;
	    	int level = 0;
	    	long curTime = System.currentTimeMillis();
	    	day30TimeStamp = getTimestamp( curTime, -30, 0,0);
	    	day90TimeStamp = getTimestamp( curTime, -90, 0,0);
	    	day180TimeStamp = getTimestamp( curTime, -180, 0,0);
	    	todayTimeStamp = getTimestamp( curTime, 0, 0, 0);
	    	for( Text value: values) {
	    		String[] dataArray = value.toString().split("\01",-1);
	    		long tmpTime = 0l;
	    		if( dataArray.length >= 2) {
	    			tmpTime =  getTimestampLong( dataArray[1]);
	    		}
	    		if( tmpTime >= day30TimeStamp && tmpTime < todayTimeStamp) {
	    			logNum30 += 1;
	    		}
	    		if( tmpTime >= day90TimeStamp && tmpTime < day30TimeStamp) {
	    			logNum90 += 1;
	    		}
	    		if( tmpTime >= day180TimeStamp && tmpTime < day180TimeStamp) {
	    			logNum180 += 1;
	    		}
	    		if( tmpTime > maxTimeStamp) {
	    			maxTimeStamp = tmpTime;
	    			maxIp = Long.valueOf( dataArray[0]);
	    		}
	    		if( tmpTime < minTimeStamp) {
	    			tmpTime = minTimeStamp;
	    		}
	    	}
	    	level = activityLevel(logNum30, logNum90, logNum180);
	    	outKey.set( new StringBuilder().append(key.get()).append('\01').append( level).append('\01')
	    			.append( maxIp).append('\1').append( "").append('\1').append( getTimestampStr( curTime)).toString()
	    			);
	    	context.write( outKey, null );
	    } 
	    
	    public String getTimestampStr( long stamp) {
	    	SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
	    	sdf.setTimeZone( TimeZone.getTimeZone("GMT+8"));
	    	return sdf.format( new Date( stamp));
	    }
	    
	    public long getTimestampLong( String stamp) {
	    	long timeStamp = 0l;
	    	SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
	    	sdf.setTimeZone( TimeZone.getTimeZone("GMT+8"));
	    	try {
	    		timeStamp = sdf.parse(stamp).getTime();
			} catch (ParseException e) {
			
			}
	    	return timeStamp;
	    }
	    
	    public int activityLevel( int logNum30, int logNum90, int logNum180) {
	    	int level = 0;
	    	if( logNum30 >= 4) {
	    		level = 1;
	    	} else if ( logNum30 >= 0) {
	    		level = 2;
	    	} else if ( logNum90 >= 0) {
	    		level = 3;
	    	} else {
	    		level = 4;
	    	}
	    	return level;
	    }
	    
	    public static long getTimestamp(long timeStamp, int deltaDay, int deltaHour, int DeltaMin){ 
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(timeStamp);
			cal.add(Calendar.DATE, deltaDay);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, DeltaMin);
			cal.set(Calendar.MILLISECOND, 0);
			return cal.getTime().getTime();
		} 
	}
	
	/*
	 * 添加当天的日志到统计任务
	 */
	public static void addFile(Job job, Configuration conf, String  fileNameString) throws IOException{
		FileSystem fs ;
		Set<Path> logFileSet = new HashSet<Path>();
		fs = FileSystem.get(conf);
		FSDataInputStream dis = fs.open(new Path(fileNameString));
		InputStreamReader isr = new InputStreamReader(dis, "utf-8");  
		BufferedReader br = new BufferedReader(isr);
		String tmpString = null;
		while((tmpString = br.readLine()) != null){
			
			logFileSet.addAll(getFileList( tmpString , fs));
		}
		for(Iterator<Path> iterator = logFileSet.iterator(); iterator.hasNext();){
			Path curPath = iterator.next();
			FileInputFormat.addInputPath(job, curPath);
		}
	}
	
	public static Set<Path> getFileList(String dirName ,FileSystem fs) throws IllegalArgumentException, IOException{
		Set<Path> logFileSet = new HashSet<Path>();
		Path dirPath = new Path(dirName);
		if(fs.isDirectory( dirPath)){
			FileStatus[] fileStatuses = fs.listStatus( dirPath);
			for( FileStatus tmpFileStatus: fileStatuses){
				String fileNameString = tmpFileStatus.getPath().getName();
				if (is90DayLog( fileNameString)){
					logFileSet.add(tmpFileStatus.getPath());
				}
			}
		}
		return logFileSet;
	}
	
	/**
	 * is 90 days log
	 * @param fileNameString
	 * @return
	 */
	public static boolean is90DayLog(String fileNameString){
		long timeStamp = 0l ;
		try{
			timeStamp = Long.valueOf(fileNameString.split("\\.", -1)[0]);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		timeStamp = timeStamp * 1000l;
		long beginTimeStamp = TActivityReducer.getTimestamp( timeStamp, -180, 0, 0);
		long endTimeStamp = TActivityReducer.getTimestamp( timeStamp, 1, 1, 20);
		if (timeStamp > beginTimeStamp && timeStamp <= endTimeStamp) {
			return true;
		}
		return false;
	}
	
	public static Job init(String[] args) throws IOException{
		/*
		 * @args : Input dataset
		 */
		String inFileName = args[0];
		String outFileName = args[1];
		Path path = new Path(inFileName);
		System.out.println( "########## in file " + inFileName );
		System.out.println( "########## out file " + outFileName );
		Configuration conf = new Configuration();
		conf.setLong("curtimestamp", System.currentTimeMillis());
		Job job = new Job(conf, "k3cloud" );
		job.setJarByClass( TActivity.class);
		job.setMapperClass( TActivityMap.class);
		job.setReducerClass( TActivityReducer.class);
		job.setNumReduceTasks(2);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);
		addFile( job,  conf, inFileName);
		FileOutputFormat.setOutputPath(job, new Path(outFileName));
		//FileOutputFormat.setCompressOutput(job, true);
		//FileOutputFormat.setOutputCompressorClass(job, org.apache.hadoop.io.compress.GzipCodec.class);
		return job;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		if ( args.length != 2 ) {
			System.out.println( "Usage: LogTransform <inFileName> <outFileName>" );
			return;
		}
		
		Job job = init( args);
		boolean b = job.waitForCompletion(true);
		if (!b) {
			throw new IOException("error with job!");
		}
	}
}
