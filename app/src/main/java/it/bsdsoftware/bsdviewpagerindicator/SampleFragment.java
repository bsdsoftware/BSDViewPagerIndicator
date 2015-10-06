package it.bsdsoftware.bsdviewpagerindicator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SampleFragment extends Fragment {


    public SampleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sample, container, false);
        Bundle args = getArguments();
        if (args != null){
            int page = args.getInt("page_key");
            TextView textView = (TextView) rootView.findViewById(R.id.sample_text);
            textView.setText(""+page);
        }
        return rootView;
    }


}
