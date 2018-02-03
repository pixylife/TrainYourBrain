package lk.ac.iit.trainyourbrain.trainyourbrain;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lk.ac.iit.trainyourbrain.trainyourbrain.model.GameData;

public class MainActivity extends AppCompatActivity {
     GameData gameData = new GameData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button btNewGame = (Button) findViewById(R.id.newgame);
        btNewGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),DifficultyActivity.class);
                intent.putExtra("gameData", gameData);
                startActivity(intent);

            }
        });

        final Button btAboutGame = (Button) findViewById(R.id.about);
        btAboutGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),PopUpActivity.class);
                startActivity(intent);

            }
        });

        final Button btcontGame = (Button) findViewById(R.id.conti);
        btcontGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               try {

                   FileInputStream fis = openFileInput("GameData");
                   ObjectInputStream is = new ObjectInputStream(fis);
                   gameData = (GameData) is.readObject();
                   is.close();
                   fis.close();
                   Intent intent = new Intent(v.getContext(), GameActivity.class);
                   intent.putExtra("gameData", gameData);
                   startActivity(intent);

               }catch (Exception e){
                   e.printStackTrace();

               }

            }
        });





    final Button btExitGame = (Button) findViewById(R.id.exit);
    btExitGame.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }
    });    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    try {
                        FileOutputStream fos = openFileOutput("GameData", MODE_PRIVATE);
                        ObjectOutputStream os = new ObjectOutputStream(fos);
                        os.writeObject(gameData);
                        os.close();
                        fos.close();
                        System.exit(0);

                    }catch (Exception e){
                        e.printStackTrace();

                    }
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    System.exit(0);
                    break;
            }
        }
    };


}
