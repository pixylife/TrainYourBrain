package lk.ac.iit.trainyourbrain.trainyourbrain;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import lk.ac.iit.trainyourbrain.trainyourbrain.model.GameData;


public class GameActivity extends AppCompatActivity {

    private int answer = 0;
    private int points=0;
    private int level=0;
     TextView txtAnswer= null;
     TextView txtMsg = null;
     TextView txtQuestion =null;
    CountDownTimer timer=null;
    GameData gameData;
    int time=0;


    int difficulty=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        gameData = (GameData) intent.getSerializableExtra("gameData");
        difficulty = gameData.getDifficulty();

        txtAnswer  = (TextView) findViewById(R.id.txtAnswer);
        txtMsg= (TextView) findViewById(R.id.textView12);
        txtQuestion= (TextView) findViewById(R.id.txtQuestion);

        newLevel();

        if(gameData.getQuestion()!=null){

        }






        final Button btnDel = (Button) findViewById(R.id.btnDel);
        btnDel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String value = txtAnswer.getText().toString();
                if (value != null && value.length() > 0) {
                    value = value.substring(0, value.length()-1);
                }
                txtAnswer.setText(value);
            }
        });


        final Button btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String value = txtAnswer.getText().toString();
                if (value != null && value.length() > 0 && ! (value.equals("?"))){
                    if(value.charAt(0)=='-'){
                        value = value.substring(1);

                    }else {
                        value = "-"+value;

                    }
                }
                txtAnswer.setText(value);
            }
        });
        final Button btnHash = (Button) findViewById(R.id.btnHash);
        btnHash.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String value = txtAnswer.getText().toString();

                    if(txtMsg.getText().equals("CORRECT!")) {
                        if (level != 10) {
                            newLevel();
                        }
                    }else {
                            int marks=0;
                        if (value != null && value.length() > 0 && !(value.equals("?")) && !(value.equals("-"))) {
                            if (value.equals(answer + "")) {
                                txtMsg.setText("CORRECT!");
                                txtMsg.setTextColor(Color.GREEN);
                                marks=100/(10-time);
                                points+=marks;
                                if (timer != null) {
                                    timer.cancel();
                                    timer = null;
                                }
                            } else {
                                txtMsg.setText("WRONG!");
                                txtMsg.setTextColor(Color.RED);

                            }
                        }
                    }

                    txtAnswer.setText(value);
                }
            });






    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            gameData.setAnswer(answer);
            gameData.setQuestion(txtQuestion.getText().toString());
            gameData.setLevel(level);
            gameData.setPoints(points);
            gameData.setTimeRemaining(time);

        }
        return super.onKeyDown(keyCode, event);
    }
    public void btnClick(View view) {
        Button b = (Button) view;
        final TextView txtAnswer = (TextView) findViewById(R.id.txtAnswer);
        txtAnswer.setText((txtAnswer.getText().equals("?")?b.getText().toString():txtAnswer.getText()+b.getText().toString()));
    }





    private String createQuestion(int difficulty){
            String question="";
            question=getRandNo()+"";
            answer=Integer.parseInt(question);
        int terms =difficulty;
        Random ran = new Random();

        if (terms==2){
           terms =  ran.nextInt(3-1)+1;
        }else if (terms==3){
            terms =  ran.nextInt(4-1)+1;
        }else if (terms==4){
            terms =  ran.nextInt(6-3)+3;
        }

        for (int i=0;i<terms;i++){
            String operator=getOperator();
            int value=getRandNo();
            question+=operator+""+value;

            switch (operator){
                case "+":
                    answer+=value;
                    break;
                case "-":
                    answer-=value;
                    break;
                case "*":
                    answer*=value;
                    break;
                case "/":
                    answer/=value;
                    break;
            }

        }
        return question;
    }
     private int getRandNo(){
         Random ran = new Random();
         return (ran.nextInt(10-1)+1);
     }

    private String getOperator(){
        Random ran = new Random();
        int x =  ran.nextInt(5-1)+1;
        String operator="";
        switch (x){
            case 1: operator =  "+";
            break;
            case 2 : operator =  "-";
            break;
            case 3 : operator =  "*";
            break;
            case 4 : operator =  "/";
            break;
        }

        return operator;

    }

    private void newLevel(){
        timeHandle();
        createQuestion(difficulty);
        level++;
        txtAnswer.setText("?");
        txtQuestion.setText(createQuestion(difficulty));
        txtMsg.setText("");

    }

private void timeHandle() {
  timer =  new CountDownTimer(10000, 1000) {

        final TextView timerView = (TextView) findViewById(R.id.timerView);


        public void onTick(long millisUntilFinished) {
            timerView.setText(millisUntilFinished / 1000+"");
            time=(int)millisUntilFinished / 1000;
        }

        public void onFinish() {
            newLevel();
        }

    }.start();
}
}
