package com.example.myfragment1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private LayoutInflater layoutInflater;
    private List<String> data;
    private List<String> des_data;
    private List<String[]> Tag;
    Context mcontext;


    //    private String btn = "btn";
    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }
    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }


    Adapter(Context context, List<String> data, ArrayList<String[]> Tag){
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.Tag = Tag;
        mcontext = context;
    }

//    Adapter(Context context, List<String> des_data, List<String> data){
//        this.layoutInflater = LayoutInflater.from(context);
//        this.data = data;
//        this.des_data = des_data;
//        mcontext = context;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recy_test,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String title = data.get(position);
//        String des = des_data.get(position);
        String[] Tag_1 = Tag.get(position);
        holder.textTitle.setText(title);
//        holder.textDescription.setText(des);


        if(Tag_1.length==0){
            holder.Total.setText(Integer.toString(Tag_1.length));
        }
       else if(Tag_1.length==1){
           holder.textDescription_1.setText(Tag_1[0]);
           holder.textDescription_1.setVisibility(View.VISIBLE);
           // Total은 나중에 거래처 갯수표현할꺼임
           holder.Total.setText(Integer.toString(Tag_1.length));
       }
       else if(Tag_1.length==2){
//           holder.textDescription.setText(Tag_1[0]+" "+Tag_1[1]);
           holder.textDescription_1.setText(Tag_1[0]);
           holder.textDescription_1.setVisibility(View.VISIBLE);
           holder.textDescription_2.setText(Tag_1[1]);
           holder.textDescription_2.setVisibility(View.VISIBLE);
           holder.Total.setText(Integer.toString(Tag_1.length));
        }
       else if(Tag_1.length==3){
//           holder.textDescription.setText(Tag_1[0]+" "+Tag_1[1]+" "+Tag_1[2]);
           holder.textDescription_1.setText(Tag_1[0]);
           holder.textDescription_1.setVisibility(View.VISIBLE);
           holder.textDescription_2.setText(Tag_1[1]);
           holder.textDescription_2.setVisibility(View.VISIBLE);
           holder.textDescription_3.setText(Tag_1[2]);
           holder.textDescription_3.setVisibility(View.VISIBLE);
           holder.Total.setText(Integer.toString(Tag_1.length));
       }
       else if(Tag_1.length==4){
//           holder.textDescription.setText(Tag_1[0]+" "+Tag_1[1]+" "+Tag_1[2]+" "+Tag_1[3]);
           holder.textDescription_1.setText(Tag_1[0]);
           holder.textDescription_1.setVisibility(View.VISIBLE);
           holder.textDescription_2.setText(Tag_1[1]);
           holder.textDescription_2.setVisibility(View.VISIBLE);
           holder.textDescription_3.setText(Tag_1[2]);
           holder.textDescription_3.setVisibility(View.VISIBLE);
           holder.textDescription_4.setText(Tag_1[3]);
           holder.textDescription_4.setVisibility(View.VISIBLE);
           holder.Total.setText(Integer.toString(Tag_1.length));
       }
       else if(Tag_1.length==5){
//           holder.textDescription.setText(Tag_1[0]+" "+Tag_1[1]+" "+Tag_1[2]+" "+Tag_1[3]+" "+Tag_1[4]);
           holder.textDescription_1.setText(Tag_1[0]);
           holder.textDescription_1.setVisibility(View.VISIBLE);
           holder.textDescription_2.setText(Tag_1[1]);
           holder.textDescription_2.setVisibility(View.VISIBLE);
           holder.textDescription_3.setText(Tag_1[2]);
           holder.textDescription_3.setVisibility(View.VISIBLE);
           holder.textDescription_4.setText(Tag_1[3]);
           holder.textDescription_4.setVisibility(View.VISIBLE);
           holder.textDescription_5.setText(Tag_1[4]);
           holder.textDescription_5.setVisibility(View.VISIBLE);
           holder.Total.setText(Integer.toString(Tag_1.length));
       }

//        holder.btnTitle.setText(btn);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle, textDescription_1, textDescription_2,textDescription_3,textDescription_4,textDescription_5,Total;
        Button btnTitle;
        String s_textTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            btnTitle = itemView.findViewById(R.id.recy_test_btn);
            textTitle = itemView.findViewById(R.id.textView);
            textDescription_1 = itemView.findViewById(R.id.textView2);
            textDescription_2 = itemView.findViewById(R.id.textView3);
            textDescription_3 = itemView.findViewById(R.id.textView4);
            textDescription_4 = itemView.findViewById(R.id.textView5);
            textDescription_5 = itemView.findViewById(R.id.textView6);
            Total = itemView.findViewById(R.id.textView7);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(mListener != null){
                            mListener.onItemClick(v,pos);
                        }
                    }
                }
            });
            // 20200206 이 부분 수정해야함
//            s_textTitle = textTitle.toString();
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Toast.makeText(mcontext, s_textTitle, Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }
}
