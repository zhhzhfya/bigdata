package cn.crxy.spider.job3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.crxy.spider.job3.download.Downloadable;
import cn.crxy.spider.job3.download.HttpClientDownload;
import cn.crxy.spider.job3.duplicatable.Duplicatable;
import cn.crxy.spider.job3.duplicatable.HashSetDuplicatable;
import cn.crxy.spider.job3.duplicatable.RedisDuplicatable;
import cn.crxy.spider.job3.process.JdProcess;
import cn.crxy.spider.job3.process.Processable;
import cn.crxy.spider.job3.repository.MemoryRepository;
import cn.crxy.spider.job3.repository.RedisRepository;
import cn.crxy.spider.job3.repository.Repository;
import cn.crxy.spider.job3.store.ConsoleStore;
import cn.crxy.spider.job3.store.FileStore;
import cn.crxy.spider.job3.store.Storable;
import cn.crxy.spider.job3.utils.SleepUtil;

public class Spider{
	final Logger logger = LoggerFactory.getLogger(getClass());
	private Config config = new Config();
	private Downloadable downloadable = new HttpClientDownload();
	private Processable processable;
	private Storable storable = new ConsoleStore();
	private Repository repository = new MemoryRepository();
	private Duplicatable duplicatable = new HashSetDuplicatable();
	private ThreadPool threadPool;
	
	/**
	 * 启动爬虫
	 */
	public void start() {
		check();
		initComponent();
		while(!Thread.currentThread().isInterrupted()) {
			//取出目标url
			final String url = repository.poll();
			if(url==null) {
				//TODO 如果为空，则等待
				
			}else {
				threadPool.run(
				new Runnable() {
					public void run() {
						//下载
						final Page page = Spider.this.download(url);
						//解析
						Spider.this.process(page);
						//把目标url放到队列中
						for (String nextUrl : page.getTargetUrls()) {
							if (duplicatable.is(nextUrl)) {
								continue;
							}
							duplicatable.add(nextUrl);
							if (nextUrl.startsWith("http://list.jd.com/")) {
								repository.addHigh(nextUrl);
							} else {
								repository.add(nextUrl);
							}
						}
						//导出
						Spider.this.store(page);
					}
				});
				SleepUtil.sleep(config.getExecuteSleep());
			}
		}
	}

	private void initComponent() {
		threadPool = new FixedThreadPool(this.getConfig().getThreadPoolSize());
		
		logger.info("=====================================================");
		logger.info("downloadable是{}", getDownloadable().getClass().getName());
		logger.info("processable是{}", getProcessable().getClass().getName());
		logger.info("storable是{}", getProcessable().getClass().getName());
		logger.info("repository是{}", getRepository().getClass().getName());
		logger.info("duplicatable是{}", getDuplicatable().getClass().getName());
		logger.info("threadPool是{}", getThreadPool().getClass().getName());
		logger.info("=====================================================");
	}

	/**
	 * 检查运行环境是否齐备
	 */
	private void check() {
		if(processable==null) {
			final String message = "请设置Processable";
			logger.error(message);
			throw new RuntimeException(message);
		}

	}

	public Page download(String url) {
		return this.downloadable.download(url);
	}


	public void process(Page page) {
		this.processable.process(page);
	}
	
	public void store(Page page) {
		this.storable.store(page);
	}


	public Downloadable getDownloadable() {
		return downloadable;
	}


	public void setDownloadable(Downloadable downloadable) {
		this.downloadable = downloadable;
	}

	public Processable getProcessable() {
		return processable;
	}

	public Spider setProcessable(Processable processable) {
		this.processable = processable;
		return this;
	}

	public Storable getStorable() {
		return storable;
	}

	public Spider setStorable(Storable storable) {
		this.storable = storable;
		return this;
	}

	public Repository getRepository() {
		return repository;
	}

	public Spider setRepository(Repository repository) {
		this.repository = repository;
		return this;
	}

	public Duplicatable getDuplicatable() {
		return duplicatable;
	}

	public Spider setDuplicatable(Duplicatable duplicatable) {
		this.duplicatable = duplicatable;
		return this;
	}

	public Spider setSeedUrl(String url) {
		this.repository.add(url);
		return this;
	}

	public ThreadPool getThreadPool() {
		return threadPool;
	}

	public void setThreadPool(ThreadPool threadPool) {
		this.threadPool = threadPool;
	}

	public Config getConfig() {
		return config;
	}

	public Spider setConfig(Config config) {
		this.config = config;
		return this;
	}

	public static void main(String[] args) {
		String url = "http://www.jd.com";
		if(args.length==1) {
			url = args[0];
		}
		final Config config2 = new Config();
		config2.setThreadPoolSize(5);
		config2.setExecuteSleep(200);
		
		final Spider spider = new Spider();
		spider
		.setConfig(config2)
		.setRepository(new RedisRepository())
		.setDuplicatable(new RedisDuplicatable())
		.setProcessable(new JdProcess())
		.setStorable(new FileStore()).setSeedUrl(url).start();
	}
}
