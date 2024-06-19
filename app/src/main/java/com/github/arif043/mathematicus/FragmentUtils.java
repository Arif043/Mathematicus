package com.github.arif043.mathematicus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentUtils {

    private FragmentUtils(){}

    public static void bind(AppCompatActivity activity, Fragment fragment, int id, String tag, boolean backstack) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.add(id, fragment, tag);
        if (backstack) transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void rebind(AppCompatActivity activity, Fragment fragment, int id, String tag, boolean backstack) {
        bind(activity, fragment, id, tag, backstack);
        remove(activity, tag);
    }

    //Das bereits existierende Keyboard-Objekt entfernen
    public static void remove(AppCompatActivity activity, String tag) {
        FragmentTransaction transactionRemove = activity.getSupportFragmentManager().beginTransaction();
        Fragment old = activity.getSupportFragmentManager().findFragmentByTag(tag);
        if (old != null) transactionRemove.remove(old).commit();
    }
}