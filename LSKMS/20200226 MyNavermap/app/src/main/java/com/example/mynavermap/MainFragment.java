package com.example.mynavermap;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.LatLngBounds;
import com.naver.maps.geometry.Utmk;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;

//프래그먼트는 액티비티위에 올라가있을떄만 프래그먼트로서 동작할 수 있다.
public class MainFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {
    //프래그먼트
    MainActivity activity;

    //네이버 api
    NaverMapOptions options; //초기 옵션 설정을 위한 옵션

    //네이버 map 전역 변수
    public static NaverMap NMap;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //이 메소드가 호출될떄는 프래그먼트가 엑티비티위에 올라와있는거니깐 getActivity메소드로 엑티비티참조가능
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //이제 더이상 엑티비티 참초가안됨
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //프래그먼트 메인을 인플레이트해주고 컨테이너에 붙여달라는 뜻임

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        /*
        Button button = rootView.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChange(1);
            }
        });
        */
        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //1-1. 프래그먼트 생성 시 불러오기
        FragmentManager fm = getChildFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map); //맵프래그먼트 객체 생성 map id 가져와서

        setFirstOptions(); //1-8. 초기 옵션 설정

        if (mapFragment == null) { //맵프래그먼트 생성된 적 없으면
            mapFragment = MapFragment.newInstance(options); //새로 만들어주고   // 1-8. 초기옵션 추가
            Toast.makeText(getContext(), "맵 생성 완료", Toast.LENGTH_SHORT).show();
            fm.beginTransaction().add(R.id.map, mapFragment).commit(); // 프래그매니저에게 명령 map 레이아웃에 생성된 맵 객체를 add
        }
        //1-3. 맵 프래그먼트에 NaverMap 객체 가져옴 : MapFragment 및 MapView는 지도에 대한 뷰 역할만을 담당하므로 API를 호출하려면 인터페이스 역할을 하는 NaverMap 객체가 필요
        mapFragment.getMapAsync(this); //이거 만들면 onMapReady 사용 가능

        //1-4. LatLng : 위경도 좌표를 나타내는 클래스. latitude 속성이 위도를, longitude 속성이 경도를 나타냅니다. LatLng의 모든 속성은 final이므로 각 속성은 생성자로만 지정할 수 있고, 한 번 생성된 객체의 속성은 변경할 수 없습니다.
        LatLng coord = new LatLng(37.5670135, 126.9783740); //위경도좌표 coord 는 새로운 좌표(위도, 경도)

        Toast.makeText(getContext(), "위도: " + coord.latitude + ", 경도: " + coord.longitude, Toast.LENGTH_SHORT).show(); //coord.latitude : 위도값, coord.longitude : 경도값 반환
        //-> 주소 저장할 때 각 위경도 값을 저장

        //1-5. 다른 좌표 표현방법
        Utmk utmk = new Utmk(953935.5, 1952044.1);
        LatLng latLng = utmk.toLatLng(); //형변환 가능

        LatLng latLng2 = new LatLng(37.5666103, 126.9783882);
        Utmk utmk2 = Utmk.valueOf(latLng);

        //1-6. MBR 최소 경계 사각형 (Minimum Bounding Rectangle
        //각각 남서쪽과 북동쪽 꼭지점을 의미하는 두 개의 좌표가 주어진다면 직사각형 형태의 영역을 만들 수 있습니다. 이를 최소 경계 사각형 또는 MBR
        LatLng southWest = new LatLng(31.43, 122.37);
        LatLng northEast = new LatLng(44.35, 132);
        LatLngBounds bounds = new LatLngBounds(southWest, northEast); //MBR 생성

        //1-7. 디스플레이 - 심벌크기 / 건물원근감 / 건물표시 /

        //1-8. 지도 초기 옵션 : NaverMapOptions 어플 껐을 때 마지막 위치 저장 등 해야함. 상단에 선언해준다. 78줄
        //하단 함수선언

        //1-9. 카메라 위치 선언 : cameraPostion 속성 final

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@보류
        //1-10. 카메라 이동 : 보류

    }

    public void setFirstOptions() { //1-8. 초기옵션 설정
        options = new NaverMapOptions()
                .camera(new CameraPosition(new LatLng(35.857654, 128.498123), 16))

                .mapType(NaverMap.MapType.Navi)
                .nightModeEnabled(true);
    } //1-8. 초기옵션 설정

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@보류
    public void setLocation(Context context) { //1-9. 카메라 위치 선언
        CameraPosition cameraPosition = new CameraPosition(
                new LatLng(35.857654, 128.498123), // 대상 지점
                //35.857654, 128.498123 그린빌
                16, // 줌 레벨
                20, // 기울임 각도
                180 // 베어링 각도
        );

        Toast.makeText(context,
                "대상 지점 위도: " + cameraPosition.target.latitude + ", " +
                        "대상 지점 경도: " + cameraPosition.target.longitude + ", " +
                        "줌 레벨: " + cameraPosition.zoom + ", " +
                        "기울임 각도: " + cameraPosition.tilt + ", " +
                        "베어링 각도: " + cameraPosition.bearing,
                Toast.LENGTH_SHORT).show();
    }//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@보류

    //1-10.카메라 현재 위치 얻기
    //CameraPosition cameraPosition = naverMap.getCameraPosition();
    public void getLocationPosition(Context context, NaverMap naverMap){
        CameraPosition cameraPosition = NMap.getCameraPosition(); //현재 위치 정보 반환하는 메소드

        Toast.makeText(context,
                "현재위치 = 대상 지점 위도: " + cameraPosition.target.latitude + ", " +
                        "대상 지점 경도: " + cameraPosition.target.longitude + ", " +
                        "줌 레벨: " + cameraPosition.zoom + ", " +
                        "기울임 각도: " + cameraPosition.tilt + ", " +
                        "베어링 각도: " + cameraPosition.bearing,
                Toast.LENGTH_SHORT).show();
    }// getL~~종료



    //1-11. 지도 패딩
    // NaverMap.setContentPadding()을 호출하면 콘텐츠 패딩을 지정할 수 있습니다.
    // naverMap.setContentPadding(0, 0, 0, 200);



    //1-12. 화면 좌표 <-> 지도 좌표 변환

    //1-13. UI Setting
    public void setMapUI(NaverMap naverMap){
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setZoomControlEnabled(false);
        uiSettings.setLocationButtonEnabled(true);
        uiSettings.isLocationButtonEnabled();
    }


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@보류 타 액티비티에서 이벤트 주기
    public void onClickFragmentBtn(View v) {
        switch (v.getId()) {
            case R.id.btncameraLocation:
                setLocation(getActivity());
                break;
            default:
                break;
        }
    }

    @Override //자동으로 호출
    public void onMapReady(@NonNull NaverMap naverMap) {
//        naverMap.setMapType(NaverMap.MapType.Navi);
//        naverMap.setNightModeEnabled(true);
        NMap = naverMap; //전역에 naverMap 당겨옴
        NMap.setContentPadding(0, 100, 0, 50);
        setMapUI(NMap);

        NMap.addOnCameraIdleListener(new NaverMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                getLocationPosition(activity, NMap);
                Log.d("MapMap", "onCameraIdle");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btncameraLocation:
                setLocation(getActivity());
                break;
            default:
                break;
        }
    }
}