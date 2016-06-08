package com.example.hpb.kunlun.data;

import android.util.Log;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hpb on 16/4/30.
 */
public class Repository {
    private static Repository mInstance;
    //    private static final String API_DEV_URL="https://api.github.com";
//    private static final String DOUBAN="https://api.douban.com/v2/movie/";
    private static final String VMOVIER_URL = "http://app.vmoiver.com";

    public static Repository getInstance() {
        if (mInstance == null) {
            synchronized (Repository.class) {
                if (mInstance == null) {
                    mInstance = new Repository();
                }
            }
        }
        return mInstance;
    }

    private Repository() {
        this(true);
    }

    private Repository(boolean useRxJava) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(VMOVIER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient());
        if (useRxJava) {
            builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        }
        mRetrofit = builder.build();
        vmovierApi = mRetrofit.create(VmovierApi.class);
    }

    Retrofit mRetrofit;
    VmovierApi vmovierApi;

    public VmovierApi getVmovierApi() {
        return vmovierApi;
    }


    private OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        boolean IS_DEV = true;
        if (IS_DEV) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }

        // 如果使用到HTTPS，我们需要创建SSLSocketFactory，并设置到client
        SSLSocketFactory sslSocketFactory = null;

        try {
            // 这里直接创建一个不做证书串验证的TrustManager
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }

        return new OkHttpClient.Builder()
                .addInterceptor(new HeaderIntercptor())
                .addInterceptor(logging)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
//                .sslSocketFactory(sslSocketFactory)
                // 信任所有主机名
//                .hostnameVerifier((hostname, session) -> true)
//                .cookieJar(new CookieJar() {
//                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
//
//                    @Override
//                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                        cookieStore.put(HttpUrl.parse(url.host()), cookies);
//                    }
//
//                    @Override
//                    public List<Cookie> loadForRequest(HttpUrl url) {
//                        List<Cookie> cookies = cookieStore.get(HttpUrl.parse(url.host()));
//                        return cookies != null ? cookies : new ArrayList<Cookie>();
//                    }
//                })
                .build();
    }




}
