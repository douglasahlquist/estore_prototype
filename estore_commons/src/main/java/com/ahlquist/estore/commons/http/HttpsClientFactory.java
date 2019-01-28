package com.ahlquist.estore.commons.http;

import java.util.HashMap;

import javax.validation.constraints.NotNull;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import com.ahlquist.estore.commons.Utils;

/**
 * Creates a new Client for HTTP connections GET and POST client methods are
 * provided.
 */
@SuppressWarnings("deprecation")
public class HttpsClientFactory {

	private HttpClient httpClient;

	public HttpsClientFactory() {
		try {
			SSLSocketFactory sf = SSLSocketFactoryEx.getInstance();

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

			httpClient = (HttpClient) new DefaultHttpClient(ccm, params);
		} catch (Exception e) {
			httpClient = (HttpClient) new DefaultHttpClient();
		}
	}

	/**
	 * Gets an HTTPS client for the GET Methods having an SSL connection
	 * 
	 * @param url
	 * @return VrsResponseEntity<String>
	 */
	public EcResponseEntity<String> httpGetWithCookie(@NotNull final String domain, @NotNull final String url,
			@NotNull final String cookieName, @NotNull final HashMap<String, String> cookies) {

		BasicCookieStore cookieStore = new BasicCookieStore();
		// for now this works for only `login-token`
		BasicClientCookie cookie = new BasicClientCookie(cookieName, cookies.get("login-token"));
		cookieStore.addCookie(cookie);
		cookie.setDomain(domain);
		cookie.setPath("/");

		HttpContext localContext = new BasicHttpContext();
		localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);

		HttpGet httpGet = new HttpGet("https://" + domain + "/" + url);
		return execute(httpGet, localContext);
	}

	/**
	 * Gets an HTTPS client for the GET Methods having an SSL connection
	 * 
	 * @param url
	 * @return VrsResponseEntity<String>
	 */
	public EcResponseEntity<String> httpGet(@NotNull final String url) {
		return execute(new HttpGet(url), null);
	}

	private EcResponseEntity<String> execute(HttpGet httpGet, HttpContext localContext) {

		HttpResponse resp = null;
		try {
			if (localContext == null) {
				resp = httpClient.execute(httpGet);
			} else {
				resp = httpClient.execute(httpGet, localContext);
			}
			int statusCode = resp.getStatusLine().getStatusCode();

			String msg = Utils.getStringFromStream(resp.getEntity().getContent());
			return new EcResponseEntity<String>(msg, statusCode);

		} catch (Exception e) {
			return new EcResponseEntity<String>(e.getMessage(), 500);
		}
	}
}