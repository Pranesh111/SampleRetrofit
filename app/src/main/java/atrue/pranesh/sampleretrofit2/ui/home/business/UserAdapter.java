package atrue.pranesh.sampleretrofit2.ui.home.business;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import atrue.pranesh.sampleretrofit2.R;
import atrue.pranesh.sampleretrofit2.model.Users;


public class UserAdapter extends RecyclerView.Adapter<UserHolder> {
    View.OnClickListener listener;
    List<Users> lstUsr;
    UserHolder userHolder;
    public UserAdapter(List<Users> lstUsr, View.OnClickListener listener) {
        this.lstUsr=lstUsr;
        this.listener=listener;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_adapter,parent,false);
        userHolder=new UserHolder(view,listener);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        userHolder.onBind(lstUsr.get(position),position);
    }

    @Override
    public int getItemCount() {
        return lstUsr.size();
    }
}
