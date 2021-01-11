package nl.rekijan.casussocialdeal;

import android.app.ProgressDialog;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONException;
import org.json.JSONObject;

import nl.rekijan.casussocialdeal.utility.AppConstants;

public class DealDetailActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private TextView descriptionDetailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_detail);

        //Enable back button and set title in action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getIntent().getStringExtra(AppConstants.INTENT_COMPANY_NAME));
        }
        
        // Fetch image with Glide Library and load it in
        // TODO instead pass image to from activity to activity so image doesn't have to be downloaded twice
        // TODO make image gallery instead
        ImageView imageDetailView = findViewById(R.id.dealDetailImageView);
        Glide.with(this)
                .load(getIntent().getStringExtra(AppConstants.INTENT_IMG))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_error)
                .fallback(R.drawable.ic_error)
                .into(imageDetailView);

        // Set text views with data passed through the intent
        TextView titleLocationDetailTextView = findViewById(R.id.titleLocationDetailTextView);
        titleLocationDetailTextView.setText(getIntent().getStringExtra(AppConstants.INTENT_TITLE));

        TextView addressDetailTextView = findViewById(R.id.addressDetailTextView);
        addressDetailTextView.setText(getIntent().getStringExtra(AppConstants.INTENT_SUBTITLE));

        TextView soldDetailTextView = findViewById(R.id.soldDetailTextView);
        soldDetailTextView.setText(getIntent().getStringExtra(AppConstants.INTENT_SOLD_TEXT));

        TextView fromPriceDetailTextView = findViewById(R.id.fromPriceDetailTextView);
        fromPriceDetailTextView.setText(getIntent().getStringExtra(AppConstants.INTENT_PRICE_FROM));
        fromPriceDetailTextView.setPaintFlags(fromPriceDetailTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        TextView priceDetailTextView = findViewById(R.id.priceDetailTextView);
        priceDetailTextView.setText(getIntent().getStringExtra(AppConstants.INTENT_PRICE));

        TextView dealDetailPriceTextView = findViewById(R.id.priceBuyNowDetailTextView);
        dealDetailPriceTextView.setText(getIntent().getStringExtra(AppConstants.INTENT_PRICE));

        // Fetch views that need to be set after json has be parsed
        descriptionDetailTextView = findViewById(R.id.descriptionDetailTextView);
        //TODO get other views from activity_deal_detail.xml

        // Use volley library to get json data
        //TODO volley doesn't use proper encoding? d'n becomes DÂ'n
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.show();

        StringRequest request = new StringRequest(AppConstants.DEAL_DETAILS_LIST_JSON, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //TODO better error handling
                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(DealDetailActivity.this);
        rQueue.add(request);
    }

    void parseJsonData(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                descriptionDetailTextView.setText(Html.fromHtml(object.getString(AppConstants.JSON_DESCRIPTION), Html.FROM_HTML_MODE_COMPACT));
            } else {
                descriptionDetailTextView.setText(Html.fromHtml(object.getString(AppConstants.JSON_DESCRIPTION), Html.FROM_HTML_MODE_LEGACY));
            }
            //TODO get more data and set to fields from activity_deal_detail.xml

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mProgressDialog.dismiss();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}