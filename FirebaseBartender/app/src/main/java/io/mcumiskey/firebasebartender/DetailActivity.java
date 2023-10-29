package io.mcumiskey.firebasebartender;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivity extends AppCompatActivity {
    TextView detailDirections, detailName, detailIngredients;
    FloatingActionButton deleteButton, editButton;
    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailName = findViewById(R.id.detailTitle);
        detailIngredients = findViewById(R.id.detailIngredients);
        detailDirections = findViewById(R.id.detailDirections);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);

        //get intent bundles
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailName.setText(bundle.getString("name"));
            detailIngredients.setText(bundle.getString("ingredients"));
            detailDirections.setText(bundle.getString("steps"));
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("cocktails");

            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, UpdateActivity.class)
                        .putExtra("name", detailName.getText().toString())
                        .putExtra("ingredients", detailDirections.getText().toString())
                        .putExtra("steps", detailIngredients.getText().toString());
                startActivity(intent);
            }
        });
    }
}