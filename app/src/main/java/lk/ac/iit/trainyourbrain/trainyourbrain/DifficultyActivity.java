package lk.ac.iit.trainyourbrain.trainyourbrain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lk.ac.iit.trainyourbrain.trainyourbrain.model.GameData;

public class DifficultyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);


        Intent intent = getIntent();
        final GameData gameData = (GameData) intent.getSerializableExtra("gameData");

        final Button btNiviceGame = (Button) findViewById(R.id.nivice);
        btNiviceGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),GameActivity.class);
                gameData.setDifficulty(1);
                intent.putExtra("gameData", gameData);
                startActivity(intent);

            }
        });

        final Button btEasyGame = (Button) findViewById(R.id.easy);
        btEasyGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),GameActivity.class);
                gameData.setDifficulty(2);
                intent.putExtra("gameData", gameData);
                startActivity(intent);

            }
        });

        final Button btMediumGame = (Button) findViewById(R.id.medium);
        btMediumGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),GameActivity.class);
                gameData.setDifficulty(3);
                intent.putExtra("gameData", gameData);
                startActivity(intent);

            }
        });
        final Button btGuruGame = (Button) findViewById(R.id.guru);
        btGuruGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),GameActivity.class);
                gameData.setDifficulty(4);
                intent.putExtra("gameData", gameData);
                startActivity(intent);

            }
        });

    }
}
