package atrue.pranesh.sampleretrofit2.ui.photos.business;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import atrue.pranesh.sampleretrofit2.MainActivity;
import atrue.pranesh.sampleretrofit2.R;
import atrue.pranesh.sampleretrofit2.model.Albums;
import atrue.pranesh.sampleretrofit2.model.Photos;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {
    PhotoHolder photoHolder;
    List<Photos> photosList;
    MainActivity mainActivity;
    public PhotoAdapter(List<Photos> albumsList, MainActivity activity) {
        this.photosList = albumsList;
        this.mainActivity=activity;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_adapter, parent, false);
        photoHolder = new PhotoHolder(view);
        return photoHolder;
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        photoHolder.onBind(photosList.get(position),mainActivity);
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }
}
