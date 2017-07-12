package cmd;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpUtil {
	

	static final long local_b = 2130706432L;
	static final long local_e = 2147483647L;

	static final long intranet_1_b = 167772160L;// 10.0.0.0:167772160
	static final long intranet_1_e = 184549375L;// 10.255.255.255:184549375

	static final long intranet_2_b = 2886729728L; // 172.16.0.0:2886729728
	static final long intranet_2_e = 2886795263L;// 172.16.255.255:2886795263

	static final long intranet_3_b = 2887712768L; // 172.31.0.0:2887712768,
	static final long intranet_3_e = 2887778303L;// 172.31.255.255:2887778303

	static final long intranet_4_b = 3232235520L; // 192.168.0.0:3232235520,
	static final long intranet_4_e = 3232301055L;// 192.168.255.255:3232301055

	static final long multicast_b = 3758096384L; // 224.0.0.0:3758096384
	static final long multicast_e = 3774873599L;// 224.255.255.255:3774873599

	static final long broadcast = 4294967295L; // 255.255.255.255:4294967295

	// int local_b=

	/**
	 * 将字符串型ip转成int型ip
	 * 
	 * @param strIp
	 * @return
	 */
	// public static long ip2Long(String strIp) {
	// String[] ss = strIp.split("\\.");
	// if (ss.length != 4) {
	// return 0;
	// }
	// byte[] bytes = new byte[ss.length];
	// for (int i = 0; i < bytes.length; i++) {
	// bytes[i] = (byte) Integer.parseInt(ss[i]);
	// }
	// return byte2Int(bytes);
	// }

	/**
	 * 将int型ip转成String型ip
	 * 
	 * @param intIp
	 * @return
	 */
	// public static String int2Ip(int intIp) {
	// byte[] bytes = int2byte(intIp);
	// StringBuilder sb = new StringBuilder();
	// for (int i = 0; i < 4; i++) {
	// sb.append(bytes[i] & 0xFF);
	// if (i < 3) {
	// sb.append(".");
	// }
	// }
	// return sb.toString();
	// }
	//
	// private static byte[] int2byte(int i) {
	// byte[] bytes = new byte[4];
	// bytes[0] = (byte) (0xff & i);
	// bytes[1] = (byte) ((0xff00 & i) >> 8);
	// bytes[2] = (byte) ((0xff0000 & i) >> 16);
	// bytes[3] = (byte) ((0xff000000 & i) >> 24);
	// return bytes;
	// }
	//
	// private static int byte2Int(byte[] bytes) {
	// int n = bytes[0] & 0xFF;
	// n |= ((bytes[1] << 8) & 0xFF00);
	// n |= ((bytes[2] << 16) & 0xFF0000);
	// n |= ((bytes[3] << 24) & 0xFF000000);
	// return n;
	// }
	
	public static boolean isIpv4(String ipAddress) {

		String ip = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
			    +"(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
			    +"(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
			    +"(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";

		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();

	}

	/**
	 * 判断是否是公网IP
	 * 
	 * @param ip
	 *            NetworkSegment
	 */
	public static boolean fromPublicNet(String ip) {
		return fromPublicNet(strIPToLng(ip));
	}

	public static boolean fromPublicNet(long ip) {

		if (((ip > local_b) && (ip <= local_e))
				|| ((ip >= intranet_1_b) && (ip <= intranet_1_e))
				|| ((ip >=intranet_2_b) && (ip <= intranet_2_e))
				|| ((ip >=intranet_3_b) && (ip <= intranet_3_e))
				|| ((ip >=intranet_4_b) && (ip <= intranet_4_e))
				|| ((ip >=multicast_b) && (ip <= multicast_e))
				|| (ip == broadcast)

		){
			return false;
		}
			return true;
	}

	public static long strIPToLng(String iP) {
		String[] addressArray = iP.split("\\.");

		long result = 0;

		for (int i = 0; i < addressArray.length; i++) {
			int power = 3 - i;

			result += ((Integer.parseInt(addressArray[i]) % 256 * Math.pow(256,
					power)));
		}

		return result;
	}

	public static String LongIPToStr(long IP) {
		return ((IP >> 24) & 0xFF) + "." + ((IP >> 16) & 0xFF) + "."
				+ ((IP >> 8) & 0xFF) + "." + (IP & 0xFF);
	}
	
	public static long getTimestamp(long timeStamp, int deltaDay, int deltaHour, int DeltaMin){ 
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeStamp);
		cal.add(Calendar.DATE, deltaDay);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, DeltaMin);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	} 

	public static void main(String[] args) {
		System.out.println( getTimestamp(1479657645000l, -180,0,0));


//		_log.info("192.168.0.1:" + strIPToLng("192.168.0.1"));
//
//		_log.info("127.0.0.0:" + strIPToLng("127.0.0.0"));
//		_log.info("127.255.255.255:" + strIPToLng("127.255.255.255"));
//
//		_log.info("255.255.255.255:" + strIPToLng("255.255.255.255"));
//
//		_log.info("10.0.0.0:" + strIPToLng("10.0.0.0"));
//		_log.info("10.255.255.255:" + strIPToLng("10.255.255.255"));
//
//		_log.info("172.16.0.0:" + strIPToLng("172.16.0.0"));
//		_log.info("172.16.255.255:" + strIPToLng("172.16.255.255"));
//
//		_log.info("172.31.0.0:" + strIPToLng("172.31.0.0"));
//		_log.info("172.31.255.255:" + strIPToLng("172.31.255.255"));
//
//		_log.info("192.168.0.0:" + strIPToLng("192.168.0.0"));
//		_log.info("192.168.255.255:" + strIPToLng("192.168.255.255"));
//
//		_log.info("224.0.0.0:" + strIPToLng("224.0.0.0"));
//		_log.info("224.255.255.255:" + strIPToLng("224.255.255.255"));

		
		
		
		// _log.info("192.168.0.1:" + ip2Long("192.168.0.1"));
		//
		// _log.info("127.0.0.0:" + ip2Long("127.0.0.0"));
		// _log.info("127.255.255.255:" + ip2Long("127.255.255.255"));
		//
		// _log.info("224.0.0.0:" + ip2Long("224.0.0.0"));
		// _log.info("224.255.255.255:" + ip2Long("224.255.255.255"));
		//
		// _log.info("255.255.255.255:" + ip2Long("255.255.255.255"));
		//
		// _log.info("10.0.0.0:" + ip2Long("10.0.0.0"));
		// _log.info("10.255.255.255:" + ip2Long("10.255.255.255"));
		//
		// _log.info("172.16.0.0:" + ip2Long("172.16.0.0"));
		// _log.info("172.16.255.255:" + ip2Long("172.16.255.255"));
		//
		// _log.info("172.31.0.0:" + ip2Long("172.31.0.0"));
		// _log.info("172.31.255.255:" + ip2Long("172.31.255.255"));
		//
		// _log.info("192.168.0.0:" + ip2Long("192.168.0.0"));
		// _log.info("192.168.255.255:" + ip2Long("192.168.255.255"));

		// int intIp = ip2Int(ip1);
		// String ip2 = int2Ip(intIp);
		// System.out.println(ip2.equals(ip1));
	}

}
