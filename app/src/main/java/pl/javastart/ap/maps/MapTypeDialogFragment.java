package pl.javastart.ap.maps;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.List;

public class MapTypeDialogFragment extends DialogFragment {

    public static final String ARG_CURRENT_MAP_TYPE = "arg.current.map.type";

    private static final List<String> types = new ArrayList<>();
    static {
        types.add(GoogleMap.MAP_TYPE_NONE, "Brak");
        types.add(GoogleMap.MAP_TYPE_NORMAL,  "Normalna");
        types.add(GoogleMap.MAP_TYPE_SATELLITE, "Satelita");
        types.add(GoogleMap.MAP_TYPE_TERRAIN, "Teren");
        types.add(GoogleMap.MAP_TYPE_HYBRID, "Hybryda");
    }

    private MapTypeSelectionListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int currentMapType = getArguments().getInt(ARG_CURRENT_MAP_TYPE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setSingleChoiceItems(types.toArray(new String[types.size()]), currentMapType, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.mapSelection(which);
                dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (MapTypeSelectionListener) getActivity();
        } catch (ClassCastException e) {
            throw new RuntimeException(getActivity().getClass().getSimpleName() + " must implement " + MapTypeSelectionListener.class.getSimpleName());
        }
    }

    public interface MapTypeSelectionListener {
        void mapSelection(int type);
    }
}
