package com.company.roomproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= findViewById(R.id.list1);


        button= findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,AddActivity.class);
                startActivity(i);
            }
        });

        new GetContacts().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selected_contact= (Contact) parent.getItemAtPosition(position);
                Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
                intent.putExtra("id", selected_contact.getId());
                startActivity(intent);
            }
        });

    }

        class GetContacts extends AsyncTask<Void, Void, List<Contact>> {

            @Override
            protected List<Contact> doInBackground(Void... voids) {
                DatabaseClient databaseClient = new DatabaseClient(getApplicationContext());


                List<Contact> contactList = databaseClient.getAppDatabase()
                        .databaseDao()
                        .getAll();
                return contactList;
            }

            @Override
            protected void onPostExecute(List<Contact> contacts) {
                super.onPostExecute(contacts);
                ContactAdapter adapter = new ContactAdapter(MainActivity.this, contacts);
                listView.setAdapter(adapter);
            }
        }

    @Override
    protected void onResume() {
        super.onResume();
               new GetContacts().execute();

    }
    }
