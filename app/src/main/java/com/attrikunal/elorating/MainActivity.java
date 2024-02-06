package com.attrikunal.elorating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.attrikunal.elorating.adapters.MyPagerAdapter;
import com.attrikunal.elorating.fragments.OutcomeFragment;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText player1RatingText, player2RatingText;
    private MaterialTextView newRatings;
    private Button updateBtn;
    private ELO elo;
    private TabLayout tabsGroup;
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1RatingText = findViewById(R.id.player1rating);
        player2RatingText = findViewById(R.id.player2rating);
        newRatings = findViewById(R.id.newratings);
        updateBtn = findViewById(R.id.updateButton);
        tabsGroup = findViewById(R.id.tabsGroup);
        viewPager2 = findViewById(R.id.viewPager);

        FragmentManager manager = getSupportFragmentManager();
        MyPagerAdapter adapter = new MyPagerAdapter(manager, getLifecycle());
        viewPager2.setAdapter(adapter);

        tabsGroup.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabsGroup.selectTab(tabsGroup.getTabAt(position));
            }
        });

        elo = new ELO();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int player1Rating = 1500, player2Rating = 1500, outcome = 0;
                boolean process = true;

                Editable p1text = player1RatingText.getText();
                if (p1text != null) {
                    try {
                        player1Rating = Integer.parseInt(p1text.toString());
                    } catch (NumberFormatException e) {
                        player1RatingText.setError("Required");
                        process = false;
                    }
                } else {
                    player1RatingText.setError("Required");
                    process = false;
                }

                Editable p2text = player2RatingText.getText();
                if (p2text != null) {
                    try {
                        player2Rating = Integer.parseInt(p2text.toString());
                    } catch (NumberFormatException e) {
                        player2RatingText.setError("Required");
                        process = false;
                    }
                } else {
                    player2RatingText.setError("Required");
                    process = false;
                }

                int selectedChip = OutcomeFragment.getSelectedChip();
                if (selectedChip == -1) {
                    Toast.makeText(MainActivity.this,
                            "Outcome required",
                            Toast.LENGTH_SHORT).show();
                    process = false;
                }

                if (process) {
                     if (selectedChip == R.id.p1wonchip) {
                         outcome = 1;
                     } else if (selectedChip == R.id.p2wonchip) {
                         outcome = 2;
                     } else {
                         outcome = 0;
                     }

                     double[] new_ratings = elo.elo(player1Rating, player2Rating, outcome);
                     int p1rating = (int) new_ratings[0];
                     int p2rating = (int) new_ratings[1];

                     String output = "Player 1: " + p1rating + "\nPlayer 2: " + p2rating;
                     newRatings.setText(output);
                     newRatings.setVisibility(View.VISIBLE);
                }
            }
        });


    }
}