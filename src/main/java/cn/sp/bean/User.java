package cn.sp.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Author: 2YSP
 * @Description: GitHub User的数据对象
 * @Date: Created in 2018/1/24
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String name;

    private String blog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "User[name="+name+",blog="+blog+"]";
    }
}
