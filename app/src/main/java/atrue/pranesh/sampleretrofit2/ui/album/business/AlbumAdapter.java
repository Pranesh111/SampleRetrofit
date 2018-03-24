package atrue.pranesh.sampleretrofit2.ui.album.business;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import atrue.pranesh.sampleretrofit2.R;
import atrue.pranesh.sampleretrofit2.model.Albums;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumHolder> {
    AlbumHolder albumHolder;
    List<Albums> albumsList;
    View.OnClickListener listener;
    public AlbumAdapter(List<Albums> albumsList,View.OnClickListener listener){
        this.albumsList=albumsList;
        this.listener=listener;
    }
    @Override
    public AlbumHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.album_adapter,parent,false);
        albumHolder=new AlbumHolder(view,listener);
        return albumHolder;
    }

    @Override
    public void onBindViewHolder(AlbumHolder holder, int position) {
        albumHolder.onBind(albumsList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return albumsList.size();
    }
}
