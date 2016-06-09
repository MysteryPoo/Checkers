package com.hti.checkers.checkers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static List<Piece> pieces = new ArrayList<>();

    static boolean validPosition(int x, int y) {
        boolean ret = true;
        for(Piece p : pieces) {
            if(p.getPieceX() == x && p.getPieceY() == y) {
                ret = false;
            }
        }
        return ret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBoard();

        // Black pieces
        for (int x = 0; x < 8; x += 2) {
            for (int y = 0; y < 3; y += 2) {
                newPiece(R.drawable.black, x, y);
            }
        }
        for (int x = 1; x < 8; x += 2) {
            newPiece(R.drawable.black, x, 1);
        }
        // Red pieces
        for (int x = 1; x < 8; x += 2) {
            for (int y = 5; y < 8; y += 2) {
                newPiece(R.drawable.red, x, y);
            }
        }
        for (int x = 0; x < 8; x += 2) {
            newPiece(R.drawable.red, x, 6);
        }
    }

    private void newPiece(int color, int x, int y) {
        GridLayout gl = (GridLayout) findViewById(R.id.gridLayout);
        pieces.add(new Piece(this, gl, color, x, y));
    }

    static int getIndex(int x, int y) {
        return x * 8 + y;
    }

    private void setupBoard() {
        GridLayout gl = (GridLayout) findViewById(R.id.gridLayout);
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
                lp.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 52, getResources().getDisplayMetrics());
                lp.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 54, getResources().getDisplayMetrics());
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