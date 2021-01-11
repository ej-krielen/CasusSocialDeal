package nl.rekijan.casussocialdeal.mvc;

import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import nl.rekijan.casussocialdeal.DealDetailActivity;
import nl.rekijan.casussocialdeal.DealListActivity;
import nl.rekijan.casussocialdeal.R;
import nl.rekijan.casussocialdeal.utility.AppConstants;

/**
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 7-1-2021
 */
public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealViewHolder> {

    private final ArrayList<DealModel> mDeals = new ArrayList<>();
    private final DealListActivity mDealListActivity;

    public DealAdapter(DealListActivity dealListActivity) {
        mDealListActivity = dealListActivity;
    }

    @Override
    public int getItemCount() {
        return mDeals.size();
    }

    public void add(DealModel dealModel) {
        mDeals.add(dealModel);
    }

    public void addAll(ArrayList<DealModel> dealModels) {
        mDeals.clear();
        mDeals.addAll(dealModels);
    }

    /* ViewHolder region */
    public static class DealViewHolder extends RecyclerView.ViewHolder{
        final ConstraintLayout constraintLayout;
        final ImageView dealImageView;
        final TextView titleTextView;
        final TextView companyCityTextView;
        final TextView soldTextView;
        final TextView fromPriceTextView;
        final TextView priceTextView;

        DealViewHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.dealConstraintLayout);
            dealImageView = itemView.findViewById(R.id.dealImageView);
            titleTextView = itemView.findViewById(R.id.titleLocationDealTextView);
            companyCityTextView = itemView.findViewById(R.id.addressDealTextView);
            soldTextView = itemView.findViewById(R.id.soldDealTextView);
            fromPriceTextView = itemView.findViewById(R.id.fromPriceDealTextView);
            priceTextView = itemView.findViewById(R.id.priceDealTextView);
        }
    }
    /* End of ViewHolder region */

    @NonNull
    @Override
    public DealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deal_list, parent, false);
        return new DealViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DealViewHolder holder, int position) {
        // Get the corresponding DealModel
        final DealModel deal = mDeals.get(position);

        // Fetch image with Glide Library and load it in
        Glide.with(mDealListActivity)
                .load(AppConstants.URL_PREFIX + deal.getImageLink())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_error)
                .into(holder.dealImageView);

        //Set fields with data from the DealModel
        holder.titleTextView.setText(deal.getTitle());
        String companyCityString = deal.getCompanyName() + "\n" + deal.getCityName();
        holder.companyCityTextView.setText(companyCityString);
        holder.soldTextView.setText(deal.getSoldText());
        holder.fromPriceTextView.setText(getPriceString(deal.getPriceFrom()));
        holder.fromPriceTextView.setPaintFlags(holder.fromPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.priceTextView.setText(getPriceString(deal.getPrice())); //TODO format so that cents are smaller font
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open new DealDetailActivity to display Details of the deal
                Intent startActivityIntent = new Intent(mDealListActivity, DealDetailActivity.class);
                // Add data from deal so they don't have to be acquired a second time
                startActivityIntent.putExtra(AppConstants.INTENT_IMG, AppConstants.URL_PREFIX + deal.getImageLink());
                startActivityIntent.putExtra(AppConstants.INTENT_TITLE, deal.getTitle());
                startActivityIntent.putExtra(AppConstants.INTENT_SUBTITLE, companyCityString);
                startActivityIntent.putExtra(AppConstants.INTENT_COMPANY_NAME, deal.getCompanyName());
                startActivityIntent.putExtra(AppConstants.INTENT_SOLD_TEXT, deal.getSoldText());
                startActivityIntent.putExtra(AppConstants.INTENT_PRICE, getPriceString(deal.getPrice()));
                startActivityIntent.putExtra(AppConstants.INTENT_PRICE_FROM, getPriceString(deal.getPriceFrom()));

                mDealListActivity.getApplicationContext().startActivity(startActivityIntent);
            }
        });
    }

    /**
    * Prefix currency symbol to price<br>
     *     //TODO format so that trailing zeros don't get removed
     *     //TODO For a final version convert currency and display user's currency
     */
    public String getPriceString(double price) {
        return mDealListActivity.getString(R.string.currency) + price;
    }
}
