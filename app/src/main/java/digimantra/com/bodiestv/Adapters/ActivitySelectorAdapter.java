package digimantra.com.bodiestv.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import digimantra.com.bodiestv.R;

/**
 * Created by mridul on 7/13/16.
 */
public class ActivitySelectorAdapter extends RecyclerView.Adapter<ActivitySelectorAdapter.ActivityViewHolder> {
    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ActivityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_activity_card_1, parent, false));
    }

    @Override
    public void onBindViewHolder(ActivityViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {

        ImageView check, activityIcon;
        TextView activityName, activityNumber;

        public ActivityViewHolder(View itemView) {
            super(itemView);

            check = (ImageView) itemView.findViewById(R.id.image_check_mark);
            activityIcon = (ImageView) itemView.findViewById(R.id.activity_icon);
            activityName = (TextView) itemView.findViewById(R.id.activity_name);
            activityNumber = (TextView) itemView.findViewById(R.id.activity_count);
        }
    }
}
