package digimantra.com.bodiestv.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import digimantra.com.bodiestv.R;

/**
 * Created by shivam on 17/7/16.
 */
public class CoachFeedbackAdapter extends RecyclerView.Adapter<CoachFeedbackAdapter.ViewHolder> {


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_sessions_history_card,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.not_rated_layout)
        LinearLayout notRatedLyout;

        @BindView(R.id.feedback_details_layout)
        RelativeLayout feedbackDetailsLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            notRatedLyout.setVisibility(View.GONE);
            feedbackDetailsLayout.setVisibility(View.GONE);
        }
    }
}
