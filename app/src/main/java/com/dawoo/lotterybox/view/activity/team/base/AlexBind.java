package com.dawoo.lotterybox.view.activity.team.base;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by alex on 18-4-20.
 *
 * @author alex
 */

final class AlexBind {

    private static Unbinder bind;

    static void bindClass(Class clazz, Context context) {

    }

    private static void bindClassView(Class clazz, Context context) {
        BindLayout declaredAnnotation = (BindLayout) clazz.getAnnotation(BindOthersView.class);
        Field[] fields = clazz.getFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                BindOthersView annotation = field.getAnnotation(BindOthersView.class);
                if (annotation != null) {
                    View inflate = LayoutInflater.from(context).inflate(annotation.value(), null);
                    try {
                        field.setAccessible(true);
                        field.set(clazz, inflate);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static void bindActivity(Activity activity) {
        bindActivityLayout(activity);
        bindActivityView(activity);
    }

    private static void bindActivityLayout(Activity activity) {
        Class aClass = activity.getClass();
        BindLayout annotation = (BindLayout) aClass.getAnnotation(BindLayout.class);
        if (annotation != null) {
            activity.setContentView(annotation.value());
            ButterKnife.bind(activity);
        }
    }

    private static void bindActivityView(Activity activity) {
        Field[] declaredFields = activity.getClass().getDeclaredFields();
        if (declaredFields != null && declaredFields.length > 0) {
            for (Field f : declaredFields) {
                BindOthersView annotation = f.getAnnotation(BindOthersView.class);
                if (annotation != null) {
                    View inflate = LayoutInflater.from(activity).inflate(annotation.value(), null);
                    if (inflate != null) {
                        try {
                            f.setAccessible(true);
                            f.set(activity, inflate);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

    static View bindFragment(Fragment fragment, @NonNull LayoutInflater inflater, @Nullable ViewGroup container){
        View view = bindFragmentLayout(fragment, inflater, container);
        bindFragmentView(fragment,inflater,container);
        return view;
    }

   private static View bindFragmentLayout(Fragment fragment, @NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        Class fragmentClass = fragment.getClass();
        BindLayout annotation = (BindLayout) fragmentClass.getAnnotation(BindLayout.class);
        if (annotation != null) {
            View inflate = inflater.inflate(annotation.value(), container, false);
            bind = ButterKnife.bind(fragment, inflate);
            return inflate;
        }
        return null;
    }

  private   static void bindFragmentView(Fragment fragment, @NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        Field[] declaredFields = fragment.getClass().getDeclaredFields();
        if (declaredFields != null && declaredFields.length > 0) {
            for (Field field : declaredFields) {
                BindOthersView declaredAnnotation = field.getAnnotation(BindOthersView.class);

                if (declaredAnnotation != null) {
                    View inflate = inflater.inflate(declaredAnnotation.value(), container, false);
                    if (inflate != null) {
                        try {
                            field.setAccessible(true);
                            field.set(fragment, inflate);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }
    static void unBind(){
        bind.unbind();
    }
}
