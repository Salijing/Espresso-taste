package dev.songchao.androidjunitrunnerdemo.presenter;

import android.util.Log;

import dev.songchao.androidjunitrunnerdemo.component.view.IMainView;
import dev.songchao.androidjunitrunnerdemo.model.MainModel;

/**
 * Created by songchao on 2016/6/30.
 */
public class MainPresenter {
    private IMainView mMainView;
    private MainModel mMainModel;

    public MainPresenter(IMainView mainView) {
        mMainView = mainView;
        mMainModel = new MainModel();
    }

    public void findContentInfo() {
        mMainModel.findContentInfo(new CallBack() {
            @Override
            public void pre(String preValue) {
                Log.d("Sysout", "now is pre");
            }

            @Override
            public void succ(String succValue) {
                Log.d("Sysout", "successed value is " + succValue);
                mMainView.setContent(succValue);
            }

            @Override
            public void fail(String failValue) {
                Log.d("Sysout", "failed value is " + failValue);
            }

            @Override
            public void pos(String posValue) {
                Log.d("Sysout", "now is pos");
            }
        });
    }

    public interface CallBack {
        void pre(String value);

        void succ(String value);

        void fail(String value);

        void pos(String value);
    }
}
