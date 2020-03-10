package com.example.calendar;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.calendar.library.CompactCalendarView;
import com.example.calendar.library.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CompactCalendarTab extends Fragment {

    private static final String TAG = "MainActivity";
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("M월 dd일", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("yyyy년 - MMM ", Locale.getDefault());
    private boolean shouldShow = false;
    private CompactCalendarView compactCalendarView;
    private ActionBar toolbar;
    Calendar nowDate = Calendar.getInstance();
    ArrayAdapter adapter;
    List<String> mutableBookings;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View mainTabView = inflater.inflate(R.layout.main_tab,container,false);

        mutableBookings = new ArrayList<>();

        final ListView bookingsListView = mainTabView.findViewById(R.id.bookings_listview);
        Button btn_todoadd = mainTabView.findViewById(R.id.Btn_todoadd);
//        final Button showPreviousMonthBut = mainTabView.findViewById(R.id.prev_button);
//        final Button showNextMonthBut = mainTabView.findViewById(R.id.next_button);
//        final Button slideCalendarBut = mainTabView.findViewById(R.id.slide_calendar);
//        final Button showCalendarWithAnimationBut = mainTabView.findViewById(R.id.show_with_animation_calendar);
//        final Button setLocaleBut = mainTabView.findViewById(R.id.set_locale);
//        final Button removeAllEventsBut = mainTabView.findViewById(R.id.remove_all_events);

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mutableBookings);
        bookingsListView.setAdapter(adapter);
        compactCalendarView = mainTabView.findViewById(R.id.compactcalendar_view);

        // below allows you to configure color for the current day in the month
        // compactCalendarView.setCurrentDayBackgroundColor(getResources().getColor(R.color.black));
        // below allows you to configure colors for the current day the user has selected
        // compactCalendarView.setCurrentSelectedDayBackgroundColor(getResources().getColor(R.color.dark_red));
        compactCalendarView.setUseThreeLetterAbbreviation(false);
        compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        compactCalendarView.setIsRtl(false);
        compactCalendarView.displayOtherMonthDays(false);

        //compactCalendarView.setIsRtl(true);
        //loadEvents();
        //loadEventsForYear(2017);
        //loadMyEvent(2020);
        compactCalendarView.invalidate();

        logEventsByMonth(compactCalendarView);

        // below line will display Sunday as the first day of the week
        // compactCalendarView.setShouldShowMondayAsFirstDay(false);

        // disable scrolling calendar
        // compactCalendarView.shouldScrollMonth(false);

        // show days from other months as greyed out days
        // compactCalendarView.displayOtherMonthDays(true);

        // show Sunday as first day of month
        // compactCalendarView.setShouldShowMondayAsFirstDay(false);

        //set initial title
        toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        toolbar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        nowDate = Calendar.getInstance();
        //set title on calendar scroll
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                toolbar.setTitle(dateFormatForMonth.format(dateClicked));
                List<Event> bookingsFromMap = compactCalendarView.getEvents(dateClicked);
                Log.d(TAG, "inside onclick " + dateFormatForDisplaying.format(dateClicked));

                //bookingsListView.setVisibility(View.VISIBLE); // todolist 띄우기
                (mainTabView.findViewById(R.id.listviewLayout)).setVisibility(View.VISIBLE);
                nowDate.setTime(dateClicked);
                if (bookingsFromMap != null) {
                    Log.d(TAG, bookingsFromMap.toString());
                    mutableBookings.clear();
                    for (Event booking : bookingsFromMap) {
                        mutableBookings.add((String) booking.getData());
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                toolbar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });

        btn_todoadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMyEvent();
            }
        });

//        showPreviousMonthBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                compactCalendarView.scrollLeft();
//            }
//        });
//
//        showNextMonthBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                compactCalendarView.scrollRight();
//            }
//        });
//
//        final View.OnClickListener showCalendarOnClickLis = getCalendarShowLis();
//        slideCalendarBut.setOnClickListener(showCalendarOnClickLis);
//
//        final View.OnClickListener exposeCalendarListener = getCalendarExposeLis();
//        showCalendarWithAnimationBut.setOnClickListener(exposeCalendarListener);

        compactCalendarView.setAnimationListener(new CompactCalendarView.CompactCalendarAnimationListener() {
            @Override
            public void onOpened() {
            }

            @Override
            public void onClosed() {
            }
        });

