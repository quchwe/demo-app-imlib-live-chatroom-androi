package cn.rongcloud.live.util.net;


import cn.rongcloud.live.data.beans.SysUser;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by quchwe on 2016/11/19 0019.
 */

public interface Api {

    interface Register{
        @POST("user/register")
        Observable<ResponseBody> register(@Body SysUser sysUser);
    }
    interface Login{
        @POST("user/login")
        Observable<ResponseBody> login(@Body SysUser sysUser);
    }
}
