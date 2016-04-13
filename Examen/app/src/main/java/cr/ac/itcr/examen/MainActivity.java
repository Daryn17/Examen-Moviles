package cr.ac.itcr.examen;

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
    }


    private void addDefaultUsersToDataBase(){
        for(int i = 0; i < 6; i++){
            User newUser = new User("name"+String.valueOf(i),"pass"+String.valueOf(i));
            admi_db.insertDB(newUser, Admi_db.USERS_TABLE_NAME);
        }
    }

    public void clickLog(View view) {

        TextView textViewUser = (TextView) findViewById(R.id.txtUser);
        String user = textViewUser.getText().toString();

        TextView textViewPass = (TextView) findViewById(R.id.txtPassword);
        String pass = textViewPass.getText().toString();



        if(admi_db.logRequest(user, pass)){
            Toast t = Toast.makeText(this, user, Toast.LENGTH_SHORT);
            Toast y = Toast.makeText(this, pass,Toast.LENGTH_SHORT);
            t.show();
            y.show();
        }
        else
            Toast.makeText(this,"Usiario no registrado",Toast.LENGTH_SHORT);
    }
}
