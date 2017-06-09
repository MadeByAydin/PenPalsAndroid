package edu.udel.iyannamm.penpals;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

import static edu.udel.iyannamm.penpals.UserModel.signature;

public class Chatroom extends AppCompatActivity {
    private LinearLayout layout;
    private ScrollView scrollView;
    private EditText message_space;
    private Firebase reff_one;
    private Firebase reff_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        layout = (LinearLayout) findViewById(R.id.layout1);
        ImageView send_button = (ImageView) findViewById(R.id.send_button);
        message_space = (EditText) findViewById(R.id.message_space);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        Firebase.setAndroidContext(this);
        reff_one = new Firebase("https://penpals-3cd80.firebaseio.com/messages/" + UserModel.username + "_" + UserModel.connectconvo);
        reff_two = new Firebase("https://penpals-3cd80.firebaseio.com/messages/" + UserModel.connectconvo + "_" + UserModel.username);

        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_message = message_space.getText().toString();

                // if the message is not blank, push it to firebase with the username of the user that sent it
                if (!text_message.equals("")) {
                    Map<String, Object> map_one = new HashMap<String, Object>();
                    map_one.put("Message", text_message);
                    map_one.put("Username", UserModel.username);
                    reff_one.push().setValue(map_one);
                    reff_two.push().setValue(map_one);
                }
            }
        });

        reff_one.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map_one = dataSnapshot.getValue(Map.class);
                String text = map_one.get("Message").toString();
                String user = map_one.get("Username").toString();

                // if the current user using the app sends the message, this is the format for the text
                if (user.equals(UserModel.username)) {
                    message_bubble("Me: \n" + text + "\n\nSignature: " + UserModel.signature, 1);
                }
                // if the message is coming from another user, this is the format for the text
                else {
                    message_bubble(UserModel.connectconvo + ": \n" + text + "\n\nFrom: " + Registration.cntry, 2);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    public void message_bubble(String text, int type) {
         TextView view = new TextView(Chatroom.this);

        view.setText(text);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,0,0,10);
        view.setLayoutParams(layoutParams);

        if(type == 1){
            view.setBackgroundResource(R.drawable.first);

        }
        else{
            view.setBackgroundResource(R.drawable.second);
        }
        layout.addView(view);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}
