package com.example.icogn.mshb.http;

import android.util.Log;

import com.example.icogn.mshb.BuildConfig;
import com.example.icogn.mshb.base.HttpResult;
import com.example.icogn.mshb.entity.User;
import com.example.icogn.mshb.exception.ApiException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 项目名称: MSHB
 * 类描述:
 * 创建人:   ICOGN
 * 创建时间: 2016/9/9 18:04
 * 修改人:   ICOGN
 * 修改时间: ${DATE}
 * 备注:
 * 版本:
 */
public enum Http {
    HTTP;
    //    private final String BASE_URL = "http://192.168.1.88:8084/icogn/";
//        private final String BASE_URL = "http://icogns.oicp.net:11465/icogn/";
    private final String BASE_URL = "http://101.200.126.206/";
    private Retrofit     retrofit;
    private OkHttpClient client;
    private UserApi      userApi;
    private ClassifyApi  classifyApi;
    private NewsApi      newsApi;
    private CommodityApi commodityApi;
    private AddressApi   addressApi;
    private static final String TAG = "Http";
    private final HttpLoggingInterceptor.Level level;

    /**
     * @return 商品相关api
     */
    public CommodityApi getCommodityApi() {
        if (commodityApi == null) commodityApi = retrofit.create(CommodityApi.class);
        return commodityApi;
    }

    /**
     * @return 地址api
     */
    public AddressApi getAddressApi() {
        if (addressApi == null) addressApi = retrofit.create(AddressApi.class);
        return addressApi;
    }

    /**
     * @return 分类相关Api
     */
    public ClassifyApi getClassifyApi() {
        if (classifyApi == null) classifyApi = retrofit.create(ClassifyApi.class);
        return classifyApi;
    }


    /**
     * @return 新闻相关Api
     */
    public NewsApi getNewsApi() {
        if (newsApi == null) newsApi = retrofit.create(NewsApi.class);
        return newsApi;
    }

    /**
     * @return 获取retrofit用户相关api动态代理对象
     */
    public UserApi getUserApi() {
        if (userApi == null) userApi = retrofit.create(UserApi.class);
        return userApi;
    }

    //单例
    Http() {
        if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY;
        else level = HttpLoggingInterceptor.Level.NONE;
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor()
                            .setLevel(level))
                    .retryOnConnectionFailure(true)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .addNetworkInterceptor(tokenInterceptor)
                    .authenticator(authenticator)
                    .build();
        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    /**
     * 日志拦截
     */
//    HttpLoggingInterceptor interceptor      = new HttpLoggingInterceptor();
    /**
     * token拦截
     */
    Interceptor   tokenInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (User.token == null || alreadyHasAuthorizationHeader(request))
                return chain.proceed(request);
            Request authorised = request.newBuilder()
                    .header("Authorization", User.token)
                    .build();
            return chain.proceed(authorised);
        }
    };
    /**
     * 刷新token
     */
    Authenticator authenticator    = new Authenticator() {
        /**
         * Returns a request that includes a credential to satisfy an authentication challenge in {@code
         * response}. Returns null if the challenge cannot be satisfied.
         *
         * @param route
         * @param response
         */
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
            User.token = userApi.refreshToken(User.token).execute().body();
            return response.request().newBuilder()
                    .addHeader("Authorization", User.token)
                    .build();
        }
    };

    /**
     * @param request
     * @return Authorization 是否为空
     */
    boolean alreadyHasAuthorizationHeader(Request request) {
        String header = request.header("Authorization");
        return header != null && !header.isEmpty();
    }

    /**
     * 封装网络方法调用
     *
     * @param observable 观察者
     * @param subscriber 订阅者
     * @param <T>        data类型
     */
    public <T> void http(Observable<HttpResult<T>> observable, Subscriber<T> subscriber) {
        observable.map(new Func<T>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }

    /**
     * 参数转换
     *
     * @auther ICOGN
     * created at 2016/9/30 9:03
     */
    private class Func<T> implements Func1<HttpResult<T>, T> {
        @Override
        public T call(HttpResult<T> result) {
            Log.d(TAG, "call: 000000000000000000000000000000" + result.getState() + result.getResult());
            Log.d(TAG, "call: 000000000000000000000000000000" + result.getData());
            if (result.getResult() != 200) {
                throw new ApiException(result.getState());
            }
            Log.d(TAG, "call: 0000000000000000000000000");
            return result.getData();

        }
    }

}
