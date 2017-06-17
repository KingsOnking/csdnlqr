package com.myrescue.presenter;

import com.google.gson.Gson;
import com.myrescue.model.Bean.ResponseInfo;
import com.myrescue.model.net.ResponseInfoApi;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by HASEE on 2017/2/17.
 *
*/
public abstract class BasePresenter {

    protected ResponseInfoApi responseInfoApi;
    private HashMap<String, String> errorMap;
    protected ResponseInfoApi responseInfoApi1;

    //    http://api.myjiuyuan.com/
    public BasePresenter() {
        errorMap = new HashMap<>();
        errorMap.put("4040","此页数据没有更新");
        errorMap.put("4000","服务器忙");
        errorMap.put("403","请求参数异常");
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.ruhefu.cn/rhfapi/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        responseInfoApi1 = retrofit.create(ResponseInfoApi.class);

        //指定Retrofit+esponseInfoApi.class);

    }



    //如何处理结果(2个方法回调方法)
    //同步?httpUrlConnection
    //异步?回调方法(成功,失败)
    class CallBackAdapter implements Callback<ResponseInfo> {
        @Override
        public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
            //获取服务器返回的结果
            ResponseInfo body = response.body();
            if (body.getCode().equals("2000")){
                //请求成功,data中的数据可用
                String json = body.getData();
                //json解析
                parseJson(json);
            }else{
                //本次请求有异常,具体的异常类型获取出来
                String errorMessage = errorMap.get(body.getCode());
                //自定义一个运行时异常,让onFailure方法接收
                onFailure(call,new RuntimeException(errorMessage));
            }
        }
        @Override
        public void onFailure(Call<ResponseInfo> call, Throwable t) {
            if (t instanceof RuntimeException){
                //onFailure方法自己调用
                String message = t.getMessage();
                //自定义一个如何显示异常方法
                showErrorMessage(message);
            }
            //retrofit框架调用
            showErrorMessage("服务器忙,请稍后重试");
        }
    }

    //因为json串对于每一个页面的请求而言,结果都是有差异的,所以无法做具体的解析,抽象
    protected abstract void parseJson(String json);
    protected abstract void showErrorMessage(String message);
}
