package com.example.todolist;

import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import org.w3c.dom.ls.LSException;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

//    public interface OnDeleteClickListener{
//        void OnDeleteClickListener(Todo todo);
//    }

    List<String> listData;
    String data;
    Context mcontext;
    int i;
    List<Todo> mtodos;
    private boolean recy_refresh_frag = false;
    AppDatabase db;


//    private OnDeleteClickListener onDeleteClickListener;

    public Adapter(Context mcontext, String data, int i) {
        Log.d("1","확인 : "+ data);
        this.data = data;
        this.mcontext = mcontext;
        this.i = i;
    }
    public Adapter(Context mcontext, List<String> data,List<Todo> todos,AppDatabase mdb) {
        Log.d("1","adapter 생성자확인 ");
        this.listData = data;
        this.mcontext = mcontext;
        this.mtodos = todos;
        this.db = mdb;
//        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.d("1","확인1");
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false));
    }

    private static class DeleteAsyncTask extends AsyncTask<Todo,Void,Void>{
        private TodoDao mTodoDao;

        public DeleteAsyncTask(TodoDao todoDao) {
            this.mTodoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mTodoDao.delete(todos[0]);
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter.ViewHolder holder, final int position) {
//        Log.d("1","확인2");

        String Title = listData.get(position);
        holder.itemName.setText(Title);
//        holder.itemName.setText(listData);

        holder.itemName.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.itemName.isChecked()){
                    Log.d("1","체크확인");
                }
                else{
                    Log.d("1","체크해제");
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteAsyncTask(db.todoDao()).execute(mtodos.get(position));
//                ((MainActivity)mcontext).recyclerView.setAdapter(new Adapter(mcontext,listData,mtodos));
                recy_refresh_frag=true;
                Toast.makeText(mcontext,"삭제클릭",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox itemName;
        ImageView edit;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // LiveData

            db.todoDao().getAll().observe((LifecycleOwner) mcontext, new Observer<List<Todo>>() {
                @Override
                public void onChanged(List<Todo> todos) {
                    Log.d("1","Adapter 확인2222");
//                    if(recy_refresh_frag==true){
//                        recy_refresh_frag = false;
////                        listData.clear();
//                        Log.d("1","Adapter 확인1111");
////                        ((MainActivity)mcontext).recyclerView.setAdapter(Adapter.this);
//                    }
                }
            });

//            db.todoDao().getAll().observe((LifecycleOwner) mcontext, new Observer<List<Todo>>() {
//                @Override
//                public void onChanged(List<Todo> todos) {
//                    if(MainActivity.addFrag == true){
//                        MainActivity.addFrag = false;
////                        listData.clear();
////                        Toast.makeText(mcontext,todos.get(todos.size()-1).toString(),Toast.LENGTH_LONG).show();
////                        listData.add(todos.get(todos.size()-1).toString());
////                        ((MainActivity)mcontext).recyclerView.setAdapter(Adapter.this);
//                    }
////                    listData.add(todos.get(i).toString());
//                }
//            });

            itemName = itemView.findViewById(R.id.cb_item);
            edit = itemView.findViewById(R.id.iv_edit);
            delete = itemView.findViewById(R.id.iv_delete);
        }
    }
}
