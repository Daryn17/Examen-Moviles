package cr.ac.itcr.examen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Admi_db admi_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        admi_db = new Admi_db(this, "EXAMEN_DATA_BASE");
        //addDefaultUsersToDataBase();
        //Admi_db.deleteTable(Admi_db.ORQUIDEA_TABLE_NAME);
        //Admi_db.deleteTable(Admi_db.USERS_TABLE_NAME);
        //addDefaultOrquideasToDataBase();

    }


    private void addDefaultUsersToDataBase(){
        for(int i = 0; i < 6; i++){
            User newUser = new User("name"+String.valueOf(i),"pass"+String.valueOf(i));
            admi_db.insertDB(newUser, Admi_db.USERS_TABLE_NAME);
        }
    }

    private void addDefaultOrquideasToDataBase(){
        for(int i = 0; i < 6; i++){
            Orquidea newOrq = new Orquidea("nombre"+String.valueOf(i), "cantPet"+String.valueOf(i), "color"+String.valueOf(i), "lugar"+String.valueOf(i));
            admi_db.insertDB(newOrq, Admi_db.ORQUIDEA_TABLE_NAME);
        }
    }

    public void clickLog(View view) {

        TextView textViewUser = (TextView) findViewById(R.id.txtUser);
        String user = textViewUser.getText().toString();

        TextView textViewPass = (TextView) findViewById(R.id.txtPassword);
        String pass = textViewPass.getText().toString();



        if(admi_db.logRequest(user, pass)){
            Intent dashboard = new Intent(this, DashboardActivity.class);
            final int result = 1;
            startActivityForResult(dashboard,result);
        }
        else
            Toast.makeText(this,"Usiario no registrado",Toast.LENGTH_SHORT);
    }

    public void clickRegi(View view) {
        TextView textViewUser = (TextView) findViewById(R.id.txtUser);
        String user = textViewUser.getText().toString();

        TextView textViewPass = (TextView) findViewById(R.id.txtPassword);
        String pass = textViewPass.getText().toString();
        if(admi_db.logRequestUser(user)){
            Toast.makeText(this, "Este usuario ya esta registrado", Toast.LENGTH_SHORT).show();
        }
        else{
            User newUser = new User(user,pass);
            admi_db.insertDB(newUser, Admi_db.USERS_TABLE_NAME);
            Toast.makeText(this, "Se registro correctamente", Toast.LENGTH_SHORT).show();
        }
    }
}
