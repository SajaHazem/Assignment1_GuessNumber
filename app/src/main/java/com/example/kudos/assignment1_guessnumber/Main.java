package com.example.kudos.assignment1_guessnumber;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main extends AppCompatActivity {

    int RandomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Generate Random number to guess
        RandomNumber = generateRandom();

        //Button listener
        // Same button is used to submit answer and restart the game after Winning!
        Button submit = (Button)findViewById(R.id.SubmentButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView result = (TextView) findViewById(R.id.result);
                if ( ! result.getText().equals("You Won,Congratulations!") ){
                    try{
                        getAnswer();
                    }catch(Exception e){
                        // catch Empty text ""
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context,"Enter Number! ", duration);
                        toast.show();
                    }
                }

                //when restart, generate new random number
                else{
                    RandomNumber = generateRandom();
                    EditText enteredNumber = (EditText)findViewById(R.id.Answer);
                    enteredNumber.setText("");
                    Button submit = (Button)findViewById(R.id.SubmentButton);
                    submit.setText("SUBMIT");
                    enteredNumber.setEnabled(true);
                    result.setText("");
                }
            }
        });


    }


    public int  generateRandom(){
        //generate random number between 0-1000
        Random random = new Random();
        return random.nextInt(1000);
    }

    public void getAnswer  () throws Exception{
//        TextView result = (TextView) findViewById(R.id.result);
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast;
        EditText enteredNumber = (EditText)findViewById(R.id.Answer);

        int answer = Integer.parseInt(enteredNumber.getText() + "");
        if (Math.abs(RandomNumber-answer) <= 5 && Math.abs(RandomNumber-answer)!= 0  ) {
            //generate toast contain result
            toast = Toast.makeText(context, answer + "so close ! ", duration);
            toast.show();
        }
        else if (RandomNumber < answer ) {
            toast = Toast.makeText(context, answer + " is Greater than the secret  number", duration);
            toast.show();
        }
        else if (RandomNumber > answer ) {
            toast = Toast.makeText(context, answer +  " is smaller than the secret number", duration);
            toast.show();
        }
        else {
            toast = Toast.makeText(context, "YOU WIN ^_^", duration);
            toast.show();
            TextView result = (TextView) findViewById(R.id.result);
            result.setText("You Won,Congratulations!");
            Button submit = (Button)findViewById(R.id.SubmentButton);
            submit.setText("RESTART");
            enteredNumber.setEnabled(false);

        }
        enteredNumber.setText("");
    }
}
