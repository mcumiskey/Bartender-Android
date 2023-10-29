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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity {
    Button updateButton, deleteButton;
    EditText updateIngredients, updateTitle, updateSteps;
    String name, ingredients, steps;
    String key;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        updateIngredients = findViewById(R.id.updateIngredients);
        updateSteps = findViewById(R.id.updateDirections);
        updateTitle = findViewById(R.id.updateName);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            updateTitle.setText(bundle.getString("name"));
            updateIngredients.setText(bundle.getString("ingredients"));
            updateSteps.setText(bundle.getString("steps"));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("cocktails");

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cocktail cocktail = new Cocktail(bundle.getString("name"), bundle.getString("ingredients"), bundle.getString("steps"));

                databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>(){
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(UpdateActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void saveData(){
        name = updateTitle.getText().toString().trim();
        ingredients = updateIngredients.getText().toString().trim();
        steps = updateSteps.getText().toString();

        Cocktail cocktail = new Cocktail(name, ingredients, steps);

        databaseReference.setValue(cocktail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}