package cn.rongcloud.live.data;

import android.content.Context;



import java.io.File;
import java.util.List;

import cn.rongcloud.live.data.beans.BaseBean;
import cn.rongcloud.live.data.beans.SysUser;
import cn.rongcloud.live.data.local.LocalSource;
import cn.rongcloud.live.data.remote.RemoteSource;
import cn.rongcloud.live.util.base.BaseResponse;
import io.reactivex.Observable;


/**
 * Created by quchwe on 2017/4/5 0005.
 */

public class DataRepository {
    private final Context mContext;
    private  DataRepository mDataRepository;
    private final LocalSource mLocalSource;
    private final RemoteSource mRemoteSource;

    public DataRepository(Context context) {
        this.mContext = context;
        this.mLocalSource = LocalSource.instance(context);
        this.mRemoteSource = RemoteSource.instance(context);
    }

    public  DataRepository instance(Context context) {
        if (mDataRepository == null) {
            mDataRepository = new DataRepository(context);
        }
        return mDataRepository;
    }


    public Observable<BaseResponse<SysUser>> login(SysUser user) {
        return mRemoteSource.login(user);
    }
    public Observable<BaseResponse<SysUser>> register(SysUser user) {
        return mRemoteSource.register(user);
    }

}
