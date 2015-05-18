package pl.javastart.ap.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class MultipleListElementsDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] colors = {"Czerwony", "Zielony", "Niebieski"};
        final boolean[] selected = {false, true, false};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Wybierz kolor");
        builder.setMultiChoiceItems(colors, selected, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                String selected = (isChecked) ? "Zaznaczono" : "Odznaczono";
                Toast.makeText(getActivity(), selected + " kolor " + colors[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Anuluj", null);

        return builder.create();
    }
}
