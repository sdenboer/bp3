package com.example.bp3.views.fragments.Opdracht.Docent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.bp3.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sven
 */
public class DateAndTimePicker extends DialogFragment {
    private IDataSendDeadline send;
    private int jaar;
    private int maand;
    private int dag;
    private int uur;
    private int minuut;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_NoActionBar_Fullscreen);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.datetimepicker, container, false);
        send = (IDataSendDeadline) getActivity();
        Calendar currCalendar = Calendar.getInstance();
        jaar = currCalendar.get(Calendar.YEAR);
        maand = currCalendar.get(Calendar.MONTH);
        dag = currCalendar.get(Calendar.DAY_OF_MONTH);
        uur = currCalendar.get(Calendar.HOUR_OF_DAY);
        minuut = currCalendar.get(Calendar.MINUTE);
        showUserSelectDateTime(root);

        DatePicker datePicker = root.findViewById(R.id.datePickerExample);
        datePicker.setMinDate(currCalendar.getTimeInMillis());
        datePicker.init(jaar, maand, dag, (datePicker1, jaar, maand, dag) -> {
            DateAndTimePicker.this.jaar = jaar;
            DateAndTimePicker.this.maand = maand;
            DateAndTimePicker.this.dag = dag;
            showUserSelectDateTime(root);
        });

        TimePicker timePicker = root.findViewById(R.id.timePickerExample);
        timePicker.setHour(this.uur);
        timePicker.setMinute(this.minuut);

        timePicker.setOnTimeChangedListener((timePicker1, uur, minuut) -> {
            DateAndTimePicker.this.uur = uur;
            DateAndTimePicker.this.minuut = minuut;

            showUserSelectDateTime(root);
        });

        Button btnOk = root.findViewById(R.id.datetimepicker_btn);
        btnOk.setOnClickListener(v -> {

            Map<String, Integer> datetime = new HashMap<>();
            datetime.put("jaar", jaar);
            datetime.put("maand", maand + 1);
            datetime.put("dag", dag);
            datetime.put("uur", uur);
            datetime.put("minuut", minuut);
            this.send.sendData(datetime);
            this.dismiss();
//            getActivity().getSupportFragmentManager().popBackStack();
        });
        return root;
    }

    private void showUserSelectDateTime(View root) {
        TextView textView = root.findViewById(R.id.textViewShowDateTime);

        String strBuffer =
                this.dag +
                        "-" +
                        this.maand +
                        "-" +
                        this.jaar +
                        " " +
                        this.uur +
                        ":" +
                        this.minuut;
        textView.setText(strBuffer);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
    }


}
