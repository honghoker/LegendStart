package com.example.lskims;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    private MapView mapView; //네이버 맵뷰

    //하단 바
    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;
    private Frag4 frag4;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //메뉴
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main); //Manifest 추가해주었으면 onCreat 로 액티비티 연결해주고 onCreat 사용해주고 setContentView 로 연결해줌


        mapView = findViewById(R.id.map);  //여기서 findView 로 id 가져오는데 alt + 클릭 map_view 가서 id 랑 map_view 이름 동일한 지 확인
        //구글에서 사용했던 getMapAsync 동일하게 사용하기
        mapView.getMapAsync(this); //여기서 그냥 this 하면 안되고 상단 클래스에 implement 사용해야함



        //하단바
        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.actionCalender:
                        setFrag(0);
                        break;
                    case R.id.actionToDoList:
                        setFrag(1);
                        break;
                    case R.id.actionMaps:
                        setFrag(2);
                        break;
                    case R.id.actionList:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });

        frag1=new Frag1();
        frag2=new Frag2();
        frag3=new Frag3();
        frag4=new Frag4();
        //setFrag(0); // 첫 프래그먼트 화면 지정
    }
    // 프레그먼트 교체
    private void setFrag(int n)
    {
        fm = getSupportFragmentManager();
        ft= fm.beginTransaction();
        switch (n)
        {
            case 0:
                ft.replace(R.id.Main_Frame,frag1);
                ft.commit();
                break;

            case 1:
                ft.replace(R.id.Main_Frame,frag2);
                ft.commit();
                break;

            case 2:
                ft.replace(R.id.Main_Frame,frag3);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.Main_Frame,frag4);
                ft.commit();
                break;

        }
    }


    //하단 바 끝

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Marker marker = new Marker();

        marker.setPosition(new LatLng(35.841621, 128.533436));  //마커 표시하는 기능 마커 두개 이상 출력 불가

        naverMap.setMapType(NaverMap.MapType.Navi); //지도 타입 설정 Basic , Hybrid, Navi(네비), Satellite(위성), Terrain(지형도), None(지도x 오버레이만 나타냄)
        naverMap.setNightModeEnabled(false); //야간모드 시간으로 설정하면 될듯
        naverMap.setLightness(0.2f);//지도 밝기
        naverMap.setBuildingHeight(0.5f); //
        naverMap.setSymbolScale(2); // 심벌 크기
        marker.setMap(naverMap);

        /*마커 찍기
        Marker marker1 = new Marker();
        marker.setPosition(new LatLng(37.5670135, 126.9783740));
        marker.setOnClickListener(overlay -> {
            Toast.makeText(context, "마커 클릭됨", Toast.LENGTH_SHORT).show();
            return true;
        });
        marker.setMap(naverMap);
        마커찍기 종료*/
    /*
        CameraPosition cameraPosition = naverMap.getCameraPosition();

        Toast.makeText(context, "대상 지점 위도: " + cameraPosition.target.latitude + ", " +
                        "대상 지점 경도: " + cameraPosition.target.longitude + ", " +
                        "줌 레벨: " + cameraPosition.zoom + ", " +
                        "기울임 각도: " + cameraPosition.tilt + ", " +
                        "베어링 각도: " + cameraPosition.bearing,
                Toast.LENGTH_SHORT).show();
    */


        NaverMapOptions options = new NaverMapOptions()
                .camera(new CameraPosition(new LatLng(35.1798159, 129.0750222), 8))
                .mapType(NaverMap.MapType.Hybrid);
    }
}
