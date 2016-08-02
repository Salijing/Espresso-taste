package dev.songchao.androidjunitrunnerdemo.component.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dev.songchao.androidjunitrunnerdemo.R;
import dev.songchao.androidjunitrunnerdemo.model.bean.AListBean;

/**
 * Created by songchao on 2016/7/4.
 */
public class AAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<AListBean> aBeans;

    public AAdapter(ArrayList<AListBean> beans, Context context) {
        this.aBeans = beans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return aBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return aBeans.get(position);
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
            viewHolder.addr = (TextView) convertView.findViewById(R.id.tv_1);
            viewHolder.phone = (TextView) convertView.findViewById(R.id.tv_2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        AListBean aListBean = aBeans.get(position);
        viewHolder.addr.setText(aListBean.getAddr());
        viewHolder.phone.setText(aListBean.getPhone());
        return convertView;
    }

    private class ViewHolder {
        private TextView addr;
        private TextView phone;
    }
}
