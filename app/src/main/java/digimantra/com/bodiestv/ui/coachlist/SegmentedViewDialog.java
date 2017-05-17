package digimantra.com.bodiestv.ui.coachlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.customview.SelectorWidget;

/**
 * Created by shivam on 17/8/16.
 */
public class SegmentedViewDialog extends Fragment {

    @BindView(R.id.selectorWidget)
    SelectorWidget selectorWidget;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.segmented_view,container,false);
//        setPosition();
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectorWidget.setListener(new SelectorWidget.OnSelectedListener() {
            @Override
            public void onSelect(String value) {
                Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
//        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }

//    private void setPosition() {
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//
//        Window window = getDialog().getWindow();
//        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        lp.copyFrom(window.getAttributes());
////This makes the dialog take up the full width
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//        lp.gravity = Gravity.TOP;
//        window.setAttributes(lp);
//    }
}
