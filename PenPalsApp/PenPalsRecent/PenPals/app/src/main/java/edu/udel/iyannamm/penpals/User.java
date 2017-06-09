package edu.udel.iyannamm.penpals;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class User extends AppCompatActivity {
    private ListView online_users;
    private ProgressDialog progressDialog;
    private int current_users;
    static ArrayList<String> chatters = new ArrayList<>();
    private Button startQuiz;
    private Button addSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        online_users = (ListView) findViewById(R.id.online_users);
        progressDialog = new ProgressDialog(User.this);
        progressDialog.setMessage("Connecting to World...");
        progressDialog.show();
        startQuiz = (Button)findViewById(R.id.quiz);
        addSignature = (Button)findViewById(R.id.addSignature);

        String url = "https://penpals-3cd80.firebaseio.com/user.json";
        StringRequest r = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print("" + error);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(User.this);
        requestQueue.add(r);

        online_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserModel.connectconvo = chatters.get(position);
                startActivity(new Intent(User.this, Chatroom.class));
            }
        });

        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User.this, Quiz.class));
            }
        });

        addSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User.this, AddSignature.class);
                startActivity(intent);
            }
        });

    }


        public void onSuccess(String m){
                try {
                    JSONObject obj = new JSONObject(m);

                    Iterator i = obj.keys();
                    String key = "";

                    while(i.hasNext()){
                        key = i.next().toString();

                        // populates the list of users that can be chatted with
                        if(!key.equals(UserModel.username) && !key.equals(UserModel.country)) {
                            chatters.add(key);
                        }

                        current_users++;
                    }

                } catch (JSONException b) {
                    b.printStackTrace();
                }

                if(current_users <=1){

                    online_users.setVisibility(View.GONE);
                }
                else {
                        online_users.setVisibility(View.VISIBLE);
                        online_users.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chatters));
                    }

            progressDialog.dismiss();
            }

        }
