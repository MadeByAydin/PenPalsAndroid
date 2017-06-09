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

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity {
    private TextView register;
    private EditText username;
    private EditText password;
    private Button l_button;
    private String user;
    private String key;
    private String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setLogo(R.drawable.cute);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_login);

        register = (TextView)findViewById(R.id.register);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        l_button = (Button)findViewById(R.id.l_button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registration.class));
            }
        });

        l_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user= username.getText().toString();
                key = password.getText().toString();

                if (user.equals("")){
                    username.setError("Username can not be blank!");
                }
                else if (key.equals("")){
                    password.setError("Password can not be blank!");
                }
                else{
                    String url = "https://penpals-3cd80.firebaseio.com/user.json";

                    final ProgressDialog progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMessage("Connecting to World...");
                    progressDialog.show();

                    // handles the input of the user into the login screen
                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("null")){
                                Toast.makeText(Login.this, "User unknown!", Toast.LENGTH_LONG).show();
                            }
                            else{
                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    if(!jsonObject.has(user)){
                                        Toast.makeText(Login.this, "User unknown!", Toast.LENGTH_LONG).show();
                                    }
                                    else if(jsonObject.getJSONObject(user).getString("password").equals(key)){
                                        UserModel.username = user;
                                        UserModel.password = key;
                                        startActivity(new Intent(Login.this, User.class));
                                    }
                                    else {
                                        Toast.makeText(Login.this, "Password Incorrect!", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException m) {
                                    m.printStackTrace();
                                }
                            }
                            progressDialog.dismiss();
                        }
                    }, new Response.ErrorListener(){
                        public void onErrorResponse(VolleyError v){
                            System.out.println("" + v);
                            progressDialog.dismiss();
                        }
                    });
                    // taken from tutorial about how to use volley library
                    RequestQueue queue = Volley.newRequestQueue(Login.this);
                    queue.add(request);
                }
            }

        });
    }
}
