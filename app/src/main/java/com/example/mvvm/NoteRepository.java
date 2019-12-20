package com.example.mvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;

//整合串接
public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database=NoteDatabase.getInstance(application);
        noteDao=database.noteDao();
        allNotes=noteDao.getAllNotes();
    }

    public void  insert(Note note){
        new InsertNoteAsynask(noteDao).execute(note);
    }
    public void  update(Note note){
        new UpdateNoteAsynask(noteDao).execute(note);
    }
    public void  delete(Note note){
        new DeleteNoteAsynask(noteDao).execute(note);
    }
    public void  deleteAllNotes(){
        new DeleteAllNotesAsynask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    //資料庫操作不能在main thread
    private static  class  InsertNoteAsynask extends AsyncTask<Note,Void,Void>{
       private  NoteDao noteDao;
       private  InsertNoteAsynask (NoteDao noteDao){
           this.noteDao=noteDao;
       }
        @Override
        protected Void doInBackground(Note... notes) {
           noteDao.insert(notes[0]);
            return null;
        }
    }
    private static  class  UpdateNoteAsynask extends AsyncTask<Note,Void,Void>{
        private  NoteDao noteDao;
        private UpdateNoteAsynask (NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static  class  DeleteNoteAsynask extends AsyncTask<Note,Void,Void>{
        private  NoteDao noteDao;
        private DeleteNoteAsynask (NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    private static  class DeleteAllNotesAsynask extends AsyncTask<Void,Void,Void>{
        private  NoteDao noteDao;
        private DeleteAllNotesAsynask (NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

}
