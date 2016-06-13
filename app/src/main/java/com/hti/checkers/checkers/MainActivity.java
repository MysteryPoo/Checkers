package com.hti.checkers.checkers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static GridLayout gl;
    static List<Piece> pieces = new ArrayList<>();
    static List<Move> moves = new ArrayList<>();
    static Context c;

    static TextView playerScore, cpuScore;

    static void clearMoves() {
        for(Move m : moves) {
            m.Remove();
        }
        moves.clear();
    }

    static boolean validPosition(int x, int y) {
        FrameLayout fl = (FrameLayout) gl.getChildAt(getIndex(x, y));
        return fl.getChildCount() == 0;
    }

    static int getColor(int x, int y) {
        Piece p = getPiece(x, y);
        return p == null ? -1 : p.getColor();
    }

    static Piece getPiece(int x, int y) {
        for(Piece p : pieces) {
            if(p.getPieceX() == x && p.getPieceY() == y) {
                return p;
            }
        }
        return null;
    }

    static void removePiece(int x, int y) {
        Piece p = getPiece(x, y);
        if(p != null) {
            pieces.remove(p);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Checkers!!!");
        actionBar.setDisplayShowHomeEnabled(true);

        playerScore = (TextView) findViewById(R.id.PlayerScoreTv);
        cpuScore = (TextView) findViewById(R.id.CPUScoreTV);

        c = this;
        gl = (GridLayout) findViewById(R.id.gridLayout);

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

    static void newPiece(int color, int x, int y) {
        pieces.add(new Piece(c, gl, color, x, y));
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

    static void addScore(boolean player) {
        if(player) {
            int score = Integer.parseInt(playerScore.getText().toString()) + 1;
            playerScore.setText(String.valueOf(score));
        } else {
            int score = Integer.parseInt(cpuScore.getText().toString()) + 1;
            cpuScore.setText(String.valueOf(score));
        }
    }
}