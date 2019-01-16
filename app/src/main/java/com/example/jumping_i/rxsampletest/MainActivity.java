package com.example.jumping_i.rxsampletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = findViewById(R.id.name_tv);

        Observable<String> simpleObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Hello RxAndroid!!");
                e.onComplete();
            }
        });

        Observer<String> simpleObserver  = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Toast.makeText(MainActivity.this, "onSubscribe", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(String value) {
                Toast.makeText(MainActivity.this, "onNext called: " + value, Toast.LENGTH_SHORT).show();
                tv.setText(value);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "onError called", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this, "onComplete called", Toast.LENGTH_SHORT).show();
            }
        };

        simpleObservable.subscribe(simpleObserver);
    }
}
