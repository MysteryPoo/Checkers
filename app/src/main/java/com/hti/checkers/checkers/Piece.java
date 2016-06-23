package com.hti.checkers.checkers;

import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;

/**
 * Created by Matthew on 6/9/2016.
 */
public class Piece extends ImageButton {

    private int x, y, color;
    GridLayout gl;

    public Piece(final Context context, GridLayout gl, int color, int x, int y) {
        super(context);
        this.x = x;
        this.y = y;
        this.color = color;
        this.gl = gl;

        FrameLayout fl = (FrameLayout) gl.getChildAt(MainActivity.getIndex(x, y));
        ImageButton piece = new ImageButton(context);
        piece.setBackgroundResource(color);
        piece.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.clearMoves();

                for(int i = -1; i <= 1; ++i) {
                    for(int j = -1; j <= 1; ++j) {
                        if(i == 0 && j == 0)
                            continue;
                        validMove(v, new Point(i, j));
                    }
                }
            }
        });
        fl.addView(piece);
    }

    private void validMove(View v, Point direction) {
        int newX = this.x + direction.x;
        int newY = this.y + direction.y;
        if(MainActivity.emptyLocation(newX, newY)) {
            MainActivity.moves.add(new Move(v.getContext(), gl, this, color == R.drawable.red ? R.drawable.red_move : R.drawable.black_move, newX, newY));
        } else if(MainActivity.emptyLocation(newX + direction.x, newY + direction.y)) {
            MainActivity.moves.add(new Move(v.getContext(), gl, this, color == R.drawable.red ? R.drawable.red_move : R.drawable.black_move,
                    newX + direction.x, newY + direction.y,
                    MainActivity.getPiece(newX, newY)));
        }
    }

    public int getPieceX() {
        return x;
    }

    public int getPieceY() {
        return y;
    }

    public void Remove() {
        FrameLayout fl = (FrameLayout) gl.getChildAt(MainActivity.getIndex(x, y));
        fl.removeAllViews();
        MainActivity.removePiece(x, y);
    }

    public int getColor() {
        return color;
    }
}
