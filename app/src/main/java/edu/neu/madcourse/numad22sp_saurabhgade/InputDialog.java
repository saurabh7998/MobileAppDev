package edu.neu.madcourse.numad22sp_saurabhgade;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;


public class InputDialog extends AppCompatDialogFragment {

    private EditText editTextUrl;
    private InputDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.input_dialog, null);

        builder.setView(view).setTitle("Add URL").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               String editUrlText = editTextUrl.getText().toString();
               listener.applyTexts(editUrlText);
            }
        });

        editTextUrl = view.findViewById(R.id.editUrlName);
        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (InputDialogListener) context;
    }

    public interface InputDialogListener {
        void applyTexts(String url);
    }


}
