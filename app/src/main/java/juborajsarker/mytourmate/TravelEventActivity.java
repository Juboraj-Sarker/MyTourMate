package juborajsarker.mytourmate;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TravelEventActivity extends AppCompatActivity {

    Dialog dialog;
    String destination = "";
    String budget = "";
    String fromDate = "";
    String toDate = "";
    private Button saveBtn;
    private EditText destinationEt;
    private EditText budgetEt;
    private EditText fromDateEt;
    private EditText toDateEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_event);

        createDialog();
    }


    private void createDialog() {

        dialog = new Dialog(this);
        dialog.setTitle("Add new Event");
        dialog.setContentView(R.layout.custom_layout);
        saveBtn = (Button) dialog.findViewById(R.id.create_event_btn);
        destinationEt = (EditText) dialog.findViewById(R.id.travelDestinationET);
        budgetEt = (EditText) dialog.findViewById(R.id.budgetET);
        fromDateEt = (EditText) dialog.findViewById(R.id.fromDateET);
        toDateEt = (EditText) dialog.findViewById(R.id.toDateET);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_new, menu);
        return true;
    }


    public void add_new(MenuItem item) {

        dialog.show();

        Button createNewEvent = (Button) dialog.findViewById(R.id.create_event_btn);
        createNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                destination = destinationEt.getText().toString();
                budget = budgetEt.getText().toString();
                fromDate = fromDateEt.getText().toString();
                toDate = toDateEt.getText().toString();

                dialog.dismiss();
            }
        });
    }

}
