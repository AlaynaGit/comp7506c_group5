package hk.hkucs.financial_news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;

public class LearnFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learn, container, false);
        
        // Initialize card click listeners
        setupCardClickListeners(view);
        
        return view;
    }

    private void setupCardClickListeners(View view) {
        CardView financialLiteracyCard = view.findViewById(R.id.card_financial_literacy);
        CardView candlestickCard = view.findViewById(R.id.card_candlestick);
        CardView tradingStrategyCard = view.findViewById(R.id.card_trading_strategy);
        CardView financialStatementCard = view.findViewById(R.id.card_financial_statement);

        financialLiteracyCard.setOnClickListener(v -> {
            // TODO: Handle Financial Literacy card click
        });

        candlestickCard.setOnClickListener(v -> {
            // Show Candlestick Pattern Fragment
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new CandlestickPatternFragment())
                        .addToBackStack("candlestick_pattern")
                        .commit();
            }
        });

        tradingStrategyCard.setOnClickListener(v -> {
            // TODO: Handle Trading Strategy card click
        });

        financialStatementCard.setOnClickListener(v -> {
            // TODO: Handle Financial Statement card click
        });
    }
} 