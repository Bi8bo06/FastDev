package com.liangliang.android.fastdev.ui.rv.refresh;

import com.liangliang.android.component.rx.utils.RxObservableOnSubscribe;
import com.liangliang.android.core.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;

/**
 * 数据模块
 */
public class DataModule {
    public Observable<List<String>> requestData(int page) {
        return Observable.create(new RxObservableOnSubscribe<List<String>>(page) {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                int page = (int) getArgs()[0];
                if (emitter.isDisposed()) {
                    return;
                }
                try {
                    Thread.sleep(1000);
                    if (emitter.isDisposed()) {
                        return;
                    }
                    emitter.onNext(getList(page));
                    emitter.onComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.onError(e);
                }
            }
        });
    }

    private List<String> getList(int page) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String str = DateUtils.getCurrentFormatString(DateUtils.TYPE_10) + " - " + i + "  page : " + page;
            list.add(str);
        }
        return list;
    }
}
