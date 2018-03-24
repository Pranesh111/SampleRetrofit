package atrue.pranesh.sampleretrofit2.ui.photos.business;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import atrue.pranesh.sampleretrofit2.MainActivity;
import atrue.pranesh.sampleretrofit2.R;
import atrue.pranesh.sampleretrofit2.model.Albums;
import atrue.pranesh.sampleretrofit2.model.Photos;

public class PhotoHolder extends RecyclerView.ViewHolder {

    TextView photoTitle;
    ImageView imgThumb, imgImageUrl;

    public PhotoHolder(View itemView) {
        super(itemView);
        photoTitle = itemView.findViewById(R.id.photoTitle);
        imgThumb = itemView.findViewById(R.id.imgThumb);
        imgImageUrl = itemView.findViewById(R.id.imgImageUrl);
    }

    public void onBind(Photos albums, MainActivity mainActivity) {
        photoTitle.setText(albums.title);
        Glide.with(mainActivity).load(albums.thumbnailUrl).into(imgThumb);
        Glide.with(mainActivity).load(albums.url).into(imgImageUrl);
    }
}
