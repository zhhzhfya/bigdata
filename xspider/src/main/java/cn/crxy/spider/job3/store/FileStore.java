package cn.crxy.spider.job3.store;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.crxy.spider.job3.Page;

public class FileStore implements Storable {
	final Logger logger = LoggerFactory.getLogger(getClass());
	private String basePath;
	private int flag = 1;
	
	public FileStore() {
		this(".", 1);
	}
	
	public FileStore(String basePath) {
		this(basePath, 1);
	}
	
	public FileStore(String basePath, int flag) {
		this.basePath = basePath;
		this.flag = flag;
		checkExist();
	}
	
	private void checkExist() {
		final File dir = new File(basePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}else {
			if(!dir.isDirectory()) {
				dir.delete();
				dir.mkdirs();
			}
		}
	}

	public void store(Page page) {
		final String valuesToJSON = page.valuesToJSON();
		
		try {
			FileUtils.write(new File(basePath+File.separator+fileName()), valuesToJSON);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	private String fileName() {
		switch (flag) {
		case 1:
			return DateUtils.formatDate(new Date(), "yyyy年MM月dd日HH时mm分ss秒SSS");
		default:
			return null;
		}
		
	}

}
