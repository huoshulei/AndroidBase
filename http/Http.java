package com.example.icogn.mshb.http;


import com.example.icogn.mshb.BuildConfig;
import com.example.icogn.mshb.base.HttpResult;
import com.example.icogn.mshb.entity.User;
import com.example.icogn.mshb.exception.ApiException;
import com.example.icogn.mshb.utils.interceptor.HttpLoggingInterceptor;
import com.example.icogn.mshb.utils.interceptor.HttpLoggingInterceptor.Level;
import com.example.icogn.mshb.utils.logger.Logger;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
   
    private final String BASE_URL = "";
    public final Api api;

    Http() {
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor()
                                .setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE))
                        .retryOnConnectionFailure(true)
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .addNetworkInterceptor(tokenInterceptor)
                        .authenticator(authenticator)
                        .build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Api.class);
    }

    /**
     * token拦截
     */
    Interceptor   tokenInterceptor = chain -> {
        Request request = chain.request();
        if (User.token == null || alreadyHasAuthorizationHeader(request))
            return chain.proceed(request);
        Request authorised = request.newBuilder()
                .header("Authorization", User.token)
                .build();
        return chain.proceed(authorised);
    };
    /**
     * 刷新token
     */
    Authenticator authenticator    = (route, response) -> {
        User.token = refreshToken(User.token);
        return response.request().newBuilder()
                .addHeader("Authorization", User.token)
                .build();
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
     * @param onNext     订阅者
     * @param <T>        data类型
     */
    public <T> void http(Flowable<HttpResult<T>> observable, Consumer<T> onNext,
                         Consumer<Throwable> onError, Action onComplete) {
        observable.map(this::apply)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(onNext, onError, onComplete, s -> s.request(Long.MAX_VALUE));
    }

    /**
     * @param token
     * @return 刷新Token
     * @throws IOException
     */
    String refreshToken(String token) throws IOException {
        return api.refreshToken(token).execute().body();
    }

    private <T> T apply(HttpResult<T> result) {
        if (result.getResultCode() != 200) throw new ApiException(result.getState());
        if (result.getData() == null) return (T) new Object();
        return result.getData();
    }
}
