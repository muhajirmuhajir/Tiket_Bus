package id.tiketbus.tiketbus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import id.tiketbus.tiketbus.adapters.LoginFragmentsAdapter;

public class LoginActivity extends AppCompatActivity {
    private ViewPager container;
    private TabLayout tab;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        container = findViewById(R.id.container_view);
        tab = findViewById(R.id.tabView);
        LoginFragmentsAdapter adapter = new LoginFragmentsAdapter(getSupportFragmentManager());
        container.setAdapter(adapter);
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(container));
        container.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}
