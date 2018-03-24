package atrue.pranesh.sampleretrofit2.ui.album;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import atrue.pranesh.sampleretrofit2.R;
import atrue.pranesh.sampleretrofit2.model.Albums;
import atrue.pranesh.sampleretrofit2.model.Users;
import atrue.pranesh.sampleretrofit2.network.ApiClient;
import atrue.pranesh.sampleretrofit2.network.ApiStories;
import atrue.pranesh.sampleretrofit2.ui.album.business.AlbumAdapter;
import atrue.pranesh.sampleretrofit2.ui.photos.PhotoFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumFragment extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    List<Albums> lstAlbum=new ArrayList<>();
    AlbumAdapter albumAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.album_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyAlbum);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        Bundle bundle = new Bundle();
        bundle = getArguments();
        Users users = null;
        if (bundle.containsKey("Users")) {
            users = bundle.getParcelable("Users");
        }


        if (lstAlbum.size() > 0) {
            setAdapter(lstAlbum);
        } else {
            if (users != null)
                fetchAlbumlist(users.id);
        }
    }

    private void fetchAlbumlist(int id) {
        ApiStories apiStories = ApiClient.getClient().create(ApiStories.class);
        Call<List<Albums>> call = apiStories.doGetalbumsList(id);
        call.enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
                setAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {

            }
        });
    }

    private void setAdapter(List<Albums> lstAlbum) {
        this.lstAlbum=lstAlbum;
        albumAdapter = new AlbumAdapter(lstAlbum, this);
        recyclerView.setAdapter(albumAdapter);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.frmAlbum:
                Albums albums = (Albums) view.getTag();
                int pos= (int) view.getTag(R.id.frmAlbum);
                showPhotoFragment(albums,pos);
                break;
        }
    }

    private void showPhotoFragment(Albums albums, int pos) {
        PhotoFragment photoFragment=new PhotoFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("Albums",albums);
        bundle.putInt("pos",pos);
        photoFragment.setArguments(bundle);
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.base_container,photoFragment,"PhotoFragment");
        fragmentTransaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN );
        fragmentTransaction.addToBackStack("PhotoFragment");
        fragmentTransaction.commit();
    }
}
