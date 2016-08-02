package dev.songchao.androidjunitrunnerdemo.model.bean;

/**
 * Created by songchao on 2016/7/4.
 */
public class BListBean {
    private String name;
    private String age;

    public BListBean(String age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
