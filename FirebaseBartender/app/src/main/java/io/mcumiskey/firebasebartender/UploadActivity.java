package io.mcumiskey.firebasebartender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadActivity extends AppCompatActivity {
    Button saveButton;
    EditText uploadName, uploadIngredients, uploadDirections;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        //reference ui
        uploadIngredients = findViewById(R.id.uploadIngredients);
        uploadName = findViewById(R.id.uploadName);
        uploadDirections = findViewById(R.id.uploadDirections);
        saveButton = findViewById(R.id.saveButton);

        //get data
        database = FirebaseDatabase.getInstance().getReference();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData();
                Intent intent = new Intent(UploadActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void InsertData() {
        String name = uploadName.getText().toString();
        String ingredients = uploadIngredients.getText().toString();
        String steps = uploadDirections.getText().toString();


        String id = database.push().getKey();

        Cocktail cocktail = new Cocktail(name, ingredients, steps);
        //add the data to the online database
        database.child("cocktails").child(id).setValue(cocktail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //check if the task (adding to database) is finished!
                        if (task.isSuccessful()) {
                            Toast.makeText(UploadActivity.this, "Data added!", Toast.LENGTH_SHORT);
                        }
                    }
                });
    }
}
