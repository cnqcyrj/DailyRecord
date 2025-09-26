package cn.qcyrj.dailyrecordpro.data;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private boolean isLogin;
    private boolean isAutoPreserveDiary;
    private boolean isAutoUpdateData;
    private boolean isAutoSortDiary;
    private int theme;
}
