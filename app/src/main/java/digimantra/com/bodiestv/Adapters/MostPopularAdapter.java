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
public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularAdapter.MostPopularViewHolder> {
    @Override
    public MostPopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MostPopularViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_activity_card_1, parent, false));
    }

    @Override
    public void onBindViewHolder(MostPopularViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

     class MostPopularViewHolder extends RecyclerView.ViewHolder {

        ImageView check, activityIcon;
        TextView activityName, activityNumber;

        public MostPopularViewHolder(View itemView) {
            super(itemView);

            check = (ImageView) itemView.findViewById(R.id.image_check_mark);
            activityIcon = (ImageView) itemView.findViewById(R.id.activity_icon);
            activityName = (TextView) itemView.findViewById(R.id.activity_name);
            activityNumber = (TextView) itemView.findViewById(R.id.activity_count);
        }
    }
}
