package com.panner.common_base.helper;

import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;

import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo2.entities.FileData;
import com.miguelbcr.ui.rx_paparazzo2.entities.Response;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.panner.common_base.R;
import com.panner.common_base.listener.Callback2;
import com.panner.common_base.model.SpinnerItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;


/**
 * 图片选择
 *
 * @author panzhijie
 * @version 2019-06-19 14:40
 */
public class TakeImagesHelper {
    private List<SpinnerItem> items;
    Callback2<FileData> mCallback;
    DialogPlus mDialogPlus;

    private TakeImagesHelper(Context context) {
        items = new ArrayList<>();
        items.add(new SpinnerItem(context.getString(R.string.str_open_photo)));
        items.add(new SpinnerItem(context.getString(R.string.str_open_camera)));
    }

    public static TakeImagesHelper with(Activity activity) {
        return new TakeImagesHelper(activity.getApplicationContext()).createDialog(activity);
    }

    public static TakeImagesHelper with(Fragment fragment) {
        return new TakeImagesHelper(fragment.getContext()).createDialog(fragment);
    }

    private TakeImagesHelper createDialog(final Activity activity) {
        mDialogPlus = DialogPlus.newDialog(activity).setContentHolder(new ViewHolder(R.layout.view_holder_take_picture)).create();
        Button camera = (Button) mDialogPlus.findViewById(R.id.take_picture_from_camera);
        Button file = (Button) mDialogPlus.findViewById(R.id.take_picture_from_file);
        Button cancel = (Button) mDialogPlus.findViewById(R.id.take_picture_cancel);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPaparazzo.single(activity)
                        .useInternalStorage()
                        .usingCamera()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Response<Activity, FileData>>() {
                            @Override
                            public void accept(Response<Activity, FileData> response) throws Exception {
                                if (response.resultCode() != RESULT_OK) {
                                    return;
                                }
                                mCallback.onSuccess(response.data());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                mDialogPlus.dismiss();
                            }
                        });
            }
        });
        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPaparazzo.single(activity)
                        .crop()
                        .usingGallery()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Response<Activity, FileData>>() {
                            @Override
                            public void accept(Response<Activity, FileData> response) throws Exception {
                                if (response.resultCode() != RESULT_OK) {
                                    return;
                                }
                                mCallback.onSuccess(response.data());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                mDialogPlus.dismiss();
                            }
                        });
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogPlus.dismiss();
            }
        });

        return this;
    }

    public void showTakeImageDialog(Callback2<FileData> callback) {
        mCallback = callback;
        mDialogPlus.show();
    }

    private TakeImagesHelper createDialog(final Fragment fragment) {
        mDialogPlus = DialogPlus.newDialog(fragment.getContext()).setContentHolder(new ViewHolder(R.layout.view_holder_take_picture)).create();
        Button camera = (Button) mDialogPlus.findViewById(R.id.take_picture_from_camera);
        Button file = (Button) mDialogPlus.findViewById(R.id.take_picture_from_file);
        Button cancel = (Button) mDialogPlus.findViewById(R.id.take_picture_cancel);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPaparazzo.single(fragment)
                        .useInternalStorage()
                        .usingCamera()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Response<Fragment, FileData>>() {
                            @Override
                            public void accept(Response<Fragment, FileData> response) throws Exception {
                                if (response.resultCode() != RESULT_OK) {
                                    return;
                                }
                                mCallback.onSuccess(response.data());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                mDialogPlus.dismiss();
                            }
                        });
            }
        });
        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPaparazzo.single(fragment)
                        .crop()
                        .usingGallery()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Response<Fragment, FileData>>() {
                            @Override
                            public void accept(Response<Fragment, FileData> response) throws Exception {
                                if (response.resultCode() != RESULT_OK) {
                                    return;
                                }
                                mCallback.onSuccess(response.data());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                mDialogPlus.dismiss();
                            }
                        });
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogPlus.dismiss();
            }
        });

        return this;
    }

}
