package fr.isep.vlacich.thibault.calculatrice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import fr.isep.vlacich.thibault.calculatrice.fragments.BinaryFragment;
import fr.isep.vlacich.thibault.calculatrice.fragments.CalculatorFragment;
import fr.isep.vlacich.thibault.calculatrice.fragments.ConversionFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = (@NonNull MenuItem item) -> {
        switch (item.getItemId()) {
            case R.id.navigation_calculator:
                showFragment(new CalculatorFragment());
                return true;

            case R.id.navigation_binary:
                showFragment(new BinaryFragment());
                return true;

            case R.id.navigation_conversion:
                showFragment(new ConversionFragment());
                return true;
        }

        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        showFragment(new CalculatorFragment());
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

}
