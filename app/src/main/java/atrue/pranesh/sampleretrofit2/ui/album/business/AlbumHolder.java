package atrue.pranesh.sampleretrofit2.ui.album.business;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import atrue.pranesh.sampleretrofit2.R;
import atrue.pranesh.sampleretrofit2.model.Albums;

public class AlbumHolder extends RecyclerView.ViewHolder {
    TextView textView;
    FrameLayout frameLayout;
    View.OnClickListener listener;

    public AlbumHolder(View itemView, View.OnClickListener listener) {
        super(itemView);
        this.listener = listener;
        textView = itemView.findViewById(R.id.txtAlbum);
        frameLayout = itemView.findViewById(R.id.frmAlbum);
    }

    public void onBind(Albums albums, int position) {
        textView.setText(albums.title);
        frameLayout.setOnClickListener(listener);
        frameLayout.setTag(albums);
        frameLayout.setTag(R.id.frmAlbum,position);
    }
}
