
package ebookfrenzy.com.mapdemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("cnt")
    @Expose
    private Double cnt;
    @SerializedName("list")
    @Expose
    private java.util.List< ebookfrenzy.com.mapdemo.List> list = null;

    public Double getCnt() {
        return cnt;
    }

    public void setCnt(Double cnt) {
        this.cnt = cnt;
    }

    public java.util.List< ebookfrenzy.com.mapdemo.List> getList() {
        return list;
    }

    public void setList(java.util.List< ebookfrenzy.com.mapdemo.List> list) {
        this.list = list;
    }

}
