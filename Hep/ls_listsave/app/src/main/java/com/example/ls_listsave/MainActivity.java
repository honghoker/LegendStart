package com.example.ls_listsave;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import com.example.ls_listsave.DataLappingByContentValues.DataLapping_LocationData;
import com.example.ls_listsave.DataLappingByContentValues.DataLapping_Tag;
import com.example.ls_listsave.DataBase.LSSQLContract.*;
import com.example.ls_listsave.LocationList_RecyclerView.LocationList;

import com.example.ls_listsave.DataLappingByContentValues.DataLapping_LocationData;
import com.example.ls_listsave.DataLappingByContentValues.DataLapping_Tag;
import com.example.ls_listsave.DataBase.LSSQLContract.*;
import com.example.ls_listsave.LocationList_RecyclerView.LocationList;

public class MainActivity extends Activity {
   private static final int GET_LOCATION_LIST_REQUEST_CODE = 100;

    private List<String> list;
    EditText Location_Name; // 이름
    TextView Location_Address; // 주소
    EditText Location_DetailAddress; // 상세주소
    EditText Location_Number; // 연락처
    EditText Location_Comment; // 메모

    ViewPager viewPager; // 이미지

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listinit(); // 저장된 태그불러오기 구현 필요
        init();
        PermissionCheck();
    }
  


    public void PermissionCheck() {
        // 6.0 마쉬멜로우 이상일 경우에는 권한 체크 후 권한 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "권한 설정 완료");
            } else {
                Log.d(TAG, "권한 설정 요청");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }


        public void listinit() {
            list = new ArrayList<String>();
            list.add("소고기");
            list.add("돼지고기");
            list.add("오리고기");
            list.add("닭고기");
            list.add("양고기");
            list.add("개고기");
        }

        public void init() {
            Location_Name = findViewById(R.id.Text_Name);
            Location_Address = findViewById(R.id.Text_Addr);
            Location_DetailAddress = findViewById(R.id.Text_DetailAddr);
            Location_Number = findViewById(R.id.Text_Number);
            Location_Comment = findViewById(R.id.Text_Comment);



            final AutoCompleteTextView autoCompleteTextView = findViewById(R.id.clearable_edit);
          
    public void init() {
        Location_Name = ((ClearableEditText) findViewById(R.id.Text_Name)).editText;
        Location_Name.setHint("이름");

        Location_Address = findViewById(R.id.Text_Addr);

        //등록된 주소가 없거나 오류일 때
        Location_Address.setTextColor(Color.RED);
        Location_Address.setPaintFlags(Location_Address.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Location_Address.setText("주소 검색 실패");
        /* ***** */

        Location_DetailAddress = ((ClearableEditText) findViewById(R.id.Text_DetailAddr)).editText;
        Location_DetailAddress.setHint("상세주소");

        Location_Number = ((ClearableEditText) findViewById(R.id.Text_Number)).editText;
        Location_Number.setHint("연락처");

        Location_Comment = ((ClearableEditText) findViewById(R.id.Text_Comment)).editText;
        Location_Comment.setHint("메모");

        viewPager = findViewById(R.id.viewPager);

        for (String s : HashTag.getHashTagar()) {
            // 등록한 해시태그 가져올때 사용
        }

        final AutoCompleteTextView autoCompleteTextView = ((HashEditText) findViewById(R.id.Text_Hash)).editText;
    }
        public void hashtag_Add(String Hash){
            AutoCompleteTextView HashText = findViewById(R.id.clearable_edit);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hashtag_Add(((TextView) view).getText().toString());
            }
        });

        ((ScrollView) findViewById(R.id.Scroll_Main)).fullScroll(View.FOCUS_DOWN);

    }

    public void onButtonHashTagAddClicked(View v) {
        hashtag_Add(((HashEditText) findViewById(R.id.Text_Hash)).editText.getText().toString().trim());
    }

    public void hashtag_Add(String Hash) {
        AutoCompleteTextView HashText = ((HashEditText) findViewById(R.id.Text_Hash)).editText;

        if (HashText.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
            hashtext_set(Hash);
        } else if (HashTag.getHashTagar().size() == 5) {
            Toast.makeText(getApplicationContext(), "태그는 5개까지 추가할 수 있습니다.", Toast.LENGTH_SHORT).show();
            hashtext_set(Hash);
        } else if (HashTag.getHashTagar().contains(Hash)) {
            Toast.makeText(getApplicationContext(), "이미 추가한 태그입니다.", Toast.LENGTH_SHORT).show();
            hashtext_set(Hash);
        } else {
            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(20, 20);
            HashTag hashtag = new HashTag(this);

            public void onPersonAddClicked (View v){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, 0);
            }
            ((FlowLayout) findViewById(R.id.flowlayout)).addView(hashtag);
        }
    }

    public void btnSaveOnClick(View view) {
        if (checkEditText()) {
            //Context context, final String tablename, String locationName, String address, String detailAddr, String phone, String comment
            DataLapping_LocationData dataLapping_locationData = new DataLapping_LocationData(
                    getApplicationContext(), LocationTable.TABLE_NAME, Location_Name.getText().toString(), Location_Address.getText().toString(),
                    Location_DetailAddress.getText().toString(), Location_Number.getText().toString(), Location_Comment.getText().toString()
            );
            if (dataLapping_locationData.storeConfirm()) {
                if (!HashTag.getHashTagar().isEmpty()) {
                    int count = dataLapping_locationData.idNumber();
                    DataLapping_Tag dataLapping_tag = new DataLapping_Tag(getApplicationContext(), TagTable.TABLE_NAME, HashTag.getHashTagar(), count);
                    if (!dataLapping_tag.inputInnerDataBase(dataLapping_tag.receiveDataToContentValues())) {
                        Toast.makeText(getApplicationContext(), "Tag Fail", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Intent intent = new Intent(getApplicationContext(), LocationList.class);
                startActivityForResult(intent, GET_LOCATION_LIST_REQUEST_CODE);
            }
        }

    public void hashtext_set(String Hash) {
        ((HashEditText) findViewById(R.id.Text_Hash)).editText.setText(Hash);
        ((HashEditText) findViewById(R.id.Text_Hash)).editText.setSelection(((HashEditText) findViewById(R.id.Text_Hash)).editText.length());
    }

    static final int Phone_Result = 0;
    static final int PICK_IMAGE = 1;
    static final int CAPTURE_IMAGE = 2;

    public void onPersonAddClicked(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(intent, Phone_Result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 연락처 불러오기
        if (requestCode == Phone_Result && resultCode == RESULT_OK) {
            Cursor cursor = getContentResolver().query(data.getData(),
                    new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
            cursor.moveToFirst();
            Location_Name.setText(cursor.getString(0));     //0은 이름을 얻어옵니다.
            Location_Number.setText(cursor.getString(1));   //1은 번호를 받아옵니다.
            cursor.close();
        }

        // 갤러리에서 사진 가져오기
        else if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            final List<Bitmap> bitmaps = new ArrayList<>();
            ClipData clipData = data.getClipData();
            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    try {
                        Uri imageUri = clipData.getItemAt(i).getUri();
                        InputStream is = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        is.close();
                        bitmaps.add(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    InputStream is = getContentResolver().openInputStream(data.getData());
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                    bitmaps.add(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            List<Bitmap> bitmapList = new ArrayList<>();
            for (Bitmap b : bitmaps) {
                bitmapList.add(b);
            }
            viewPager.setAdapter(new ViewPagerAdapter(this, bitmapList));

        }
        // 카메라
        else if (requestCode == CAPTURE_IMAGE && resultCode == RESULT_OK && data.hasExtra("data")) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (bitmap != null) {
                ArrayList<Bitmap> bitmapArrayList = new ArrayList<>();
                bitmapArrayList.add(bitmap);

                viewPager.setAdapter(new ViewPagerAdapter(getApplicationContext(), bitmapArrayList));
            }
        }
    }

    // 화면에 맞게 이미지 크기 조절
    public Bitmap resizeBitmapImg(Bitmap source, int maxWidth, int maxHeight) {
        //Bitmap bitmap1 = resizeBitmapImg(bitmap, ((LinearLayout) findViewById(R.id.Layout_Image)).getWidth() / 5, ((LinearLayout) findViewById(R.id.Layout_Image)).getHeight()); *사용법*
        int newWidth = maxWidth;
        int newHeight = maxHeight;

        return Bitmap.createScaledBitmap(source, newWidth, newHeight, true);
    }

    // 권한 요청
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult");
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
        }
    }

    // 사진 추가
    public void onBtn_ImageAddClicked(View v) {
        final CharSequence[] PhotoModels = {"갤러리", "카메라"};
        final AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setTitle("사진 가져오기");
        alt_bld.setSingleChoiceItems(PhotoModels, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    // 갤러리
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) { // 권한 요청
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                        return;
                    }
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_IMAGE);
                    dialog.cancel();
                } else {
                    // 카메라
                    try {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAPTURE_IMAGE);
                        dialog.cancel();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        final AlertDialog alert = alt_bld.create();
        alert.show();
    }


    public void listshowOnButton(View view) {
        Intent intent = new Intent(getApplicationContext(), LocationList.class);
        startActivityForResult(intent, GET_LOCATION_LIST_REQUEST_CODE);
    }
}
