package atrue.pranesh.sampleretrofit2.ui.home.business;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import atrue.pranesh.sampleretrofit2.R;
import atrue.pranesh.sampleretrofit2.model.Users;


public class UserHolder extends RecyclerView.ViewHolder {
    TextView txtName,txtEmail,txtPhne,txtCompany,txtAddress,txtWebSite;
    CardView cardView;
    View.OnClickListener listener;
    public UserHolder(View view,View.OnClickListener listener) {
        super(view);
        txtName=view.findViewById(R.id.txtName);
        txtEmail=view.findViewById(R.id.txtEmail);
        txtPhne=view.findViewById(R.id.txtPhone);
        txtCompany=view.findViewById(R.id.txtCompany);
        txtAddress=view.findViewById(R.id.txtaddress);
        txtWebSite=view.findViewById(R.id.txtWebsite);
        cardView=view.findViewById(R.id.cardview);
        this.listener=listener;
    }

    public void onBind(Users users, int position) {
        txtName.setText(users.name);
        txtEmail.setText(users.email);
        txtPhne.setText(users.phone);
        txtCompany.setText(users.company.name);
        txtAddress.setText(users.address.street.concat(",").concat(users.address.city));
        txtWebSite.setText(users.website);

        cardView.setOnClickListener(listener);
        cardView.setTag(users);
        cardView.setTag(R.id.cardview,position);
    }
}
