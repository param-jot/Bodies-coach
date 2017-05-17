package digimantra.com.bodiestv.ui.coachlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.R;

/**
 * Created by shivam on 17/8/16.
 */
public class RefineFiltersFragment extends Fragment {

    private RefineFilterCallbacks callback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.refine_listing_view, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.back_arrow)
    void onCLose(){
        callback.onClosePressed();
    }

    @OnClick(R.id.sort_requested)
    void filters(){
        callback.onSortRequested();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        callback = (RefineFilterCallbacks)getActivity();
    }

    public interface RefineFilterCallbacks{
        void onClosePressed();
        void onSortRequested();
    }


}
