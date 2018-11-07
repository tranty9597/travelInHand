package com.alltravel.tytv.travelinhand.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alltravel.tytv.travelinhand.CreateTravelActivity;
import com.alltravel.tytv.travelinhand.R;
import com.alltravel.tytv.travelinhand.adapters.AddTravelHotelAdapter;
import com.alltravel.tytv.travelinhand.model.base.Hotel;
import com.alltravel.tytv.travelinhand.model.base.User;
import com.alltravel.tytv.travelinhand.services.HotelService;
import com.alltravel.tytv.travelinhand.singleton.RetrofitInstance;
import com.alltravel.tytv.travelinhand.singleton.UserInstance;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HotelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ListView hotelListView;
    private ArrayList<Hotel> hotels;
    public HotelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HotelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HotelFragment newInstance(String param1, String param2) {
        HotelFragment fragment = new HotelFragment();
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
        View view= inflater.inflate(R.layout.fragment_hotel, container, false);

        hotels= new ArrayList<>();
        hotelListView = view.findViewById(R.id.hotelList);
        loadHotels();
        return view;
    }

    private void loadHotels(){
        HotelService hotelService = RetrofitInstance.getRetrofitInstance().create(HotelService.class);
        Call<Object> call = hotelService.getHotels("");
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                JsonParser parser = new JsonParser();
                JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();
                if(resJson.get("isError").getAsBoolean()){
                    return;
                }else{
                    JsonArray data = resJson.getAsJsonArray("data");
                    for (JsonElement hotel: data) {
                        JsonObject hotelObj = (JsonObject) hotel;
                        hotels.add(new Hotel(
                                hotelObj.get("ID").getAsString(),
                                hotelObj.get("hotelNm").getAsString(),
                                hotelObj.get("phone").getAsString(),
                                hotelObj.get("cityID").getAsString(),
                                null
                        ));
                    }
                    setList();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                System.out.println("==========");
                System.out.println(t.getMessage());
            }
        });
    }

    private void setList(){
        AddTravelHotelAdapter adapter = new AddTravelHotelAdapter(this,(CreateTravelActivity)getActivity(),hotels);
        hotelListView.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
