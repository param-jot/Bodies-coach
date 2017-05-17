package digimantra.com.bodiestv.ui.onboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import digimantra.com.bodiestv.R;

/**
 * Created by shivam on 2/8/16.
 */
public class SliderFragment extends Fragment {


    private static final String IMAGE_NUMBER = "slide_number";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layout = 0;
        switch (getArguments().getInt(IMAGE_NUMBER)){
            case 0:
                layout = R.layout.slider_1;
                break;
            case 1:
                layout = R.layout.slider_2;
                break;
            case 2:
                layout = R.layout.slider_3;
                break;
        }
        return inflater.inflate(layout,container, false);
    }



    public static SliderFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt(IMAGE_NUMBER, position);
        SliderFragment fragment = new SliderFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
