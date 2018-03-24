package atrue.pranesh.sampleretrofit2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import atrue.pranesh.sampleretrofit2.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {
FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout=findViewById(R.id.base_container);
        setHomeFragment();
    }

    private void setHomeFragment() {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.base_container,new HomeFragment(),"Home");
        fragmentTransaction.commit();
    }
}
