package com.example.mvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE=
            "package com.example.mvvm.EXTRA_TITLE";
    public static final String EXTRA_ID=
            "package com.example.mvvm.EXTRA_ID";
    public static final String EXTRA_DES=
            "package com.example.mvvm.EXTRA_DES";
    public static final String EXTRA_PRIORITY=
            "package com.example.mvvm.EXTRA_PRIORITY";
    private EditText editTitle;
    private EditText editdes;
    private NumberPicker numberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editdes=findViewById(R.id.edit_des);
        editTitle=findViewById(R.id.edit_title);
        numberPicker=findViewById(R.id.number_picker_priority);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

       //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        Intent intent=getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            setTitle("edit note");
            editTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editdes.setText(intent.getStringExtra(EXTRA_DES));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }else {
            setTitle("add note");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }


    }

    private void saveNote() {
        String title=editTitle.getText().toString();
        String des=editdes.getText().toString();
        int priority=numberPicker.getValue();
        
        if (title.trim().isEmpty() || des.trim().isEmpty()){
            Toast.makeText(this, "plz insert titke and des", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data=new Intent();
        int id =getIntent().getIntExtra(EXTRA_ID,-1);
        if (id!=-1){
            data.putExtra(EXTRA_ID,id);
            data.putExtra(EXTRA_TITLE,title);
            data.putExtra(EXTRA_DES,des);
            data.putExtra(EXTRA_PRIORITY,priority);
        }else {
            data.putExtra(EXTRA_TITLE,title);
            data.putExtra(EXTRA_DES,des);
            data.putExtra(EXTRA_PRIORITY,priority);
        }

        setResult(RESULT_OK,data);
        finish();
    }
}

