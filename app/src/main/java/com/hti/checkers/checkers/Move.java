package com.hti.checkers.checkers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Matthew on 6/12/2016.
 */
public class Move extends ImageButton {
    private int x, y, color;
    GridLayout gl;
    private Piece delete = null;

    public Move(final Context context, GridLayout gl, Piece parent, int color, int x, int y) {
        super(context);
        this.x = x;
        this.y = y;
        this.color = color;
        this.gl = gl;
        //this.parent = parent;

        FrameLayout fl = (FrameLayout) gl.getChildAt(MainActivity.getIndex(x, y));
        ImageButton piece = new ImageButton(context);
        piece.setBackgroundResource(color);
        piece.setOnClickListener(new PieceOnClickListener(this, parent));
        //piece.getBackground().setAlpha(0);
        fl.addView(piece);
    }

    public Move(final Context context, GridLayout gl, Piece parent, int color, int x, int y, Piece d) {
        this(context, gl, parent, color, x, y);
        delete = d;
    }

    private class PieceOnClickListener implements OnClickListener {
        private Move me;
        private Piece piece;

        public PieceOnClickListener(Move m, Piece p) {
            me = m;
            piece = p;
        }

        public void onClick(View v) {
            MainActivity.clearMoves();
            if(delete != null) {
                delete.Remove();
                if(piece.getColor() == R.drawable.black) {
                    MainActivity.addScore(true);
                    //Since there are 12 pieces per player, when one reaches 12 points the game is over
                    if(Integer.parseInt(MainActivity.BlackScore.getText().toString()) == 12)
                    {
                        Toast.makeText(getContext(), "Game Over. Winner is 'Black'", Toast.LENGTH_LONG).show();

                        Intent home = new Intent (getContext(), HomePage.class);
                        getContext().startActivity(home);
                    }
                } else {
                    MainActivity.addScore(false);
                    if(Integer.parseInt(MainActivity.RedScore.getText().toString()) == 12)
                    {
                        Toast.makeText(getContext(), "Game Over. Winner is 'Red'", Toast.LENGTH_LONG).show();
                        Intent home = new Intent (getContext(), HomePage.class);
                        getContext().startActivity(home);
                    }
                }
            }
            MainActivity.newPiece(me.color == R.drawable.red_move ? R.drawable.red : R.drawable.black, me.getPieceX(), me.getPieceY());
            piece.Remove();
        }
    }

    public void Remove() {
        FrameLayout fl = (FrameLayout) gl.getChildAt(MainActivity.getIndex(x, y));
        fl.removeAllViews();
    }

    public int getPieceX() {
        return x;
    }

    public int getPieceY() {
        return y;
    }
}
