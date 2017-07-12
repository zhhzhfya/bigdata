package hbase;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.io.compress.Compression.Algorithm;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HbaseCrud {
	static Logger _log = LoggerFactory.getLogger(HbaseCrud.class);

	private static final String TABLE_NAME = "PEOPLE";
	private static final String COLUMN_FAMILY = "f2";

	public static void createOrOverwrite(Admin admin, HTableDescriptor table)
			throws IOException {

		if (admin.tableExists(table.getTableName())) {
			admin.disableTable(table.getTableName());
			admin.deleteTable(table.getTableName());
		}
		admin.createTable(table);
	}

	public static void createSchemaTables(Configuration config)
			throws IOException {

		Connection connection = ConnectionFactory.createConnection(config);
		Admin admin = connection.getAdmin();

		HTableDescriptor table = admin.getTableDescriptor(TableName
				.valueOf(TABLE_NAME));
		table.addFamily(new HColumnDescriptor(COLUMN_FAMILY)
				.setCompressionType(Algorithm.NONE));

		System.out.println("Creating table......");
		createOrOverwrite(admin, table);
		System.out.println("Done.");

	}

	public static void insert(Configuration conf) throws IOException {
		// HTable table = new HTable(conf,TABLE_NAME.getBytes());
		// Connection conn = new Connection(conf);
		Connection connection = ConnectionFactory.createConnection(conf);
		Table table = connection.getTable(TableName.valueOf(TABLE_NAME));

		Put put = new Put(Bytes.toBytes("0003"));
		List<Put> puts = new LinkedList<Put>();
		// put.addColumn("f2".getBytes(), "name".getBytes(),
		// "wangxiaoming".getBytes());
		// put.addColumn("f2".getBytes(), "age".getBytes(), "21".getBytes());
		// put.add(COLUMN_FAMILY.getBytes(), "name".getBytes(),new
		// Date().getTime(),
		// "wangxiaoming".getBytes());
		// put.add(COLUMN_FAMILY.getBytes(), "age".getBytes(), "21".getBytes());
		put.addColumn("f2".getBytes(), "name".getBytes(), new Date().getTime(),
				"wangxiaoming".getBytes());
		put.addColumn("f2".getBytes(), "age".getBytes(), new Date().getTime(),
				"21".getBytes());

		puts.add(put);
		table.put(puts);
		System.out.println("insert success!");
		table.close();
	}

	public static void inserTest(Configuration conf) throws IOException {
		// HTable table = new HTable(conf,TABLE_NAME.getBytes());
		// Connection conn = new Connection(conf);
		Connection connection = ConnectionFactory.createConnection(conf);
		Table table = connection.getTable(TableName.valueOf(TABLE_NAME));

		String key = "00011233";
		String f = "f2";
		String q = "qwe";
		String v = "0.933";
		// ,tech,0.933

		Put put = new Put(Bytes.toBytes(key));
		List<Put> puts = new LinkedList<Put>();
		// put.addColumn("f2".getBytes(), "name".getBytes(),
		// "wangxiaoming".getBytes());
		// put.addColumn("f2".getBytes(), "age".getBytes(), "21".getBytes());
		// put.add(COLUMN_FAMILY.getBytes(), "name".getBytes(),new
		// Date().getTime(),
		// "wangxiaoming".getBytes());
		// put.add(COLUMN_FAMILY.getBytes(), "age".getBytes(), "21".getBytes());

		put.addColumn(f.getBytes(), q.getBytes(), new Date().getTime(),
				v.getBytes());

		// put.addColumn(f.getBytes(), "age".getBytes(), new
		// Date().getTime(),v.getBytes());

		puts.add(put);
		table.put(puts);
		System.out.println("insert success!");
		table.close();
	}

	public static void scan(Configuration conf, String cf, String q,String tableName)
			throws IOException {
		// public static final byte[] CF = "cf".getBytes();
		// public static final byte[] ATTR = "attr".getBytes();

		byte[] ATTR = q.getBytes();
		byte[] CF = cf.getBytes();
		Connection connection = ConnectionFactory.createConnection(conf);
		
		Table table = connection.getTable(TableName.valueOf(tableName));
		Scan scan = new Scan();
		scan.addFamily(CF);
		// scan.addColumn(CF, ATTR);
		// scan.setRowPrefixFilter(Bytes.toBytes("row"));
		ResultScanner rs = table.getScanner(scan);
		try {
			for (Result r = rs.next(); r != null; r = rs.next()) {
				System.out.println("获得到rowkey:" + new String(r.getRow()));
				NavigableMap<byte[], byte[]> familyMap = r.getFamilyMap(CF);
				Set<byte[]> keySet = familyMap.keySet();
				for (byte[] bs : keySet) {
					_log.info("列：" + new String(bs, "UTF-8") + "====值:"
							+ new String(familyMap.get(bs), "UTF-8"));
					// + "====值:" + new String(familyMap.get(bs)));
					// familyMap.

				}

				// for (KeyValue keyValue : r.raw()) {
				// System.out.println("列：" + new String(keyValue.getFamily())
				// + "====值:" + new String(keyValue.getValue()));
				// }
				// _log.info(r.toString());
			}
		} finally {
			rs.close(); // always close the ResultScanner!
		}
	}
	public static void scan(Configuration conf,String tableName)
			throws IOException {
		// public static final byte[] CF = "cf".getBytes();
		// public static final byte[] ATTR = "attr".getBytes();
		
		
		Connection connection = ConnectionFactory.createConnection(conf);
		
		Table table = connection.getTable(TableName.valueOf(tableName));
		Scan scan = new Scan();
		// scan.addColumn(CF, ATTR);
		// scan.setRowPrefixFilter(Bytes.toBytes("row"));
		ResultScanner rs = table.getScanner(scan);
		try {
			for (Result r = rs.next(); r != null; r = rs.next()) {
				System.out.println("获得到rowkey:" + new String(r.getRow()));
				NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> map3Level = r.getMap();
				Set<byte[]> cfSet = map3Level.keySet();
				for (byte[] cf : cfSet) {
					NavigableMap<byte[], byte[]> familyMap = r.getFamilyMap(cf);
					Set<byte[]> keySet = familyMap.keySet();
					for (byte[] bs : keySet) {
						_log.info("列族：" + new String(cf, "UTF-8")+"列：" + new String(bs, "UTF-8") + "====值:"
								+ new String(familyMap.get(bs), "UTF-8"));
						// + "====值:" + new String(familyMap.get(bs)));
						// familyMap.
						
					}
				}
				
				
				// for (KeyValue keyValue : r.raw()) {
				// System.out.println("列：" + new String(keyValue.getFamily())
				// + "====值:" + new String(keyValue.getValue()));
				// }
				// _log.info(r.toString());
			}
		} finally {
			rs.close(); // always close the ResultScanner!
		}
	}

	public static void get(Configuration conf) throws IOException {
		// public static final byte[] CF = "cf".getBytes();
		// public static final byte[] ATTR = "attr".getBytes();
		byte[] CF = "name".getBytes();
		byte[] ATTR = "attr".getBytes();
		Get get = new Get(Bytes.toBytes("row1"));
		Connection connection = ConnectionFactory.createConnection(conf);
		Table table = connection.getTable(TableName.valueOf("userPortrait"));
		Result r = table.get(get);
		// r.r.getFamilyMap(CF).firstKey()
		byte[] b = r.getValue(CF, ATTR); // returns current version of value
	}

	public static void modifySchema(Configuration conf) throws IOException {
		Connection connection = ConnectionFactory.createConnection(conf);
		Admin admin = connection.getAdmin();

		TableName tableName = TableName.valueOf(TABLE_NAME);
		if (!admin.tableExists(tableName)) {
			System.out.println("Table does not exist.");
			System.exit(-1);
		}

		HTableDescriptor table = admin.getTableDescriptor(tableName);

		// Update existing table
		HColumnDescriptor newColumn = new HColumnDescriptor("NEWCF");
		newColumn.setCompactionCompressionType(Algorithm.GZ);
		newColumn.setMaxVersions(HConstants.ALL_VERSIONS);
		admin.addColumn(tableName, newColumn);

		// Update existing column family
		HColumnDescriptor existingColumn = new HColumnDescriptor(COLUMN_FAMILY);
		existingColumn.setCompactionCompressionType(Algorithm.GZ);
		existingColumn.setMaxVersions(HConstants.ALL_VERSIONS);
		table.modifyFamily(existingColumn);
		admin.modifyTable(tableName, table);

		// Disable an existing table
		admin.disableTable(tableName);

		// Delete an existing column family
		admin.deleteColumn(tableName, COLUMN_FAMILY.getBytes("UTF-8"));

		// Delete a table (Need to be disabled first)
		admin.deleteTable(tableName);

	}

	public static void main(String... args) throws IOException {
		// System.setProperty("hadoop.home.dir", ConstantPool.HADOOP_HOME);
		Configuration config = HBaseConfiguration.create();
		config.addResource(new Path("D:\\git_local_repo\\bigdata\\hbase\\target\\config",
				"hbase-site.xml"));
		config.addResource(new Path("D:\\git_local_repo\\bigdata\\hbase\\target\\config",
				"core-site.xml"));

		// Add any necessary configuration files (hbase-site.xml, core-site.xml)
		// config.addResource(new Path(System.getenv("HBASE_CONF_DIR"),
		// "hbase-site.xml"));
		// config.addResource(new Path(System.getenv("HADOOP_CONF_DIR"),
		// "core-site.xml"));

//		config.addResource("conf/hbase-site.xml");
//		config.addResource("core-site.xml");
		//
		// Connection connection = ConnectionFactory.createConnection(config);
		// Admin admin = connection.getAdmin();
		//
		// HTableDescriptor table = admin.getTableDescriptor(TableName
		// .valueOf(TABLE_NAME));
		// table.addFamily(new HColumnDescriptor(COLUMN_FAMILY)
		// .setCompressionType(Algorithm.NONE));
		// createOrOverwrite(admin, table);

		// createSchemaTables(config);
		//inserTest(config);
		// modifySchema(config);
		
		String cf = "portrait";
		String q = "attr";
		String tableName = "userPortrait";
		scan(config,tableName);
		// String bs ="\xE6\x89\x80\xE5\xB1\x9E\xE4\x" ;
		// _log.info(new String(bytes, charsetName));
	}
}
