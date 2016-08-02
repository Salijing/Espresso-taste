package dev.songchao.androidjunitrunnerdemo.model.bean;

/**
 * Created by songchao on 2016/7/4.
 */
public class AListBean {
    private String addr;
    private String phone;

    public AListBean(String addr, String phone) {
        this.addr = addr;
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
