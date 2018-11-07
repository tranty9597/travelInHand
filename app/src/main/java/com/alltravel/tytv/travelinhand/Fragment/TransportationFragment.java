package com.alltravel.tytv.travelinhand.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alltravel.tytv.travelinhand.CreateTravelActivity;
import com.alltravel.tytv.travelinhand.R;
import com.alltravel.tytv.travelinhand.adapters.TransportationListAdapter;
import com.alltravel.tytv.travelinhand.model.base.Transportation;
import com.alltravel.tytv.travelinhand.model.base.TravelStep;
import com.alltravel.tytv.travelinhand.services.TransportationService;
import com.alltravel.tytv.travelinhand.singleton.RetrofitInstance;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.CRC32;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TransportationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TransportationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransportationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnAddTransportation;
    private ListView listView;
    private OnFragmentInteractionListener mListener;
    private String transportId = "";

    public TransportationFragment() {
        // Required empty public constructor
    }

    public void setTransportId(String transportId) {
        this.transportId = transportId;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransportationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransportationFragment newInstance(String param1, String param2) {
        TransportationFragment fragment = new TransportationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transportation, container, false);
        String from_location = ((CreateTravelActivity) getActivity()).getFromLocation();
        String to_location = ((CreateTravelActivity) getActivity()).getToLocation();
        this.fetchData(this,from_location, to_location);
        this.listView = view.findViewById(R.id.transportationListView);

        TextView txtTitle = view.findViewById(R.id.txtTransTitle);
        txtTitle.setText("List of Transportation From " + from_location + " To " + to_location);
//        this.btnAddTransportation = view.findViewById(R.id.btnAddTransportation);
//        this.btnAddTransportation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!transportId.equals("")) {
//                    ((CreateTravelActivity) getActivity()).setTransportation(transportId);
//                    Toast.makeText((CreateTravelActivity)getActivity(), "Add Transportation Successfully", Toast.LENGTH_SHORT).show();
//                    btnAddTransportation.setEnabled(false);
//                }
//            }
//        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    //Fetch data transportation from API
    private void fetchData(final TransportationFragment fragment, String from_location, String to_location) {
        final ArrayList<Transportation> listTrans = new ArrayList<>();
        TransportationService transportationService = RetrofitInstance.getRetrofitInstance().create(TransportationService.class);
        Call<Object> call = transportationService.getTransportation("CITY-1", "CITY-1");
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                JsonParser parser = new JsonParser();
                JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();
                if(resJson.get("isError").getAsBoolean()){
                    return;
                }else {
                    JsonArray data = resJson.get("data").getAsJsonArray();
                    ArrayList<TravelStep> travelSteps = new ArrayList<>();
                    for (JsonElement step: data) {
                        JsonObject stepObj = (JsonObject) step;
                        listTrans.add(new Transportation(
                                stepObj.get("ID").getAsString(),
                                stepObj.get("transpotationNm").getAsString(),
                                stepObj.get("phone").getAsString(),
                                stepObj.get("openTime").getAsString(),
                                stepObj.get("timeDistance").getAsInt(),
                                stepObj.get("price").getAsDouble(),
                                stepObj.get("description").getAsString()
                        ));
                    }
                    if (!listTrans.isEmpty()) {
                        TransportationListAdapter adapter = new TransportationListAdapter((CreateTravelActivity)getActivity() ,fragment, listTrans);
                        listView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
