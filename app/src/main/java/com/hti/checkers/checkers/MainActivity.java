package com.hti.checkers.checkers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBoard();

        newPiece(R.drawable.black, 1, 0);
        newPiece(R.drawable.red, 5, 3);
    }

    private void newPiece(int color, int x, int y) {
        GridLayout gl = (GridLayout)findViewById(R.id.gridLayout);
        FrameLayout fl = (FrameLayout)gl.getChildAt(getIndex(x, y));
        ImageButton piece = new ImageButton(this);
        piece.setBackgroundResource(color);
        fl.addView(piece);
    }

    private int getIndex(int x, int y) {
        return x * 8 + y;
    }

    private void setupBoard() {
        GridLayout gl = (GridLayout)findViewById(R.id.gridLayout);
        for(int x = 0; x < 8; ++x) {
            for(int y = 0; y < 8; ++y) {
                GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
                lp.width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 52, getResources().getDisplayMetrics());
                lp.height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 54, getResources().getDisplayMetrics());
                lp.setGravity(Gravity.FILL);
                lp.columnSpec = GridLayout.spec(x);
                lp.rowSpec = GridLayout.spec(y);

                FrameLayout fl = new FrameLayout(this);
                fl.setLayoutParams(lp);

                gl.addView(fl);
            }
        }
    }
}
