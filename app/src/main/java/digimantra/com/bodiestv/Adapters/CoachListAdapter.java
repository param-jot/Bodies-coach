package digimantra.com.bodiestv.Adapters;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.networking.responses.coaches.Coach;
import digimantra.com.bodiestv.networking.responses.coaches.CoachListResponse;
import retrofit2.Callback;

/**
 * Created by shivam on 13/7/16.
 */
public class CoachListAdapter extends ParallaxRecyclerAdapter<Coach> {

    private  List<Coach> coachList;
    HashMap<Integer,Boolean> favoriteStore ;

    private  View.OnClickListener clickListener;

//    public CoachListAdapter(List<Coach> data, View.OnClickListener clickListener) {
//        super(data);
//        this.clickListener = clickListener;
//
//    }

    public CoachListAdapter(List<Coach> coach) {
        super(coach);
        coach.add(0,null);
//        coach.add(1,null);
        coach.add(coach.size(),null);

        Log.d("TAG", coach.toString());

        this.coachList = coach;
        favoriteStore = new HashMap<>();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return VIEW_TYPES.HEADER;
        }
        if (position == 1)
            return  VIEW_TYPES.FIRST_VIEW;
        if(position == coachList.size()){
            return  100;
        }
        return VIEW_TYPES.NORMAL;
    }

    @Override
    public void onBindViewHolderImpl(final RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<Coach> parallaxRecyclerAdapter, final int i) {

        Log.d("TAG","Coach AT"+i);
        if(viewHolder instanceof MyCustomViewHolder){



            Coach coach = coachList.get(i);

            final MyCustomViewHolder viewHolder1 = (MyCustomViewHolder) viewHolder;


            if(i%2==0){
                viewHolder1.rootview.setBackgroundColor(Color.WHITE);
            }
            else{
                viewHolder1.rootview.setBackgroundColor(Color.parseColor("#F0F0F0"));
            }

            viewHolder1.heartImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(favoriteStore.containsKey(i)){
                        if(!favoriteStore.get(i)){
                            viewHolder1.heartImage.setImageResource(R.drawable.heart_filled);
                            favoriteStore.put(i,true);
                        }
                        else{
                            viewHolder1.heartImage.setImageResource(R.drawable.heart);
                            favoriteStore.put(i,false);
                        }
                    }
                    else{
                        viewHolder1.heartImage.setImageResource(R.drawable.heart_filled);
                        favoriteStore.put(i,true);

                    }
                }
            });

            viewHolder1.coachName.setText(coach.getUserDetails().getFirstname() + " " + coach.getUserDetails().getLastname());
            viewHolder1.coachCity.setText(coach.getUserAddressList().get(0).city);
            viewHolder1.coachDetails.setText(coach.getBrandingDesc());
            viewHolder1.reviewCount.setText(coach.getRewardInfo());
            viewHolder1.upvotes.setText("98%");
            Picasso.with(viewHolder1.coachImage.getContext()).load(viewHolder1.coachImage.getContext().getString(R.string.image_url)+coach.getUserDetails().getUserphotoPath()).into(viewHolder1.coachImage);

        }

    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<Coach> parallaxRecyclerAdapter, int i) {
        View view = null;
        Log.d("Value of i ", String.valueOf(i));
        if(i==VIEW_TYPES.NORMAL){
            view = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.coach_listing_card, viewGroup, false));
            view.setOnClickListener(clickListener);
            return new MyCustomViewHolder(view);
        }
        else if(i==VIEW_TYPES.FIRST_VIEW){
            view = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.coach_list_top_view, viewGroup, false));
            return new TopView(view);
        }
        else if(i==100){
            view = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_more_result_button, viewGroup, false));
            return new TopView(view);
        }

        return new MyCustomViewHolder(view);


    }





    @Override
    public int getItemCountImpl(ParallaxRecyclerAdapter<Coach> parallaxRecyclerAdapter) {
        return coachList.size() ;
    }




    public static class TopView extends RecyclerView.ViewHolder {




        public TopView(View view) {
            super(view);
            ButterKnife.bind(this,view);

        }
    }


    public static class MyCustomViewHolder extends RecyclerView.ViewHolder {


        private final View rootview;
        @BindView(R.id.heart_image)
        ImageView heartImage;

        @BindView(R.id.coach_name_text_view)
        TextView coachName;

        @BindView(R.id.coach_city_text)
        TextView coachCity;

        @BindView(R.id.coach_details)
        TextView coachDetails;

        @BindView(R.id.up_vote_percentage)
        TextView upvotes;

        @BindView(R.id.review_count)
        TextView reviewCount;

        @BindView(R.id.coach_image)
        ImageView coachImage;


        public MyCustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            this.rootview = view;
//            heartImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    heartImage.setImageResource(R.drawable.heart_filled);
//                }
//            });

        }
    }
}





