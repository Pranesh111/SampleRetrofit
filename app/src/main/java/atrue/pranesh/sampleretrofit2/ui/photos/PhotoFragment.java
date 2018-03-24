package atrue.pranesh.sampleretrofit2.ui.photos;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import atrue.pranesh.sampleretrofit2.MainActivity;
import atrue.pranesh.sampleretrofit2.R;
import atrue.pranesh.sampleretrofit2.model.Albums;
import atrue.pranesh.sampleretrofit2.model.Photos;
import atrue.pranesh.sampleretrofit2.network.ApiClient;
import atrue.pranesh.sampleretrofit2.network.ApiStories;
import atrue.pranesh.sampleretrofit2.ui.photos.business.PhotoAdapter;
import atrue.pranesh.sampleretrofit2.widget.CenterZoomLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoFragment extends Fragment {
    PhotoAdapter photoAdapter;
    List<Photos> photos = new ArrayList<>();
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.phot_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyPhotos);
        recyclerView.setLayoutManager(new CenterZoomLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        Bundle bundle;
        Albums albums = null;
        bundle = getArguments();
        if (bundle != null && bundle.containsKey("Albums")) {
            albums = bundle.getParcelable("Albums");
        }
        if (photos.size() > 0) {
            setAdapter(photos);
        } else {
            fetchPhoto(albums.id);
        }

    }

    private void fetchPhoto(int id) {
        ApiStories apiStories = ApiClient.getClient().create(ApiStories.class);
        Call<List<Photos>> call = apiStories.doGetPhotoList(id);
        call.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                setAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {

            }
        });
    }

    private void setAdapter(List<Photos> photos) {
        this.photos=photos;
        photoAdapter = new PhotoAdapter(photos, (MainActivity) getActivity());
        recyclerView.setAdapter(photoAdapter);
    }
}
