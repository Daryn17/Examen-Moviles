package cr.ac.itcr.examen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActualizarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActualizarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActualizarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ActualizarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActualizarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActualizarFragment newInstance(String param1, String param2) {
        ActualizarFragment fragment = new ActualizarFragment();
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
        View view = inflater.inflate(R.layout.fragment_actualizar,
                container, false);
        final Button button2 = (Button) view.findViewById(R.id.btnModificar);
        final TextView txtNomVi = (TextView) view.findViewById(R.id.txtNombreVi);
        final TextView txtNomOrq = (TextView) view.findViewById(R.id.txtNombreMo);
        final TextView txtCantPet = (TextView) view.findViewById(R.id.txtCantPetMo);
        final TextView txtColor = (TextView) view.findViewById(R.id.txtColorMo);
        final TextView txtLugar = (TextView) view.findViewById(R.id.txtLugarMo);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String nombreVi = txtNomVi.getText().toString();
                String nombre = txtNomOrq.getText().toString();
                String cantPet = txtCantPet.getText().toString();
                String color = txtColor.getText().toString();
                String lugar = txtLugar.getText().toString();
                Orquidea newOrq = new Orquidea(nombre, cantPet, color,lugar);
                Admi_db.actualizarOrq(nombreVi,newOrq);
            }
        });
        return view;
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
