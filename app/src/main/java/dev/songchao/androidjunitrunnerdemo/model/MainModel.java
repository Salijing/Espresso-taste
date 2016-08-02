package dev.songchao.androidjunitrunnerdemo.model;

import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import dev.songchao.androidjunitrunnerdemo.presenter.MainPresenter;

/**
 * Created by songchao on 2016/6/30.
 */
public class MainModel {

    private FindContentTask mFindContentTask;

    public void findContentInfo(MainPresenter.CallBack callBack) {
        if (null != mFindContentTask && mFindContentTask.getStatus() == AsyncTask.Status.RUNNING) {
            mFindContentTask.cancel(true);
        }
        mFindContentTask = new FindContentTask(callBack);
        mFindContentTask.execute();
    }

    private class FindContentTask extends AsyncTask {

        private MainPresenter.CallBack mCallback;

        public FindContentTask(MainPresenter.CallBack callback) {
            mCallback = callback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mCallback.pre(null);
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://www.baidu.com").openConnection();
                httpURLConnection.setConnectTimeout(10 * 1000);
                httpURLConnection.setReadTimeout(10 * 1000);
                httpURLConnection.connect();
                if (HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String value = bufferedReader.readLine();
                    return value;
                }
            } catch (IOException e) {
                e.printStackTrace();
                mCallback.fail(e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object value) {
            super.onPostExecute(value);
            if (TextUtils.isEmpty((CharSequence) value)) {
                mCallback.fail("value is empty");
            } else {
                mCallback.succ((String) value);
            }
            mCallback.pos(null);
        }
    }
}
