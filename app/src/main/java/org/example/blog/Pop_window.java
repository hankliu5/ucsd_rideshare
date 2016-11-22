package org.example.blog;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Alan Beas on 11/13/2016.
 */

public class Pop_window extends Activity {
    final String TAG = "User Profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detailed_post);

        Bundle bun = this.getIntent().getExtras();
        String uid = bun.getString("uid");

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User user = dataSnapshot.getValue(User.class);
                ((TextView)findViewById(R.id.post_username)).setText(user.getUsername());
                ((TextView)findViewById(R.id.post_phone_num)).setText(user.getStringPhoneNumber());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        userRef.addValueEventListener(postListener);

        ((TextView)findViewById(R.id.post_departure)).setText(bun.getString("start"));
        ((TextView)findViewById(R.id.post_destination)).setText(bun.getString("dest"));
        ((TextView)findViewById(R.id.post_price)).setText(bun.getString("price"));
        ((TextView)findViewById(R.id.post_additional)).setText(bun.getString("addit"));
        ((TextView)findViewById(R.id.post_date)).setText(bun.getString("date"));

    }

    public void goBack(View view){
        super.onBackPressed();
    }

    public String stringMonth(int m){
        switch (m){
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Oct";
            case 9:
                return "Aug";
            case 10:
                return "Sep";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
        }
        return "";
    }
}
