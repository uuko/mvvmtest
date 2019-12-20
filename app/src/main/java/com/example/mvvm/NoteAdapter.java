package com.example.mvvm;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
   private List<Note> notes=new ArrayList<>();
   private OnItemClickListener listener;
    @NonNull
    //55555
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("s", "onCreateViewHolder: ");
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new NoteHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote=notes.get(position);
        holder.tvtitle.setText(currentNote.getTitle());
        holder.tvdes.setText(currentNote.getDes());
        holder.tvpriority.setText(Integer.toString(currentNote.getPriority()));

    }

    public interface  OnItemClickListener{
        void onItemClick(Note note);
    }
    public void  setOnItemClickListener(OnItemClickListener listener){
            this.listener=listener;
    }
    public Note getNoteAct(int position){
        return notes.get(position);
    }
    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void  setNotes(List<Note> notes){
        this.notes=notes;
        notifyDataSetChanged();
    }

    class NoteHolder extends  RecyclerView.ViewHolder{
        private TextView tvtitle;
        private TextView tvdes;
        private TextView tvpriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle=itemView.findViewById(R.id.tv_title);
            tvdes=itemView.findViewById(R.id.tv_des);
            tvpriority=itemView.findViewById(R.id.tv_priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position =getAdapterPosition();
                    if (listener !=null && position !=RecyclerView.NO_POSITION){
                        listener.onItemClick(notes.get(position));
                    }

                }
            });
        }
    }
}
