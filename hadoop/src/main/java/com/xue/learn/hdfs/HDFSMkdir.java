package com.xue.learn.hdfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSMkdir {
	public static void main(String[] args){
		Configuration config=new Configuration();
		FileSystem fSystem;
		try {
			fSystem=FileSystem.get(config);
			fSystem.mkdirs(new Path("/dir01/dir001"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
}
