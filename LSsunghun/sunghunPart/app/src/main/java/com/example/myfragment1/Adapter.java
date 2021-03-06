package com.example.myfragment1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Delete;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    static List<String> title;
    Context mcontext;

    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;
    private boolean recy_refresh_frag = false;


    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else
            return TYPE_ITEM;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    public Adapter(Context context, List<String> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.title = data;
        mcontext = context;
    }

    class HeaderViewHolder extends ViewHolder {
        HeaderViewHolder(View headerView) {
            super(headerView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("1","확인111");
        Log.d("Search", "확인");
        RecyclerView.ViewHolder holder;
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_add, parent, false);
            holder = new HeaderViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_test, parent, false);
//            MainActivity.test_view.add(view);
            holder = new ViewHolder(view);
        }
        return (ViewHolder) holder;
    }

    @Override
    public int getItemCount() {
        return title.size() + 1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        } else {
            String Title = title.get(position - 1);
            holder.textTitle.setText(Title);
        }
    }

    // 비동기 처리를 위한 클래스
    private static class InsertAsyncTask extends AsyncTask<Directory, Void, Void> {
        private DirectoryDao mDierctoryDao;

        public InsertAsyncTask(DirectoryDao directoryDao) {
            this.mDierctoryDao = directoryDao;
        }

        @Override
        protected Void doInBackground(Directory... directories) {
            mDierctoryDao.insert(directories[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Directory, Void, Void> {
        private DirectoryDao mDierctoryDao;

        public DeleteAsyncTask(DirectoryDao directoryDao) {
            this.mDierctoryDao = directoryDao;
        }

        @Override
        protected Void doInBackground(Directory... directories) {
            mDierctoryDao.delete(directories[0]);
            return null;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textDescription_1, textDescription_2, textDescription_3, textDescription_4, textDescription_5, Total;
//        ViewHolder holder;

        // db객체
        AppDatabase db = Room.databaseBuilder(mcontext, AppDatabase.class, "directory_db").build();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = (TextView) itemView.findViewById(R.id.textView);
            Total = (TextView) itemView.findViewById(R.id.textView7);
            Log.d("Search", "4");
            // LiveData
            db.directoryDao().getAll().observe((LifecycleOwner) mcontext, new Observer<List<Directory>>() {
                @Override
                public void onChanged(List<Directory> directories) {
                    if(recy_refresh_frag==true){
//                        title.clear();
                        recy_refresh_frag = false;

                        title.add(directories.get(directories.size()-1).toString());

                        ((MainActivity)mcontext).recy_test_view.setAdapter(Adapter.this);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        if (pos == 0) {
                            // 삭제는 어케하누?
//                            new DeleteAsyncTask(db.directoryDao()).execute();
                            AlertDialog.Builder ad = new AlertDialog.Builder(mcontext);
                            ad.setIcon(R.mipmap.ic_launcher);
                            ad.setTitle("제목");
                            ad.setMessage("directory의 이름을 적어주세요");

                            final EditText et = new EditText(mcontext);
                            et.setSingleLine(true);
                            ad.setView(et);

                            // 긍정적인 버튼
                            ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                        String result = et.getText().toString();

                                    new InsertAsyncTask(db.directoryDao()).execute(new Directory(et.getText().toString()));
                                    recy_refresh_frag = true;
                                    Log.d("1", "여긴옴?");
//                                    MainActivity.recyclerView.setAdapter(Adapter.this);


//                                        Toast.makeText(mcontext,db.directoryDao().getAll().toString(),Toast.LENGTH_SHORT).show();
                                    dialog.dismiss(); // 모든 작업이 끝났으니 dialog를 닫어라
                                }
                            });

                            // 부정적인 버튼
                            ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss(); // 모든 작업이 끝났으니 dialog를 닫어라
                                }
                            });
                            ad.show();

//                            Toast.makeText(mcontext,"add Btn",Toast.LENGTH_SHORT).show();
                        } else if (mListener != null) {
                            mListener.onItemClick(v, pos - 1);
                        }
                    }
                }
            });
        }
    }
}
