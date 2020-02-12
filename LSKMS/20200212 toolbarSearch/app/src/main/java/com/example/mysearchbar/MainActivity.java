package com.example.mysearchbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    EditText ed;
    ClearableEditText ct;
    AutoCompleteTextView ac;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed = findViewById(R.id.editText2);
        ct = findViewById(R.id.clear_text);
        ac = findViewById(R.id.clearable_edit); //실제 자동완성 텍스트
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); //키보드-시스템서비스


        toolbar = findViewById(R.id.toolbar);
        // spinner 선언
        // toolbar 초기 Title 선언
        toolbar.setTitle("여기는 됌?");
        // **NoActionBar 해주고 이 메서드 호출하면 toolbar를 Activity의 앱바로 사용가능

        setSupportActionBar(toolbar);
    } // end of onCreate

    public void ButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.button:

                ed.requestFocus();
                ed.setCursorVisible(true);
                Toast.makeText(this,"버튼눌렀다",Toast.LENGTH_SHORT).show();
                //event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 100, 100, 0);
                //new Instrumentation().sendPointerSync(event);
                break;
            case R.id.button2:

                ct.requestFocus();
                Toast.makeText(this,"버튼눌렀다",Toast.LENGTH_SHORT).show();
                //event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 100, 100, 0);
                //new Instrumentation().sendPointerSync(event);
                break;
            default: break;
        }

    }
    public void TouchEventExec() {

        Instrumentation inst = new Instrumentation();

        long downTime = SystemClock.uptimeMillis();

        long eventTime = SystemClock.uptimeMillis();

        MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 100, 100, 0);

        MotionEvent event2 = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 100, 100, 0);

        inst.sendPointerSync(event);

        inst.sendPointerSync(event2);

    }

    @Override //메뉴 생성 메소드
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);//xml로 메뉴를 만드는 부분
        return true;
    }

    InputMethodManager imm; //키보드 설정 위한
    //상단 툴바 클릭 이벤트
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_search: //상단 검색 버튼 클릭 시
                Toast.makeText(getApplicationContext(), "검색할 장소를 입력하세요.", Toast.LENGTH_LONG).show();
//                Animation animationH = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translatehide); //애니메이션 디렉토리의 애니종류 xml 선택
//                ibtn.setAnimation(animationH); //각 버튼에 애니메이션 세팅
//                ibtn.setVisibility(mView.GONE);
                // Toast.makeText(this, "i wanna go home. .2 . ", Toast.LENGTH_SHORT).show();
                //툴바 제거
                if (getSupportActionBar().isShowing()) {
                    getSupportActionBar().hide();
                    ct.requestFocus();
                    //editText.setFocusableInTouchMode(true);
                    Log.d("오류", "requestFocus 오류",null);
                    //clearbleText.requestFocus();
                    Log.d("오류2", "requestFocus 오류",null);

                    imm.showSoftInput(ac, 0);
                }
                return true;
            default:
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
            getSupportActionBar().show();
            ct.clearFocus();
        imm.hideSoftInputFromWindow(ac.getWindowToken(), 0);
        }

}
