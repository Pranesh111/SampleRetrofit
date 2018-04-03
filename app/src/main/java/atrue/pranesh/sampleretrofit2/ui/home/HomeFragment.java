package atrue.pranesh.sampleretrofit2.ui.home;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import atrue.pranesh.sampleretrofit2.R;
import atrue.pranesh.sampleretrofit2.model.Users;
import atrue.pranesh.sampleretrofit2.network.ApiClient;
import atrue.pranesh.sampleretrofit2.network.ApiStories;
import atrue.pranesh.sampleretrofit2.ui.album.AlbumFragment;
import atrue.pranesh.sampleretrofit2.ui.home.business.UserAdapter;
import atrue.pranesh.sampleretrofit2.widget.CenterZoomLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener {

    RecyclerView recyclerView;
    List<Users> userList = new ArrayList<>();
    UserAdapter userAdapter;
    CollapsingToolbarLayout collapseToolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyUser);
        collapseToolbar=view.findViewById(R.id.collapsingToolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        if (userList.size() > 0) {
            setAdapter(userList);
        } else {
            fetchUserData();
        }
        Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.flower);

        if (bitmap != null && !bitmap.isRecycled()) {
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    applyPalette(palette);
                }
            });
        }

    }
    private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.colorPrimaryDark);
        int primary = getResources().getColor(R.color.colorPrimary);
        collapseToolbar.setContentScrimColor(palette.getMutedColor(primary));
        collapseToolbar.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
   }
    private void fetchUserData() {
        ApiStories apiStories = ApiClient.getClient().create(ApiStories.class);
        Call<List<Users>> call = apiStories.doGetListUsers();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                setAdapter(response.body());
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });
    }

    private void setAdapter(List<Users> lstUsr) {
        this.userList=lstUsr;
        userAdapter = new UserAdapter(lstUsr, this);
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.cardview:
                Users users = (Users) view.getTag();
                int pos = (int) view.getTag(R.id.cardview);
                showAlbumFragment(users,pos);
                break;
        }
    }

    private void showAlbumFragment(Users users, int pos) {
        AlbumFragment albumFragment=new AlbumFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("Users",users);
        bundle.putInt("pos",pos);
        albumFragment.setArguments(bundle);
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.base_container,albumFragment,"AlbumFragment");
        fragmentTransaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN );
        fragmentTransaction.addToBackStack("AlbumFragment");
        fragmentTransaction.commit();
    }
}
