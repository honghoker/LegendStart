package com.example.ls_listsave;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ls_listsave.LSSQLContract;
import com.example.ls_listsave.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public RecyclerAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public TextView nameText;
        public TextView textView;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.list_recyclerview_nametextView);
            textView = itemView.findViewById(R.id.list_recyclerview_textView2);
        }
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater는 안드로이드에서 View를 만드는 가장 기본적인 방법입니다.
        //LayoutInflater : Fragment의 View를 만들거나 RecyclerView에서 ViewHolder를 만들때, CustomView에서
        // xml로 정의된 View를 merge할 때 등 여러곳에서 사용됩니다.


        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_recyclerview,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        //Cursor는 Table에서 행을 참조합니다.
        //Cursor.moveToPosition(position) : Cursor의 position 위치로 이동

        if(!mCursor.moveToPosition(position))
            return;

        //name에는 Cursor가 가리키는 행의 String을 가져옴(행의 인덱스 번호로 찾아서 이름을 검색)
        String name = mCursor.getString(mCursor.getColumnIndex(LSSQLContract.LocationTable.COLUMN_NAME));
        long id = mCursor.getLong(mCursor.getColumnIndex(LSSQLContract.LocationTable._ID));
        //String test = mCursor.getString(mCursor.getColumnIndex(LSSQLContract.TagTable.COLUMN_TAG_1));
        holder.nameText.setText(name);
        //holder.textView.setText(test);
        holder.itemView.setTag(id);
    }

    public Cursor returnCursor(){
        return mCursor;
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor != null)
            mCursor.close();

        mCursor = newCursor;
        if(newCursor != null)
            //notifiyDataSetChanged : 변경된 데이터 확인 후 recyclerview 갱신
            notifyDataSetChanged();
    }

}
