package fr.supinternet.slike;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by SYLVAIN on 26/09/2017.
 */

public class DFragment extends DialogFragment {

    private DatabaseReference mUsers;
    private DatabaseReference mMessages;

    private FirebaseAuth mAuth;

    private FirebaseUser current_user;

    private EditText message;


    private String name;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //GET user
        mUsers = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        current_user = mAuth.getCurrentUser();

        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                System.out.println("user" + current_user);
                name = dataSnapshot.child("users").child(current_user.getUid()).child("name").getValue(String.class);
                System.out.println("user" + name);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

            }

        };

        mUsers.addListenerForSingleValueEvent(postListener);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Nouveau message")
                .setView(getActivity().getLayoutInflater().inflate(R.layout.dialogfragment, null))
                .setPositiveButton(R.string.alert_dialog_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                mMessages = FirebaseDatabase.getInstance().getReference();
                                message = (EditText) getDialog().findViewById(R.id.message);

                                Message messageToSend = new Message(message.getText().toString(), name);

                                mMessages.child("messages").push().setValue(messageToSend);


                            }
                        }
                )
                .setNegativeButton(R.string.alert_dialog_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DFragment.this.getDialog().cancel();
                            }
                        }
                )
                .create();
    }


}
