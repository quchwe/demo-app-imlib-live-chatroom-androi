package cn.rongcloud.live.data.remote;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.rongcloud.live.data.beans.BaseBean;
import cn.rongcloud.live.data.beans.SysUser;
import cn.rongcloud.live.data.local.LocalSource;
import cn.rongcloud.live.util.NormalUtil.NormalUtil;
import cn.rongcloud.live.util.base.BaseResponse;
import cn.rongcloud.live.util.net.ApiMoudle;
import cn.rongcloud.live.util.net.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


/**
 * Created by quchwe on 2017/4/5 0005.
 */

public class RemoteSource {
    private static Gson gson;
    private final Context mContext;
    private final LocalSource mLocalSource;
    private static RemoteSource mRemoteSource;

    private RemoteSource(Context context) {
        this.mContext = context;
        mLocalSource = LocalSource.instance(context);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });

        gson = builder.create();


    }

    public static synchronized RemoteSource instance(Context context) {
        if (mRemoteSource == null) {
            mRemoteSource = new RemoteSource(context);
        }
        return mRemoteSource;
    }

    public Observable<BaseResponse<SysUser>> login(SysUser user) {

        return ApiMoudle.login().login(user)
                .subscribeOn(SchedulerProvider.io())
                .observeOn(SchedulerProvider.ui())
                .flatMap(new Function<ResponseBody, ObservableSource<BaseResponse<SysUser>>>() {
                    @Override
                    public ObservableSource<BaseResponse<SysUser>> apply(ResponseBody responseBody) throws Exception {

                        String s = responseBody.string();

                        BaseResponse<SysUser> baseBean =
                                gson.fromJson(s, new TypeToken<BaseResponse<SysUser>>() {
                                }.getType());
                        return Observable.just(baseBean);
                    }
                });
    }
    public Observable<BaseResponse<SysUser>> register(SysUser user) {

        return ApiMoudle.login().login(user)
                .subscribeOn(SchedulerProvider.io())
                .observeOn(SchedulerProvider.ui())
                .flatMap(new Function<ResponseBody, ObservableSource<BaseResponse<SysUser>>>() {
                    @Override
                    public ObservableSource<BaseResponse<SysUser>> apply(ResponseBody responseBody) throws Exception {

                        String s = responseBody.string();

                        BaseResponse<SysUser> baseBean =
                                gson.fromJson(s, new TypeToken<BaseResponse<SysUser>>() {
                                }.getType());
                        return Observable.just(baseBean);
                    }
                });
    }


}
