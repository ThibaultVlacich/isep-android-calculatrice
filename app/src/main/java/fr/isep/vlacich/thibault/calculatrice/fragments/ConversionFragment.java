package fr.isep.vlacich.thibault.calculatrice.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.isep.vlacich.thibault.calculatrice.R;
import fr.isep.vlacich.thibault.calculatrice.services.FixerLatest;
import fr.isep.vlacich.thibault.calculatrice.services.FixerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConversionFragment extends Fragment implements Callback<FixerLatest> {

    FixerService fixerService;

    Map<String, String> currencies = new HashMap<>();

    Spinner entryCurrencySpinner;
    Spinner outputCurrencySpinner;

    EditText entryAmount;
    TextView outputAmount;

    public ConversionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Create the FixerService object
        fixerService = new Retrofit.Builder()
                .baseUrl(FixerService.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FixerService.class);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conversion, container, false);

        // Bind layout elements
        entryCurrencySpinner  = view.findViewById(R.id.entryCurrency);
        outputCurrencySpinner = view.findViewById(R.id.outputCurrency);
        entryAmount           = view.findViewById(R.id.entryAmount);
        outputAmount          = view.findViewById(R.id.outputAmount);

        // Init the list of currencies
        loadCurrenciesFromJSON();

        // Populate the spinners
        List countriesName = new ArrayList(currencies.values());
        Collections.sort(countriesName);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, countriesName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        entryCurrencySpinner.setAdapter(adapter);
        entryCurrencySpinner.setSelection(countriesName.indexOf("Euro"));

        outputCurrencySpinner.setAdapter(adapter);
        outputCurrencySpinner.setSelection(countriesName.indexOf("US dollar"));

        // Bind the click on convert button action
        view.findViewById(R.id.buttonConvert).setOnClickListener((View v) -> commitConversion());

        return view;
    }

    private void loadCurrenciesFromJSON() {
        String json;

        try {
            InputStream is = getActivity().getAssets().open("currencies.json");
            int size       = is.available();
            byte[] buffer  = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        try {
            JSONObject jsonObject     = new JSONObject(json);
            JSONArray currenciesArray = jsonObject.getJSONArray("currencies");

            for (int i = 0; i < currenciesArray.length(); i++) {
                JSONObject currency = currenciesArray.getJSONObject(i);

                currencies.put(currency.getString("code"), currency.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
    private String findCurrencyCode(String currencyName) {
        for (Map.Entry<String, String> currency : currencies.entrySet()) {
            String code = currency.getKey();
            String name = currency.getValue();

            if (name.equals(currencyName)) {
                return code;
            }
        }

        return null;
    }

    private void commitConversion() {
        String entryCurrencyCode = findCurrencyCode(entryCurrencySpinner.getSelectedItem().toString().trim());
        String outputCurrencyCode = findCurrencyCode(outputCurrencySpinner.getSelectedItem().toString().trim());

        Call<FixerLatest> call = fixerService.latest(entryCurrencyCode, outputCurrencyCode);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<FixerLatest> call, Response<FixerLatest> response) {
        if (response.isSuccessful()) {
            FixerLatest body = response.body();

            Double amount = Double.parseDouble(entryAmount.getText().toString());
            Double rate   = (Double) body.getRates().values().toArray()[0];

            Double result = amount * rate;

            outputAmount.setText(new DecimalFormat("#.##").format(result));
        }
    }

    @Override
    public void onFailure(Call<FixerLatest> call, Throwable t) {
        t.printStackTrace();
    }
}
