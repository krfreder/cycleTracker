package com.example.menstrualtracker;

import android.content.Intent;
import android.view.View;
import android.widget.CalendarView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    declare variables
    public static final String RESULT = "result";
    public static final String EVENT = "event";
    public static final int ADD_NOTE = 44;

    private CalendarView calendarView;
    private List<EventDay> eventDayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//snags the calendar
        calendarView = (CalendarView) findViewById(R.id.calendarView);
//sets listener to submit entered note
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
//sets listener to preview note
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                previewNote(eventDay);
            }
        });
//if everything checks out with database, sets event date and note to database
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == ADD_NOTE && resultCode == RESULT_OK) {
                MyEventDay myEventDay = data.getParcelableExtra(RESULT);
                calendarView.setDate(myEventDay.getCalendar());
                eventDayList.add(myEventDay);
                calendarView.setEvents(eventDayList);
            }
        }

        private void addNote() {
            Intent intent = new Intent(this, AddNoteActivity.class);
            startActivityForResult(intent, ADD_NOTE);
        }

        private void previewNote(EventDay eventDay) {
            Intent intent = new Intent(this, NotePreviewActivity.class)
            if(eventDay instanceof MyEventDay) {
                intent.putExtra(EVENT, (MyEventDay) eventDay);
            }
            startActivity(intent);
        }

    }

}