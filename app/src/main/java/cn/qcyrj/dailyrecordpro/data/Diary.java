package cn.qcyrj.dailyrecordpro.data;


import cn.qcyrj.dailyrecordpro.tools.Tools;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diary {
    private String title;
    private String content;
    private String updateTime;
    private String createTime;
    public Diary(String title, String content, String time) {
        this.title = title;
        this.content = content;
        this.updateTime = time;
        this.createTime = Tools.getCurrentTime();
    }
}
