package hbase;

import java.io.IOException;
import java.util.Map;
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
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.io.compress.Compression.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HbaseAPP {
	static Logger _log = LoggerFactory.getLogger(HbaseAPP.class);
	
	private static final String TABLE_NAME = "userPortrait";
	private static final String CF_DEFAULT = "DEFAULT_COLUMN_FAMILY";

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
		try (Connection connection = ConnectionFactory.createConnection(config);
				Admin admin = connection.getAdmin()) {

			HTableDescriptor table = new HTableDescriptor(
					TableName.valueOf(TABLE_NAME));
			table.addFamily(new HColumnDescriptor(CF_DEFAULT)
					.setCompressionType(Algorithm.NONE));

			System.out.print("Creating table. ");
			createOrOverwrite(admin, table);
			System.out.println(" Done.");
		}
	}
	
	public static void modifySchema(Configuration config) throws IOException {
		try (Connection connection = ConnectionFactory.createConnection(config);
				Admin admin = connection.getAdmin()) {

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
			HColumnDescriptor existingColumn = new HColumnDescriptor(CF_DEFAULT);
			existingColumn.setCompactionCompressionType(Algorithm.GZ);
			existingColumn.setMaxVersions(HConstants.ALL_VERSIONS);
			table.modifyFamily(existingColumn);
			admin.modifyTable(tableName, table);

			// Disable an existing table
			admin.disableTable(tableName);

			// Delete an existing column family
			admin.deleteColumn(tableName, CF_DEFAULT.getBytes("UTF-8"));

			// Delete a table (Need to be disabled first)
			admin.deleteTable(tableName);
		}
	}
	public static void descripSchema(Configuration config) throws IOException {
		try (Connection connection = ConnectionFactory.createConnection(config);
				Admin admin = connection.getAdmin()) {
			
			TableName tableName = TableName.valueOf(TABLE_NAME);
			if (!admin.tableExists(tableName)) {
				System.out.println("Table does not exist.");
				System.exit(-1);
			}
			
			HTableDescriptor table = admin.getTableDescriptor(tableName);
			Set<byte[]> familiesKeys = table.getFamiliesKeys();
			for (byte[] bs : familiesKeys) {
				_log.info("列簇："+new String(bs,"UTF-8"));
				//table.get
			}
			HColumnDescriptor[] columnFamilies = table.getColumnFamilies();
			//columnFamilies.
			for (int i = 0; i < columnFamilies.length; i++) {
				Map<ImmutableBytesWritable, ImmutableBytesWritable> values = columnFamilies[i].getValues();
				Set<ImmutableBytesWritable> keySet = values.keySet();
				for (ImmutableBytesWritable immutableBytesWritable : keySet) {
					_log.info(new String(immutableBytesWritable.get(),"UTF-8")+" ： "+new String(values.get(immutableBytesWritable).get(),"UTF-8"));
					
				}
				
				
			}
			
			
		}
	}

	

	public static void main(String... args) throws IOException {
		Configuration config = HBaseConfiguration.create();

		// Add any necessary configuration files (hbase-site.xml, core-site.xml)
		// config.addResource(new Path(System.getenv("HBASE_CONF_DIR"),
		// "hbase-site.xml"));
		// config.addResource(new Path(System.getenv("HADOOP_CONF_DIR"),
		// "core-site.xml"));

		String src = HbaseAPP.class.getResource("/").toString();
		config.addResource(new Path(src + "hbase-site.xml"));
		config.addResource(src + "core-site.xml");
//		createSchemaTables(config);
//		modifySchema(config);

		// file:/D:/git_local_repo/bigdata/hbase/target/classes/hbase/
		_log.info(HbaseAPP.class.getResource("").toString());
		// D:/git_local_repo/bigdata/hbase/target/classes/

		_log.info(src);
		descripSchema(config);
	}

}
