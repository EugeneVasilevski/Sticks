package com.lis.sticks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lis.tictactoe.R;

public class Options extends AppCompatActivity
        implements
        SeekBar.OnSeekBarChangeListener {

    private final int NUM_OF_FIELD_SIZE = 5;

    private int fieldSize = 3;
    private int color = 1;

    private boolean isSquare = true;

    private TextView textView;
    private SeekBar seekBar;
    private CheckBox bot;
    private RadioGroup radioGroup;
    private RadioGroup fieldStyle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        getSupportActionBar().hide();

        textView = (TextView) findViewById(R.id.textView);

        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(this);

        bot = (CheckBox) findViewById(R.id.bot);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(radioButtonClickListener);

        fieldStyle = (RadioGroup) findViewById(R.id.field_style);
        fieldStyle.setOnCheckedChangeListener(fieldStyleClickListener);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        fieldSize = (int) Math.round(seekBar.getProgress() / (100.0 / (NUM_OF_FIELD_SIZE - 1)));
        fieldSize = 3 + fieldSize * 2;
        textView.setText(Integer.toString(fieldSize) + "x" + (Integer.toString(fieldSize)));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void start(View view) {
        Intent intent = new Intent(Options.this, MainActivity.class);

        intent.putExtra("fieldSize", fieldSize);
        intent.putExtra("square", isSquare);
        intent.putExtra("vsBot", bot.isChecked());
        intent.putExtra("color", color);

        startActivity(intent);
    }

    RadioGroup.OnCheckedChangeListener radioButtonClickListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radio_blue:
                    color = 1;
                    break;
                case R.id.radio_purple:
                    color = 2;
                    break;
                default:
                    break;
            }
        }
    };

    RadioGroup.OnCheckedChangeListener fieldStyleClickListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.square:
                    isSquare = true;
                    break;
                case R.id.rhombus:
                    isSquare = false;
                    break;
                default:
                    break;
            }
        }
    };
}
