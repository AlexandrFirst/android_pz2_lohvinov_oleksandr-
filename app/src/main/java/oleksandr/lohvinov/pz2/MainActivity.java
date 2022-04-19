package oleksandr.lohvinov.pz2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar redColorSeekBar, greenColorSeekBar, blueColorSeekBar;
    TextView redColorValueText, greenColorValueText, blueColorValueText;
    int red, green, blue;

    LinearLayout colorDisplay;
    Drawable colorDisplayDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorDisplay = findViewById(R.id.colorDisplay);;

        redColorSeekBar = findViewById(R.id.redColorSeekBarId);
        greenColorSeekBar = findViewById(R.id.greenColorSeekBarId);
        blueColorSeekBar = findViewById(R.id.blueColorSeekBarId);

        redColorSeekBar.incrementProgressBy(1);
        greenColorSeekBar.incrementProgressBy(1);
        blueColorSeekBar.incrementProgressBy(1);


        redColorValueText = findViewById(R.id.redColorValueTextId);
        greenColorValueText = findViewById(R.id.greenColorValueTextId);
        blueColorValueText = findViewById(R.id.blueColorValueTextId);

        colorDisplayDrawable = colorDisplay.getBackground();

        SeekBar.OnSeekBarChangeListener colorSeekBarListener =
                new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                if (seekBar == redColorSeekBar) {
                    red = progress;
                    redColorValueText.setText("Red(0-255): " + progress);
                } else if (seekBar == greenColorSeekBar) {
                    green = progress;
                    greenColorValueText.setText("Green(0-255): " + progress);
                } else if (seekBar == blueColorSeekBar) {
                    blue = progress;
                    blueColorValueText.setText("Blue(0-255): " + progress);
                }

                colorDisplayDrawable.setColorFilter(Color.parseColor(
                        RGBToHex(red, green, blue)),
                        PorterDuff.Mode.SRC_ATOP);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        redColorSeekBar.setOnSeekBarChangeListener(colorSeekBarListener);
        greenColorSeekBar.setOnSeekBarChangeListener(colorSeekBarListener);
        blueColorSeekBar.setOnSeekBarChangeListener(colorSeekBarListener);

        if(savedInstanceState!=null){
            redColorSeekBar.
                    setProgress(savedInstanceState.getInt("red"));
            greenColorSeekBar.
                    setProgress(savedInstanceState.getInt("green"));
            blueColorSeekBar.
                    setProgress(savedInstanceState.getInt("blue"));
        }
        else{
            colorDisplayDrawable.
                    setColorFilter(Color.parseColor(
                            RGBToHex(0, 0, 0)),
                            PorterDuff.Mode.SRC_ATOP);
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("red", redColorSeekBar.getProgress());
        outState.putInt("green", greenColorSeekBar.getProgress());
        outState.putInt("blue", blueColorSeekBar.getProgress());
    }

    private String RGBToHex(int red, int green, int blue){
        String hex = String.format("#%02X%02X%02X", red, green, blue);
        return hex;
    }
}