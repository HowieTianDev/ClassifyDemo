package com.howietian.rxjavademo;

import android.Manifest;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "main_activity";

    private Button btnSend;
    private EditText etUrl;
    private TextView tvTag;
    private TextView tvClassify;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate" + Thread.currentThread().getName());
        //rxTest();
       btnSend = findViewById(R.id.btn_send);
       etUrl = findViewById(R.id.et_url);
       tvTag = findViewById(R.id.tv_tag_result);
       tvClassify = findViewById(R.id.tv_classify_result);

       btnSend.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String url = etUrl.getText().toString();
               rxClassify(url);
           }
       });
    }


    private void rxClassify(String url){
       Retrofit retrofit = Utils.create();
       Api api = retrofit.create(Api.class);
       api.getClassifyResult(url)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<Result>() {
                   @Override
                   public void onSubscribe(Disposable d) {
                       dialog = new ProgressDialog(MainActivity.this);
                       dialog.setMessage("正在请求...");
                       dialog.show();
                       Log.e("Main","onSubscribe");
                   }

                   @Override
                   public void onNext(Result s) {
                        Log.e("Main",s.getItem().getLv1_tag_list().toString());
                        String classifyResult = s.getItem().getLv1_tag_list().get(0).getTag();
                        if(!TextUtils.isEmpty(classifyResult)){
                            tvClassify.setText(classifyResult);
                        }
                        StringBuilder myTags = new StringBuilder();
                       ArrayList<Result.Tag> tags = s.getItems();
                        if(tags!=null){
                            for(int i = 0;i<tags.size();i++){
                                myTags.append(tags.get(i).getTag()).append(" ");
                            }
                        }
                        if(myTags!=null){
                            tvTag.setText(myTags.toString());
                        }

                   }
                   @Override
                   public void onError(Throwable e) {
                       dialog.dismiss();
                       Toast.makeText(MainActivity.this,"time out",Toast.LENGTH_SHORT).show();
                       Log.e("Main","onError"+e.getMessage());
                   }

                   @Override
                   public void onComplete() {
                       dialog.dismiss();
                       Log.e("Main","onComplete");
                   }
               });
    }


    private void rxTest() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.e(TAG, "observable" + Thread.currentThread().getName());
                e.onNext(1);
                e.onComplete();

            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "doOnNext" + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, integer + Thread.currentThread().getName());
                    }
                });


    }

//    private void rxBmobTest() {
//        String s = new MWhere("mActivity", "MActivity", "649f7697e1").toString();
//        Retrofit retrofit = Utils.createBmobRetrofit();
//        Api api = retrofit.create(Api.class);
//        Observable<String> observable = api.getComment();
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.e(TAG,"onSubscribe");
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        Log.e(TAG,"onNext");
//                        Log.e(TAG, s);
//                        tv_test.setText(s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG,"onError"+e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.e(TAG,"onComplete");
//                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }


//    private void rxretrofitTest() {
//
//        String id = et_test.getText().toString();
//        Retrofit retrofit = Utils.create();
//        Api api = retrofit.create(Api.class);
//        Observable<Integer> observable;
//        if (TextUtils.isEmpty(et_test.getText())) {
//            observable = api.getInteger();
//        } else {
//            observable = api.getInteger(Integer.valueOf(id));
//        }
//
//
//        // api.getInteger(Integer.valueOf(id))
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//
//                        Log.e(TAG, integer.toString());
//                        tv_test.setText(integer.toString());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, "error");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.e(TAG, "success");
//                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }
}
