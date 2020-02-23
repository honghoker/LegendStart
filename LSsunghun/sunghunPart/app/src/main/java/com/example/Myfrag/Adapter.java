package com.example.Myfrag;

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
import androidx.room.Room;

import com.example.Myfrag.R;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    static List<String> title;
    //    private List<String> data;
    //    private List<String> des_data;
    private List<String[]> tag;
    Context mcontext;
//    View view;
//    List<View> test_view;

    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;
//    AppDatabase db = Room.databaseBuilder(mcontext,AppDatabase.class,"directory_db").build();

    ConstraintLayout recy_card_layout;

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else
            return TYPE_ITEM;
    }

    //    private String btn = "btn";
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
        Log.d("Search", "adapter 생성자확인");
//        this.tag = Tag;
    }

//    Adapter(Context context, List<String> des_data, List<String> data){
//        this.layoutInflater = LayoutInflater.from(context);
//        this.data = data;
//        this.des_data = des_data;
//        mcontext = context;
//    }

    class HeaderViewHolder extends ViewHolder {
        HeaderViewHolder(View headerView) {
            super(headerView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
//            MainActivity.test_view.add(holder);
//            view.setVisibility(View.GONE);
        }
        return (ViewHolder) holder;
//        return new ViewHolder(view);

//        View view = layoutInflater.inflate(R.layout.recy_test, parent, false);
//        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
//        return 1;
        return title.size() + 1;
    }

//    public void test(){
//        if(MainActivity.recyFrag==false){
////            int a = MainActivity.test_view.size();
////            Log.d("1",Integer.toString(a));
////            view.setVisibility(View.GONE);
//        }
//        else{
////            view.setVisibility(View.VISIBLE);
//        }
//    }

//    AppDatabase db = Room.databaseBuilder(mcontext,AppDatabase.class,"directory_db").build();

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        String Title = title.get(position);
//        String des = des_data.get(position);
//        String[] Tag_1 = tag.get(position);
//        holder.textTitle.setText("실험");
//        holder.textTitle.setText(Title);
//        holder.textDescription.setText(des);

        if (holder instanceof HeaderViewHolder) {
            //없어도 뜨네 ? ㅅㅂ 뭐지
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        } else {
//            Toast.makeText(mcontext,db.directoryDao().getAll().toString(),Toast.LENGTH_SHORT).show();
            String Title = title.get(position - 1);
            holder.textTitle.setText(Title);
        }

//        holder.btnTitle.setText(btn);
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
//                Log.d("Search", "itemVIew####   " + itemView.toString());
//                MainActivity.test_view.add(itemView);
//                for(int i =0;i<5; i++){
//                    MainActivity.test_view_2[i] = itemView;
//                }
//                itemView.setVisibility(View.GONE);

            // LiveData
            db.directoryDao().getAll().observe((LifecycleOwner) mcontext, new Observer<List<Directory>>() {
                @Override
                public void onChanged(List<Directory> directories) {
//                    holder.textTitle.setText(directories.toString());
//                        Log.d("1","db확인22"+ db.directoryDao().getAll().toString());
//                        Log.d("1","db확인33"+ directories.toString());
                    Toast.makeText(mcontext, directories.toString(), Toast.LENGTH_SHORT).show();

                    Log.d("1","sival");
//                        MainActivity.recy_test_view.setAdapter(Adapter.this);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        if (pos == 0) {
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

                                    Log.d("1", "여긴옴?");


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
