package id.tiketbus.tiketbus;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.tiketbus.tiketbus.fragments.AkunFragment;
import id.tiketbus.tiketbus.fragments.HomeFragment;
import id.tiketbus.tiketbus.fragments.PesananFragment;
import id.tiketbus.tiketbus.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new HomeFragment());
        bottomNavigationView = findViewById(R.id.bn_main_navigation);
        BottomNavigation();


    }

    private void BottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.cari:
                        fragment = new SearchFragment();
                        break;
                    case R.id.pesanan:
                        fragment = new PesananFragment();
                        break;
                    case R.id.akun:
                        fragment = new AkunFragment();
                        break;
                }
                return loadFragment(fragment);
            }
        });
    }

    // method untuk load fragment yang sesuai
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_view, fragment)
                    .commit();
            return true;
        }
        return false;
    }


}
