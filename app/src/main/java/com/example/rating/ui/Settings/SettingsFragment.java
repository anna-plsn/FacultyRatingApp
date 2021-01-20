package com.example.rating.ui.Settings;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.rating.R;
import com.example.rating.SessionManager;
import com.example.rating.ui.Login.LoginActivity;


public class SettingsFragment extends Fragment {
    Button exit;
    Button error;
    Button password;

    SessionManager manager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_settings, container, false);

        manager = new SessionManager(getActivity());
        exit = (Button) root.findViewById(R.id.exit);
        password = (Button) root.findViewById(R.id.reset_pass_btn);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogTheme);
                builder.setTitle("Вы уверены, что хотите выйти?")
                        .setCancelable(false)
                        .setPositiveButton("Да",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        manager.logoutUser();
                                        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                                        getActivity().finish();
                                        dialog.cancel();
                                    }
                                })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity( new Intent(getActivity(), ChangePasswordActivity.class));
            }
        });

        return root;
    }


}