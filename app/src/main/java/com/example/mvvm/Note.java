package com.example.mvvm;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//@Entity 註解實體類 Database通過entity創建類名稱 or 表
@Entity(tableName = "note_table")
public class Note {
    //主KEY
    @PrimaryKey(autoGenerate = true)
    private  int id;
    private String title;
    private  String des;
    private  int priority;

    public Note( String title, String des, int priority) {
        this.title = title;
        this.des = des;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDes() {
        return des;
    }

    public int getPriority() {
        return priority;
    }
}
