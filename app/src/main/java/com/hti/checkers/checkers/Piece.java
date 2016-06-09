package com.hti.checkers.checkers;

import android.content.Context;
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
        piece.setOnClickListener(new PieceOnClickListener(this));
        fl.addView(piece);
    }

    // Create movement objects to click on surrounding the piece in applicable locations.
    private class PieceOnClickListener implements OnClickListener {
        private Piece parent;

        public PieceOnClickListener(Piece p) {
            parent = p;
        }

        public void onClick(View v) {
            int left = parent.x - 1;
            int up = parent.y - 1;
            int right = parent.x + 1;
            int down = parent.y + 1;
            if (left >= 0) {
                if (up >= 0 && MainActivity.validPosition(left, up))
                    MainActivity.pieces.add(new Piece(v.getContext(), parent.gl, R.drawable.red, left, up));
                if (down < 8 && MainActivity.validPosition(left, down))
                    MainActivity.pieces.add(new Piece(v.getContext(), parent.gl, R.drawable.red, left, down));
            }
            if (right < 8) {
                if (up >= 0 && MainActivity.validPosition(right, up))
                    MainActivity.pieces.add(new Piece(v.getContext(), parent.gl, R.drawable.red, right, up));
                if (down < 8 && MainActivity.validPosition(right, down))
                    MainActivity.pieces.add(new Piece(v.getContext(), parent.gl, R.drawable.red, right, down));
            }
        }
    }

    public int getPieceX() {
        return x;
    }

    public int getPieceY() {
        return y;
    }
}
