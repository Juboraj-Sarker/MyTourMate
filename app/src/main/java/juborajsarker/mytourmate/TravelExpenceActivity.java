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

public class TravelExpenceActivity extends AppCompatActivity {

    Dialog dialog;
    String details = "";
    String ammount = "";
    private Button addBtn;
    private EditText expenceDetails;
    private EditText expenceAmmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_expence);

        createDialog();
    }


    private void createDialog() {

        dialog = new Dialog(this);
        dialog.setTitle("Expense Entry");
        dialog.setContentView(R.layout.expence_custom_layout);
        addBtn = (Button) dialog.findViewById(R.id.entryRecord);
        expenceDetails = (EditText) dialog.findViewById(R.id.expenceDetailsET);
        expenceAmmount = (EditText) dialog.findViewById(R.id.expenceAmmountET);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_new, menu);
        return true;
    }


    public void add_new(MenuItem item) {

        dialog.show();

        Button createNewEvent = (Button) dialog.findViewById(R.id.entryRecord);
        createNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                details = expenceDetails.getText().toString();
                ammount = expenceAmmount.getText().toString();
                dialog.dismiss();
            }
        });
    }
}
