package com.smorzhok.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance = null;
    private static final String DB_NAME = "note.db";

    public static NoteDatabase getInstance(Application application){
        if (instance == null)
        {
             instance = Room.databaseBuilder(
                     application,
                     NoteDatabase.class,
                     DB_NAME
             ).build();
        }
        return instance;
    }

    public abstract NotesDao notesDao();

}
