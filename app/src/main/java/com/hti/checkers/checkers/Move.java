package com.hti.checkers.checkers;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;

/**
 * Created by Matthew on 6/12/2016.
 */
public class Move extends ImageButton {
    private int x, y, color;
    GridLayout gl;
    private Piece parent;

    public Move(final Context context, GridLayout gl, Piece parent, int color, int x, int y) {
        super(context);
        this.x = x;
        this.y = y;
        this.color = color;
        this.gl = gl;
        this.parent = parent;

        FrameLayout fl = (FrameLayout) gl.getChildAt(MainActivity.getIndex(x, y));
        ImageButton piece = new ImageButton(context);
        piece.setBackgroundResource(color);
        piece.setOnClickListener(new PieceOnClickListener(this, parent));
        fl.addView(piece);
    }

    private class PieceOnClickListener implements OnClickListener {
        private Move me;
        private Piece piece;

        public PieceOnClickListener(Move m, Piece p) {
            me = m;
            piece = p;
        }

        public void onClick(View v) {
            /*
            FrameLayout fl = (FrameLayout) me.getGl().getChildAt(MainActivity.getIndex(me.getPieceX(), me.getPieceY()));
            fl.removeAllViews();
            fl = (FrameLayout) piece.getGl().getChildAt(MainActivity.getIndex(piece.getPieceX(), piece.getPieceY()));
            fl.removeAllViews(); */
            piece.Remove();
            MainActivity.newPiece(piece.getColor() == R.drawable.red ? R.drawable.red : R.drawable.black, me.getPieceX(), me.getPieceY());
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

    public GridLayout getGl() {return gl;}
}
