package com.example.mvvm;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

// 通過entity類創建數據庫  加上 創建DAOS (數據訪問對象)
@Database(entities = {Note.class},version =1 )
public abstract  class NoteDatabase extends RoomDatabase {
        private static  NoteDatabase instance;
        public  abstract NoteDao noteDao();
        public static  synchronized  NoteDatabase getInstance(Context context){
            if (instance == null){
                //獲取實例
                instance= Room.databaseBuilder(context.getApplicationContext(),
                        NoteDatabase.class,"note_database")
                        .fallbackToDestructiveMigration() //如果migrate的話會直接清空重建
                        .addCallback(roomCallback)
                        .build();
            }
            return instance;
        }
        /*
         如果要migration的話 1.修改entity 2.修改版本號
         3.建立透過constructor Migration(int startVersion, int endVersion)建立版本1升級到2的內容
         4.add到instance  ex: .addMigrations(MIGRATION_1_2)
        */

        //Callback
        private static Callback roomCallback=new Callback(){
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                new PopulateDbAsyncTask(instance).execute();
            }
        };


        // 資料庫操作不可以在main thread 所以切thread
        private static class  PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
            private NoteDao noteDao;
            private  PopulateDbAsyncTask(NoteDatabase db){
                noteDao=db.noteDao();
            }
            @Override
            protected Void doInBackground(Void... voids) {
                noteDao.insert(new Note("Title 1","Des 1",1));
                noteDao.insert(new Note("Title 2","Des 2",2));
                noteDao.insert(new Note("Title 3","Des 3",3));
                return null;
            }
        }
}
