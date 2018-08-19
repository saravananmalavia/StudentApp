package org.evision.saravanan.studentapp;

/**
 * Created by saravanan on 18/08/18.
 * Student MainActivity Class
 *
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etStudentId,etStudentName,etDepartment;
    Button btnAdd,btnUpdate,btnDelete,btnFind;
    TextView tvList,tvMessage;

    MyDBHandler dbHandler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new MyDBHandler(this);

        etStudentId = (EditText)findViewById(R.id.studentid);
        etStudentName = (EditText)findViewById(R.id.studentname);
        etDepartment = (EditText)findViewById(R.id.department);

        btnAdd = (Button) findViewById(R.id.btnadd);
        btnUpdate = (Button) findViewById(R.id.btnupdate);
        btnDelete = (Button) findViewById(R.id.btndelete);
        btnFind = (Button) findViewById(R.id.btnfind);


        tvList = (TextView) findViewById(R.id.lst);
        tvMessage = (TextView) findViewById(R.id.tvmessage);

        tvList.setText(dbHandler.loadHandler());

        // code to add a new record

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long result=0;
                int id = Integer.parseInt(etStudentId.getText().toString());
                String name = etStudentName.getText().toString();
                String department = etDepartment.getText().toString();
                Student student = new Student(id, name,department);

                result = dbHandler.addHandler(student);
                if(result > 0) {
                    etStudentId.setText("");
                    etStudentName.setText("");
                    etDepartment.setText("");

                    tvMessage.setText("Record added successfully.");
                }
                else
                {
                    tvMessage.setText("Error : Please try again later");
                }

                //call the method to display all the records
                tvList.setText(dbHandler.loadHandler());

            }
        });


        // code to find the record which matches the name entered


        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student =
                        dbHandler.findHandler(etStudentName.getText().toString());
                if (student != null) {
                    tvList.setText(String.valueOf(student.getID()) + " " + student.getStudentName() + System.getProperty("line.separator"));
                    etStudentId.setText("");
                    etStudentName.setText("");
                    etDepartment.setText("");
                } else {
                    tvMessage.setText("No Match Found");
                    etStudentId.setText("");
                    etStudentName.setText("");
                    etDepartment.setText("");
                }

            }
        });


        // code to delete the record which matches the studentid entered in the textview

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean result = dbHandler.deleteHandler(Integer.parseInt(
                        etStudentId.getText().toString()));
                if (result) {
                    etStudentId.setText("");
                    etStudentName.setText("");
                    etDepartment.setText("");
                    tvMessage.setText("Record Deleted");
                } else
                    tvMessage.setText("No Match Found");

                //call the method to display all the records
                tvList.setText(dbHandler.loadHandler());
            }
        });

// code to update the record

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = dbHandler.updateHandler(Integer.parseInt(
                        etStudentId.getText().toString()), etStudentName.getText().toString(),etDepartment.getText().toString());
                if (result) {
                    etStudentId.setText("");
                    etStudentName.setText("");
                    etDepartment.setText("");
                    tvMessage.setText("Record Updated");
                } else
                    tvMessage.setText("No Match Found");
                //call the method to display all the records

                tvList.setText(dbHandler.loadHandler());
            }
        });


    }

}
