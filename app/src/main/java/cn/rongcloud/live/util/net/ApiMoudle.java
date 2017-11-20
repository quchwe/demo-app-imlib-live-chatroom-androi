package cn.rongcloud.live.util.net;

import retrofit2.Retrofit;

/**
 * Created by quchwe on 2016/11/20 0020.
 */

public class ApiMoudle {

    private static final Retrofit retrofit = RetrofitUtil.retrofit();


    public static Api.Register register(){
        return retrofit.create(Api.Register.class);
    }

   public static Api.Login login(){
        return retrofit.create(Api.Login.class);
   }

}
