package cn.qcyrj.dailyrecordpro.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    private String username;
    private String password;
    private boolean isAutoLogin;
    private boolean isRemember;
}
