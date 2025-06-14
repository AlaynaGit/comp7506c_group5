package hk.hkucs.financial_news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;

public class CandlestickPatternFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_candlestick_pattern, container, false);
        
        // Initialize card click listeners
        setupCardClickListeners(view);
        
        return view;
    }

    private void setupCardClickListeners(View view) {
        CardView componentsCard = view.findViewById(R.id.card_components);
        CardView bullishCard = view.findViewById(R.id.card_bullish);
        CardView bearishCard = view.findViewById(R.id.card_bearish);
        CardView neutralCard = view.findViewById(R.id.card_neutral);

        componentsCard.setOnClickListener(v -> {
            // Show Candlestick Components Fragment
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new CandlestickComponentsFragment())
                        .addToBackStack("candlestick_components")
                        .commit();
            }
        });

        bullishCard.setOnClickListener(v -> {
            // Show Bullish Pattern Fragment
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new BullishPatternFragment())
                        .addToBackStack("bullish_pattern")
                        .commit();
            }
        });

        bearishCard.setOnClickListener(v -> {
            // Show Bearish Pattern Fragment
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new BearishPatternFragment())
                        .addToBackStack("bearish_pattern")
                        .commit();
            }
        });

        neutralCard.setOnClickListener(v -> {
            // Show Neutral Pattern Fragment
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new NeutralPatternFragment())
                        .addToBackStack("neutral_pattern")
                        .commit();
            }
        });
    }
} 