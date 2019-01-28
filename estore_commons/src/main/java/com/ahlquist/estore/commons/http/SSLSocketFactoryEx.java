package com.ahlquist.estore.commons.http;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.log4j.Logger;

/**
 * Factory for SSL Socket Creation Note: many methods are deprecated in the
 * current apache.http libraries.
 * 
 * @author dahlquist
 *
 */
@SuppressWarnings("deprecation")
public class SSLSocketFactoryEx extends SSLSocketFactory {

	final static Logger logger = Logger.getLogger(SSLSocketFactoryEx.class);
	final SSLContext sslContext = SSLContext.getInstance("TLS");

	private static SSLSocketFactoryEx instance = null;

	public static SSLSocketFactoryEx getInstance() {

		if (instance == null) {
			try {
				KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				trustStore.load(null, null);
				instance = new SSLSocketFactoryEx(trustStore);
				instance.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			} catch (Exception e) {
				logger.error(e);
			}
		}
		return instance;
	}

	/**
	 * 
	 * @param truststore
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 */
	public SSLSocketFactoryEx(KeyStore truststore)
			throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
		super(truststore);

		TrustManager tm = new X509TrustManager() {

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		sslContext.init(null, new TrustManager[] { tm }, null);
	}

	/**
	 * interface implementation methods
	 */
	@Override
	public Socket createSocket() throws IOException {
		return sslContext.getSocketFactory().createSocket();
	}

	/**
	 * interface implementation methods
	 */
	@Override
	public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
			throws IOException, UnknownHostException {
		return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
	}
}
