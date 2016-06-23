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
            // If left of the piece is on the board
            if (left >= 0) {
                // Red Piece
                // Check if Up & Left is empty
                if (color == R.drawable.red && up >= 0 && MainActivity.validPosition(left, up))
                    MainActivity.moves.add(new Move(v.getContext(), me.gl, me, color == R.drawable.red ? R.drawable.red_move : R.drawable.black_move, left, up));
                // It's not empty, see if it's occupied by the opponent and see if the jump space is empty
                else if (color == R.drawable.red && up - 1 >= 0 && left - 1 >= 0
                        && MainActivity.validPosition(left - 1, up - 1)
                        && MainActivity.getColor(left, up) == R.drawable.black)
                    MainActivity.moves.add(new Move(v.getContext(), me.gl, me, color == R.drawable.red ? R.drawable.red_move : R.drawable.black_move, left - 1, up - 1, MainActivity.getPiece(left, up)));
                // Black Piece
                // Check if Down & Left is empty
                if (color == R.drawable.black && down < 8 && MainActivity.validPosition(left, down))
                    MainActivity.moves.add(new Move(v.getContext(), me.gl, me, color == R.drawable.red ? R.drawable.red_move : R.drawable.black_move, left, down));
                // It's not empty, see if it's occupied by the opponent and see fi the jump space is empty
                else if (color == R.drawable.black && down < 8 && down + 1 < 8 && left - 1 >= 0
                        && MainActivity.validPosition(left - 1, down + 1)
                        && MainActivity.getColor(left, down) == R.drawable.red)
                    MainActivity.moves.add(new Move(v.getContext(), me.gl, me, color == R.drawable.red ? R.drawable.red_move : R.drawable.black_move, left - 1, down + 1, MainActivity.getPiece(left, down)));
            }
            // If right of the piece is on the board
            if (right < 8) {
                // Red Piece
                // Check if Up & Right is empty
                if (color == R.drawable.red && up >= 0 && MainActivity.validPosition(right, up))
                    MainActivity.moves.add(new Move(v.getContext(), me.gl, me, color == R.drawable.red ? R.drawable.red_move : R.drawable.black_move, right, up));
                // It's not empty, see if it's occupied by the opponent and see fi the jump space is empty
                else if (color == R.drawable.red && up >= 0 && up - 1 >= 0 && right + 1 < 8
                        && MainActivity.validPosition(right + 1, up - 1)
                        && MainActivity.getColor(right, up) == R.drawable.black)
                    MainActivity.moves.add(new Move(v.getContext(), me.gl, me, color == R.drawable.red ? R.drawable.red_move : R.drawable.black_move, right + 1, up - 1, MainActivity.getPiece(right, up)));
                // Check if Down & Right is empty
                if (color == R.drawable.black && down < 8 && MainActivity.validPosition(right, down))
                    MainActivity.moves.add(new Move(v.getContext(), me.gl, me, color == R.drawable.red ? R.drawable.red_move : R.drawable.black_move, right, down));
                // It's not empty, see if it's occupied by the opponent and see fi the jump space is empty
                else if (color == R.drawable.black && down < 8 && down + 1 < 8 && right + 1 < 8
                        && MainActivity.validPosition(right + 1, down + 1)
                        && MainActivity.getColor(right, down) == R.drawable.red)
                    MainActivity.moves.add(new Move(v.getContext(), me.gl, me, color == R.drawable.red ? R.drawable.red_move : R.drawable.black_move, right + 1, down + 1, MainActivity.getPiece(right, down)));
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
        MainActivity.removePiece(x, y);
    }

    public int getColor() {
        return color;
    }
}
