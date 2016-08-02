package dev.songchao.androidjunitrunnerdemo.component.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import dev.songchao.androidjunitrunnerdemo.R;
import dev.songchao.androidjunitrunnerdemo.component.adapter.AAdapter;
import dev.songchao.androidjunitrunnerdemo.component.adapter.BAdapter;
import dev.songchao.androidjunitrunnerdemo.component.view.IMainView;
import dev.songchao.androidjunitrunnerdemo.model.bean.AListBean;
import dev.songchao.androidjunitrunnerdemo.model.bean.BListBean;
import dev.songchao.androidjunitrunnerdemo.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements IMainView {
    private TextView mContentTextView;
    private EditText mContentEditText;

    private MainPresenter mMainPresenter;

    private ListView mListViewA;
    private ListView mListViewB;

    private boolean isClickDelayFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainPresenter = new MainPresenter(this);
        mContentTextView = (TextView) findViewById(R.id.content_textview);
        mContentEditText = (EditText) findViewById(R.id.content_edittext);
        ((Button) findViewById(R.id.test_click)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Sysout", "test_click button click");
                mMainPresenter.findContentInfo();
            }
        });
        mListViewA = (ListView) findViewById(R.id.list_1);
        mListViewB = (ListView) findViewById(R.id.list_2);
        ArrayList<AListBean> aBeans = new ArrayList<>();
        aBeans.add(new AListBean("南京", "1890909090909"));
        aBeans.add(new AListBean("滁州", "186868688686"));
        aBeans.add(new AListBean("合肥", "1826262626262"));
        mListViewA.setAdapter(new AAdapter(aBeans, this));
        ArrayList<BListBean> bBeans = new ArrayList<>();
        bBeans.add(new BListBean("22", "Jelly"));
        bBeans.add(new BListBean("26", "Erial"));
        bBeans.add(new BListBean("28", "Phill"));
        bBeans.add(new BListBean("29", "Jimmy"));
        mListViewB.setAdapter(new BAdapter(bBeans, this));
    }

    @Override
    public void setContent(String content) {
        mContentTextView.setText(content);
    }

    public boolean isClickDelayFinished() {
        return isClickDelayFinished;
    }

    @Override
    public void inputText(String content) {
        mContentEditText.setText(content);
    }
}
