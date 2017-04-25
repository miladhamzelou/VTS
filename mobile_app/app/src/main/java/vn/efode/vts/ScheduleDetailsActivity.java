package vn.efode.vts;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import vn.efode.vts.model.Schedule;

public class ScheduleDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtScheduleId, txtDriverId, txtVehicleId, txtStartPointAddress, txtEndPointAddress, txtIntendStartTime, txtIntendEndTime, txtScheduleStatusTypeId;
    Intent intentSchedule;
    Button btnStartSchedule;
    Schedule schedule;
    ImageView imgScheduleStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addControlls();
        addEvents();

        intentSchedule = getIntent();
        Bundle bundleSchedule = intentSchedule.getBundleExtra("ScheduleDetails");
        schedule = new Schedule();
        schedule = (Schedule) bundleSchedule.get("ListSchedule");

        setContentSchedule();

    }

    /**
     * set content details about schedule
     */

    private void setContentSchedule() {
        txtScheduleId.setText("Schedule ID: " + schedule.getScheduleId());
        txtIntendStartTime.setText("Intend Start Time: " + schedule.getIntendStartTime());
        txtIntendEndTime.setText("Intend End Time: " + schedule.getIntendEndTime());
        txtDriverId.setText("Driver Id: " + schedule.getDriverId());
        txtVehicleId.setText("Vehicle Id: " + schedule.getVehicleId());
        txtStartPointAddress.setText("Start Point Address: " + schedule.getStartPointAddress());
        txtEndPointAddress.setText("End Point Address: " + schedule.getEndPointAddress());

        if(schedule.getScheduleStatusTypeId() == 1) {
            btnStartSchedule.setVisibility(View.VISIBLE);
            txtScheduleStatusTypeId.setText("Schedule Status: " + schedule.getScheduleStatusName() + "!!!");
            imgScheduleStatus.setImageResource(R.drawable.notstart);
        }
        else btnStartSchedule.setVisibility(View.INVISIBLE);

        if (schedule.getScheduleStatusTypeId() == 2)
        {
            txtScheduleStatusTypeId.setText("Schedule Status: " + schedule.getScheduleStatusName() + "!!!");
            imgScheduleStatus.setImageResource(R.drawable.complete);
        }
        else
        if (schedule.getScheduleStatusTypeId() == 3)
        {
            txtScheduleStatusTypeId.setText("Schedule Status: " + schedule.getScheduleStatusName() + "!!!");
            imgScheduleStatus.setImageResource(R.drawable.inprogress);
        }
        else
        if (schedule.getScheduleStatusTypeId() == 4)
        {
            txtScheduleStatusTypeId.setText("Schedule Status: " + schedule.getScheduleStatusName() + "!!!");
            imgScheduleStatus.setImageResource(R.drawable.cancelschedule);
        }
    }

    private void addEvents() {
        btnStartSchedule.setOnClickListener(this);
    }

    private void addControlls() {
        btnStartSchedule = (Button) findViewById(R.id.btn_start_schedule);
        txtScheduleId = (TextView) findViewById(R.id.txtScheduleId);
        txtDriverId = (TextView) findViewById(R.id.txtDriverId);
        txtVehicleId = (TextView) findViewById(R.id.txtVehicleId);
        txtStartPointAddress = (TextView) findViewById(R.id.txtStartPointAddress);
        txtEndPointAddress = (TextView) findViewById(R.id.txtEndPointAddress);
        txtIntendStartTime = (TextView) findViewById(R.id.txtIntendStartTime);
        txtIntendEndTime = (TextView) findViewById(R.id.txtIntendEndTime);
        txtScheduleStatusTypeId = (TextView) findViewById(R.id.txtScheduleStatusTypeId);
        imgScheduleStatus = (ImageView) findViewById(R.id.imgScheduleStatus);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start_schedule:
                showDialogAccept();
                break;
        }
    }

    /**
     * Show dialog accept or not
     */
    private void showDialogAccept() {
        if(MainActivity.scheduleActive == null)
            new AlertDialog.Builder(ScheduleDetailsActivity.this)
                    .setTitle("Do you want start this schedule?")
                    .setMessage("Scheduleid:" + schedule.getScheduleId() + "\nAddress: "+schedule.getEndPointAddress())
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                                swtichActivityAndPutData();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        else
            new AlertDialog.Builder(ScheduleDetailsActivity.this)
                .setTitle("You are in a schedule active!")
                .setMessage("Scheduleid:" + MainActivity.scheduleActive.getScheduleId() + "\nAddress: "+ MainActivity.scheduleActive.getEndPointAddress())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    /**
     * Start activity and put data to another Activity
     */
    private void swtichActivityAndPutData(){
        Intent intent = new Intent(ScheduleDetailsActivity.this, MainActivity.class);
        MainActivity.scheduleActive = schedule;
        startActivity(intent);
        finish();
    }



}
