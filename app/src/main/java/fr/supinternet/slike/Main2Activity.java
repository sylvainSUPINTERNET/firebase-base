package fr.supinternet.slike;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.support.v4.app.FragmentManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    Button dfragbutton;
    EditText message;
    String message_val;

    Adapter adapter;
    RecyclerView rvList;

    //FIREBASE AUTH
    private FirebaseAuth mAuth;

    //DB
    private DatabaseReference mDatabase;


    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        message = (EditText) findViewById(R.id.message);

        //FIREBASE Auth
        mAuth = FirebaseAuth.getInstance();


        //DATABASE
        mDatabase = FirebaseDatabase.getInstance().getReference();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                DFragment dFragment = new DFragment();
                dFragment.show(fm, "test");
            }
        });



        //TO DO => constructor de Adapter ajouter la list des message et ici l'envoyer pour initilisaer la list
        rvList = (RecyclerView) findViewById(R.id.rvMessage);
        mLayoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(mLayoutManager);



        //test
        final ArrayList<Message> mMessageList = new ArrayList<>();
        mMessageList.add(0,new Message("sylvain", "f de base"));
        mMessageList.add(1,new Message("sylvain", "d de eazeazeae"));
        mMessageList.add(2,new Message("sylvain", "p de ezaeaeaeaeae"));



        adapter = new Adapter(Main2Activity.this, /*test*/mMessageList );
        rvList.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d("CHILD ADDED", dataSnapshot.getKey());
                Message messageToDisplay = dataSnapshot.child("messages").getValue(Message.class);
                adapter.addMessage(messageToDisplay);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d("Child Event,Verse", "onChildChanged:" + dataSnapshot.getKey());
                Log.d("Child Event,Verse", "onChildChanged:" + dataSnapshot.getValue()); //full list des messages
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Message messageToDelete = dataSnapshot.child("messages").getValue(Message.class);
                adapter.removeMessage(messageToDelete);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Firebase", "onCancelled", databaseError.toException());
            }
        };

        mDatabase.addChildEventListener(childEventListener);


    }


}
