package dev.songchao.androidjunitrunnerdemo.component.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dev.songchao.androidjunitrunnerdemo.R;
import dev.songchao.androidjunitrunnerdemo.model.bean.BListBean;

/**
 * Created by songchao on 2016/7/4.
 */
public class BAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BListBean> bBeans;

    public BAdapter(ArrayList<BListBean> beans, Context context) {
        this.bBeans = beans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return bBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_item, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_1);
            viewHolder.age = (TextView) convertView.findViewById(R.id.tv_2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BListBean bListBean = bBeans.get(position);
        viewHolder.age.setText(bListBean.getAge());
        viewHolder.name.setText(bListBean.getName());
        return convertView;
    }

    private class ViewHolder {
        private TextView name;
        private TextView age;
    }
}
