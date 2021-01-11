package nl.rekijan.casussocialdeal;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import nl.rekijan.casussocialdeal.mvc.DealAdapter;
import nl.rekijan.casussocialdeal.mvc.DealModel;
import nl.rekijan.casussocialdeal.utility.AppConstants;

public class DealListActivity extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    RecyclerView mDealsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup RecyclerView by binding the adapter to it.
        mDealsRecyclerView = findViewById(R.id.dealsRecyclerView);
        mDealsRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mDealsRecyclerView.setLayoutManager(llm);

        // Use volley library to get json data
        //TODO volley doesn't use proper encoding? d'n becomes DÂ'n
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.show();

        StringRequest request = new StringRequest(AppConstants.DEAL_LIST_JSON, new Response.Listener<String>() {
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

        RequestQueue rQueue = Volley.newRequestQueue(DealListActivity.this);
        rQueue.add(request);
    }

    void parseJsonData(String jsonString) {
        try {
            // Get entire list of deals
            //TODO for final version get data in batches instead, data most likely to big to do all at once
            JSONObject object = new JSONObject(jsonString);
            JSONArray dealsArray = object.getJSONArray("deals");
            // Make an ArrayList to store Models in
            ArrayList<DealModel> dealModels = new ArrayList<>();

            // Go through list of deals and put them in the ArrayList
            for (int i = 0; i < dealsArray.length(); ++i) {
                JSONObject deal = dealsArray.getJSONObject(i);

                dealModels.add(new DealModel(deal.getString(AppConstants.JSON_UNIQUE),
                        deal.getString(AppConstants.JSON_IMG),
                        deal.getString(AppConstants.JSON_TITLE),
                        deal.getString(AppConstants.JSON_COMPANY_NAME),
                        deal.getString(AppConstants.JSON_CITY_NAME),
                        deal.getString(AppConstants.JSON_SOLD_TEXT),
                        deal.getDouble(AppConstants.JSON_PRICE),
                        deal.getDouble(AppConstants.JSON_PRICE_FROM)));
            }

            //Fill adapter with ArrayList and set it to a custom adapter
            DealAdapter adapter = new DealAdapter(this);
            adapter.addAll(dealModels);
            mDealsRecyclerView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mProgressDialog.dismiss();
    }
}