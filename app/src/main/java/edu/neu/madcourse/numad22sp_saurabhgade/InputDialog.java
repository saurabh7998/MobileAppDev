package edu.neu.madcourse.numad22sp_saurabhgade;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.snackbar.Snackbar;


public class InputDialog extends AppCompatDialogFragment {

    private EditText editTextUrl;
    private EditText editTextUrlName;
    private boolean invalidUrlFlag;
    private InputDialogListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.input_dialog, null);

        editTextUrl = view.findViewById(R.id.editTextUrl);
        editTextUrlName = view.findViewById(R.id.editTextUrlName);

        editTextUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // whenever text size changes it will check
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Patterns.WEB_URL.matcher(editTextUrl.getText().toString()).matches()) {
                    invalidUrlFlag = false;
                } else {
                    // otherwise show error of invalid url
                    editTextUrl.setError("Invalid URL");
                    invalidUrlFlag = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        builder.setView(view)
                .setTitle("Add URL")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (invalidUrlFlag) {
                            Toast.makeText(getContext(), "Cannot add the url", Toast.LENGTH_SHORT).show();
                        } else {
                            String editUrlText = editTextUrl.getText().toString();
                            String editUrlNameText = editTextUrlName.getText().toString();
                            listener.applyTexts(editUrlText, editUrlNameText);
                        }


                    }
                });


        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (InputDialogListener) context;
    }

    public interface InputDialogListener {
        void applyTexts(String url, String urlName);
    }


}
