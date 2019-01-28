package com.ahlquist.estore.commons;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Utils {

	/**
	 * get a UUID
	 * 
	 * @return UUID
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}

	public static String getStringFromStream(InputStream inputStream) {

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[1024];
		try {
			while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(buffer.toByteArray(), StandardCharsets.UTF_8);
	}
	
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	
	public static String timestampToString(final String format, final Timestamp time) {
		Long millis = time.getTime();
		return new SimpleDateFormat(format).format(new Date(millis));
	}
	
	public static String timestampAsString(Timestamp timestamp) {
	    return timestampToString(DEFAULT_DATE_FORMAT, timestamp);
	}
	
	public static Timestamp stringToTimestamp(final String format, final String dateString) {
		
		Timestamp timestamp = null;
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		    Date parsedDate = dateFormat.parse(dateString);
		    timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) { 
		   e.printStackTrace();
		}
		return timestamp;
	}
    
	public static Timestamp stringToTimestamp(final String dateString) {
		return stringToTimestamp(DEFAULT_DATE_FORMAT, dateString);
	}

}