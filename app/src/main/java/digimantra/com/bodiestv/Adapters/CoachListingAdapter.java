package digimantra.com.bodiestv.Adapters;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import digimantra.com.bodiestv.R;

/**
 * Created by mridul on 7/12/16.
 */
public class CoachListingAdapter extends RecyclerView.Adapter<CoachListingAdapter.CoachListHolder> {
    @Override
    public CoachListingAdapter.CoachListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CoachListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.coach_listing_card, parent, false));

    }

    @Override
    public void onBindViewHolder(CoachListingAdapter.CoachListHolder holder, int position) {
        if (position%2==0)
            holder.completeLayout.setBackgroundColor(Color.parseColor("#F0F0F0"));
        else
            holder.completeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class CoachListHolder extends RecyclerView.ViewHolder {

        RelativeLayout completeLayout;
        CircularImageView coachImage;
        ImageView verificationImage, heart;
        TextView coachName, coachCity, coachDistance, coachDetails, coachUpVotes, coachReviews;

        public CoachListHolder(View itemView) {
            super(itemView);

            completeLayout = (RelativeLayout)itemView.findViewById(R.id.complete_layout);
            coachImage = (CircularImageView) itemView.findViewById(R.id.coach_image);
            verificationImage = (ImageView) itemView.findViewById(R.id.coach_verified_image);
            heart = (ImageView) itemView.findViewById(R.id.heart_image);
            coachName = (TextView) itemView.findViewById(R.id.coach_name_text_view);
            coachCity = (TextView)itemView.findViewById(R.id.coach_city_text);
            coachDistance = (TextView)itemView.findViewById(R.id.coach_distance);
            coachDetails = (TextView)itemView.findViewById(R.id.coach_details);
            coachUpVotes = (TextView)itemView.findViewById(R.id.up_vote_percentage);
            coachReviews = (TextView)itemView.findViewById(R.id.review_count);
        }
    }
}
