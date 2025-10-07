package cn.qcyrj.dailyrecordpro.store;

import java.util.ArrayList;
import java.util.List;

import cn.qcyrj.dailyrecordpro.data.Diary;
import lombok.Getter;
public class DiaryList {
    private static DiaryList instance;
    private DiaryList(){}
    public static DiaryList getInstance(){
        if(instance == null){
            instance = new DiaryList();
        }
        return instance;
    }
    @Getter
    private List<Diary> diaryList = new ArrayList<>();
}


