package edu.udel.iyannamm.penpals;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

public class Registration extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button register;
    static String user;
    private String key;
    private TextView login;
    private EditText country;
    static String cntry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        country = (EditText)findViewById(R.id.country);
        username = (EditText)findViewById(R.id.username);
        password= (EditText)findViewById(R.id.password);
        register = (Button)findViewById(R.id.register);
        login = (TextView)findViewById(R.id.login);

        Firebase.setAndroidContext(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, Login.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                key= password.getText().toString();
                cntry = country.getText().toString();

                if(user.equals("")){
                    username.setError("Can not be blank!");
                }
                else if (key.equals("")){
                    password.setError("Can not be blank!");
                }
                else if(user.length() < 3){
                    username.setError("At least 3 characters long!");
                }
                else if (key.length()< 3){
                    password.setError("At least 3 characters long!");
                }
                else if (cntry.equals("")){
                    country.setError("Can not be blank!");
                }
                else{
                    final ProgressDialog progressDialog = new ProgressDialog(Registration.this);
                    progressDialog.setMessage("Connecting to the World...");
                    progressDialog.show();
                    String url = "https://penpals-3cd80.firebaseio.com/user.json";

                    StringRequest r = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            Firebase reference = new Firebase("https://penpals-3cd80.firebaseio.com/user");

                            if(s.equals("null")) {
                                reference.child(user).child("password").setValue(key);
                                reference.child(user).child("country").setValue(cntry);
                                UserModel.country = cntry;
                                Toast.makeText(Registration.this, "Welcome to Penpals!", Toast.LENGTH_LONG).show();
                            }
                            else {
                                try {
                                    JSONObject object = new JSONObject(s);

                                    if (!object.has(user)) {
                                        reference.child(user).child("password").setValue(key);
                                        reference.child(user).child("country").setValue(cntry);
                                        UserModel.country = cntry;
                                        Toast.makeText(Registration.this, "Welcome to Penpals!", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(Registration.this, "Username unoriginal", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException m) {
                                    m.printStackTrace();
                                }
                            }

                            progressDialog.dismiss();
                        }

                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.print("" + volleyError);
                            progressDialog.dismiss();
                        }
                    });

                    // taken from tutorial about using volley library
                    RequestQueue queue = Volley.newRequestQueue(Registration.this);
                    queue.add(r);
                }
            }
        });

    }
}
