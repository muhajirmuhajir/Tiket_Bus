package id.tiketbus.tiketbus.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import id.tiketbus.tiketbus.fragments.signin;
import id.tiketbus.tiketbus.fragments.signup;

public class LoginFragmentsAdapter extends FragmentPagerAdapter {
    public LoginFragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new signin();
            case 1:
                return new signup();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
