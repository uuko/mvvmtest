package com.example.mvvm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
//註解一個接口 / 抽象方法
// 在使用@Database注解的类中必须定一个不带参数的方法，这个方法返回使用@Dao注解的类
@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void  update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();

}
