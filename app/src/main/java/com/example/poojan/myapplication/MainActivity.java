package com.example.poojan.myapplication;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id,firstname,lastname,marks;
    DatabaseHelper myDb;
    Button btn,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);
        firstname=(EditText)findViewById(R.id.EditView1);
        lastname=(EditText)findViewById(R.id.EditView2);
        id=(EditText)findViewById(R.id.EditView0);
        marks=(EditText)findViewById(R.id.EditView3);
        btn=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);
        btn3=(Button)findViewById(R.id.button3);
        Adddata();
        viewalldata();
        DeleteData();;

    }
    public  void DeleteData(){
        btn3.setOnClickListener(
                new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Integer deletedrows=myDb.deleteData(id.getText().toString());
            if(deletedrows>0){Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
        }
        else{Toast.makeText(MainActivity.this, "Data not deleted", Toast.LENGTH_SHORT).show();}
    }});}

    public void Adddata(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted =myDb.insertdata(firstname.getText().toString(),lastname.getText().toString(),marks.getText().toString());
                if(isInserted = true){
                    Toast.makeText(MainActivity.this, "Text Inserted", Toast.LENGTH_SHORT).show();}
                    else{Toast.makeText(MainActivity.this,"TExt not inserted", Toast.LENGTH_SHORT).show();}
            }
        });
    }
    public void viewalldata(){
        btn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Cursor res=myDb.getAllData();
                                        if(res.getCount()==0){
                                            showMessage("Error","Nothing Found");
                                            return;

                                    }
                                    StringBuffer buffer=new StringBuffer();
                                        while(res.moveToNext()){buffer.append("ID"+res.getString(0)+"\n");
                                            buffer.append("FirstName"+res.getString(1)+"\n");buffer.append("LastName"+res.getString(2)+"\n");
                                            buffer.append("Marks"+res.getString(3)+"\n");
                                            showMessage("Data",buffer.toString());


                                }}}

        );
    }
public  void  showMessage(String Title,String Message){
    AlertDialog.Builder builder=new AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setTitle(Title);
    builder.setMessage(Message);
    builder.show();
}





}