//        setLocaleBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Locale locale = Locale.FRANCE;
//                dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", locale);
//                TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
//                dateFormatForDisplaying.setTimeZone(timeZone);
//                dateFormatForMonth.setTimeZone(timeZone);
//                compactCalendarView.setLocale(timeZone, locale);
//                compactCalendarView.setUseThreeLetterAbbreviation(false);
//                loadEvents();
//                loadEventsForYear(2017);
//                logEventsByMonth(compactCalendarView);
//
//            }
//        });
//
//        removeAllEventsBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                compactCalendarView.removeAllEvents();
//            }
//        });


        // uncomment below to show indicators above small indicator events
        // compactCalendarView.shouldDrawIndicatorsBelowSelectedDays(true);

        // uncomment below to open onCreate
        //openCalendarOnCreate(v);

        return mainTabView;
    }

    @NonNull
    private View.OnClickListener getCalendarShowLis() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!compactCalendarView.isAnimating()) {
                    if (shouldShow) {
                        compactCalendarView.showCalendar();
                    } else {
                        compactCalendarView.hideCalendar();
                    }
                    shouldShow = !shouldShow;
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener getCalendarExposeLis() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!compactCalendarView.isAnimating()) {
                    if (shouldShow) {
                        compactCalendarView.showCalendarWithAnimation();
                    } else {
                        compactCalendarView.hideCalendarWithAnimation();
                    }
                    shouldShow = !shouldShow;
                }
            }
        };
    }

    private void openCalendarOnCreate(View v) {
        final LinearLayout layout = v.findViewById(R.id.main_content);
        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16) {
                    layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                compactCalendarView.showCalendarWithAnimation();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        // Set to current day on resume to set calendar to latest day
        // toolbar.setTitle(dateFormatForMonth.format(new Date()));
    }

    private void loadEvents() {
        //addEvents(-1, -1);
        //addEvents(Calendar.DECEMBER, -1);
        //addEvents(Calendar.AUGUST, -1);
    }

    private void logEventsByMonth(CompactCalendarView compactCalendarView) {
        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        currentCalender.set(Calendar.MONTH, Calendar.AUGUST);
        List<String> dates = new ArrayList<>();
        for (Event e : compactCalendarView.getEventsForMonth(new Date())) {
            dates.add(dateFormatForDisplaying.format(e.getTimeInMillis()));
        }
        Log.d(TAG, "Events for Aug with simple date formatter: " + dates);
        Log.d(TAG, "Events for Aug month using default local and timezone: " + compactCalendarView.getEventsForMonth(currentCalender.getTime()));
    }

    private void addMyEvent(){
        Calendar cal = Calendar.getInstance();

        final TimePickerDialog dialog = new TimePickerDialog(this.getContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                currentCalender.setTime(new Date(nowDate.getTimeInMillis()));
                long timelnMillis = currentCalender.getTimeInMillis();
                List<Event> events = getEvents(timelnMillis, hour, min);
                compactCalendarView.addEvents(events);
                mutableBookings.add(dateFormatForDisplaying.format(timelnMillis) + " " + hour + "시 " + min +"분");
                adapter.notifyDataSetChanged();
            }
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);  //마지막 boolean 값은 시간을 24시간으로 보일지 아닐지

        dialog.show();
    }

    private List<Event> getEvents(final long timeInMillis, int hour, int min) {
        List<Event> bookingsFromMap = compactCalendarView.getEvents(timeInMillis);
        int pointcolor;
        switch (bookingsFromMap.size()) {
            case 0:
                pointcolor = Color.argb(255, 169, 68, 65); break;
            case 1:
                pointcolor = Color.argb(255, 100, 68, 65); break;
            case 2:
                pointcolor = Color.argb(255, 70, 68, 65); break;
            default:
                pointcolor = Color.argb(255, 70, 68, 65);
        }

        return Arrays.asList(
                new Event(pointcolor, timeInMillis, dateFormatForDisplaying.format(timeInMillis) + " " + hour + "시 " + min +"분")
        );
    }

    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}