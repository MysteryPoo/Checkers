package com.hti.checkers.checkers;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

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
        piece.setOnClickListener(new PieceOnClickListener(this));
        fl.addView(piece);
    }

    // Create movement objects to click on surrounding the piece in applicable locations.
    private class PieceOnClickListener implements OnClickListener {
        private Piece me;

        public PieceOnClickListener(Piece p) {
            me = p;
        }

        public void onClick(View v) {
            MainActivity.clearMoves();
            int left = me.x - 1;
            int up = me.y - 1;
            int right = me.x + 1;
            int down = me.y + 1;
            if (left >= 0) {
                if (color == R.drawable.red && up >= 0 && MainActivity.validPosition(left, up))
                    MainActivity.moves.add(new Move(v.getContext(), me.gl, me, color == R.drawable.red ? R.drawable.red_move : R.drawable.black_move, left, up));
                if (color == R.drawable.black && down < 8 && MainActivity.validPosition(left, down))
                    MainActivity.moves.add(new Move(v.getContext(), me.gl, me, color == R.drawable.red ? R.drawable.red_move : R.drawable.black_move, left, down));
            }
            if (right < 8) {
                if (color == R.drawable.red && up >= 0 && MainActivity.validPosition(right, up))
                    MainActivity.moves.add(new Move(v.getContext(), me.gl, me, color == R.drawable.red ? R.drawable.red_move : R.drawable.black_move, right, up));
                if (color == R.drawable.black && down < 8 && MainActivity.validPosition(right, down))
                    MainActivity.moves.add(new Move(v.getContext(), me.gl, me, color == R.drawable.red ? R.drawable.red_move : R.drawable.black_move, right, down));
            }
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
        MainActivity.pieces.remove(this);
    }

    public int getColor() {
        return color;
    }
}
