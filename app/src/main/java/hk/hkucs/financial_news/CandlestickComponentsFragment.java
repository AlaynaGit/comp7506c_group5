package hk.hkucs.financial_news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.io.File;

public class CandlestickComponentsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_candlestick_components, container, false);
        
        ImageView imageView = view.findViewById(R.id.candlestick_components_image);
        
        // Load the image from the Material folder
        File imageFile = new File(requireContext().getFilesDir().getParentFile(), "Material/Foto for Candlestick Components.png");
        if (imageFile.exists()) {
            imageView.setImageBitmap(android.graphics.BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
        }
        
        return view;
    }
} 