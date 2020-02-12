package com.asiapay.payyobusiness.home;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.login.ApplicationClass;
import com.asiapay.payyobusiness.model.User;
import com.asiapay.payyobusiness.network.Constants;
import com.asiapay.payyobusiness.utility.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileUpdateFragment extends Fragment implements ProfileUpdateContracts.View {

    private AppCompatEditText edtUpdateName, edtUpdateEmail, edtUpdateMobNo;
    private AppCompatButton btnSaveProfile;
    private Context mContext;
    private View view;
    private ProfileUpdatePresenter ProfilePresenter;
    private ContentLoadingProgressBar loadingProgressBar;
    private Bitmap bitmap = null;
    private boolean ISImageAvailable = false;
    private Uri mImageCaptureUri;
    private static final int PICK_FROM_CAMERA = 1000;
    private int SELECT_FILE = 1;
    private String path = "";
    private FloatingActionButton actionButton;
    private CircleImageView imageViewProfile;
    User updateProfile;
    private String strUserId, strUserProfilePath, userChoosenTask = "", strBankName, strAccountNo, strIfscCode;
    private Toolbar toolbar;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int MY_GALLERY_PERMISSION_CODE = 200;
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    public ProfileUpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.profile_bottom_nav_fragment, container, false);
        view = inflater.inflate(R.layout.profile_bottom_nav_fragment, container, false);
        initUI();
        strUserId = ApplicationClass.getInstance().getPreferences().getString(Constants.USER_ID, null);
        strUserProfilePath = ApplicationClass.getInstance().getPreferences().getString(Constants.USER_PROFILE_PATH, null);
        if (strUserProfilePath.equalsIgnoreCase("")) {
        } else {

            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bitmap = decodeFile(strUserProfilePath, bmOptions);
            imageViewProfile.setImageBitmap(bitmap);
        }
        addEventListener();
        updateProfile = new User();
        //  ApplicationClass.getInstance().payForDataSource.updateProfilePic(updateProfile);
        ProfilePresenter = new ProfileUpdatePresenter(this, mContext);
        return view;
    }


    @Override
    public void showInvalidInputMessage(String forComponent, String message) {
        if (forComponent.equals("userName")) {
            edtUpdateEmail.setError(message);
        } else if (forComponent.equals("EmailID")) {
            edtUpdateMobNo.setError(message);
        } else if (forComponent.equals("Mobile No")) {
            edtUpdateMobNo.setError("Mobile No");
        }

    }

    @Override
    public void showAPIErrorMessage(String message) {

    }

    @Override
    public void UploadSuccess(String updateProfile) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bitmap = decodeFile(updateProfile, bmOptions);
        imageViewProfile.setImageBitmap(bitmap);

    }


    public void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Delete"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    galleryIntent();
                } else if (items[item].equals("Delete")) {
                    SharedPreferences pref = ApplicationClass.getInstance().getPreferences();
                    SharedPreferences.Editor pref_edit = pref.edit();
                    pref_edit.putString(Constants.USER_PROFILE_PATH, "");
                    pref_edit.commit();
                    imageViewProfile.setImageResource(0);

                  /*  File fdelete = new File(path);
                    if (fdelete.exists()) {
                        if (fdelete.delete()) {
                            System.out.println("file Deleted :" + file_dj_path);
                        }
                    }*/

                    dialog.dismiss();
                }

            }
        });
        builder.show();
    }

    @Override
    public void initUI() {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Update Profile");
        edtUpdateName = view.findViewById(R.id.edtUpdateName);
        edtUpdateEmail = view.findViewById(R.id.edtUpdateEmail);
        edtUpdateMobNo = view.findViewById(R.id.edtUpdateMobNo);
        btnSaveProfile = view.findViewById(R.id.btnSaveProfile);
        loadingProgressBar = view.findViewById(R.id.progressBar);
        actionButton = view.findViewById(R.id.floatingActionButton);
        imageViewProfile = view.findViewById(R.id.imageView_profile);

        strBankName = ApplicationClass.getInstance().getPreferences().getString(Constants.USER_NAME, null);
        strAccountNo = ApplicationClass.getInstance().getPreferences().getString(Constants.USER_EMAIL, null);
        strIfscCode = ApplicationClass.getInstance().getPreferences().getString(Constants.USER_MOBILE_No, null);
        edtUpdateName.setText(strBankName);
        edtUpdateEmail.setText(strAccountNo);
        edtUpdateMobNo.setText(strIfscCode);
    }

    @Override
    public void addEventListener() {

        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User updateProfile = new User();
                updateProfile.setUserName(edtUpdateName.getText().toString());
                updateProfile.seteMail(edtUpdateEmail.getText().toString());
                updateProfile.setMobileNo(edtUpdateMobNo.getText().toString());
                updateProfile.setImagePath(path);
                ProfilePresenter.onSaveButtonClick(updateProfile);
            }
        });
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (arePermissionsEnabled()) {
                        //  permissions granted, continue flow normally
                        selectImage();
                    } else {
                        requestMultiplePermissions();
                    }
                }

              /*  if (ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{android.Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                        requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_GALLERY_PERMISSION_CODE);
                    }
                }*/ /*else if () {
                    // Do the file write
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    }

                }*/// else {

                //  }

            }
        });

    }

    @Override
    public void showProgress() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideKeyboard() {
        Util.hideKeyboard(btnSaveProfile);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    if (shouldShowRequestPermissionRationale(permissions[i])) {
                      /*  new AlertDialog.Builder(mContext)
                                .setMessage("Permission Denied")
                                .setPositiveButton("Allow", (dialog, which) -> requestMultiplePermissions())
                                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                                .create()
                                .show();*/

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                        // set title
                        alertDialogBuilder.setTitle("permission");
                        // set dialog message
                        alertDialogBuilder
                                .setMessage("Permission Denied !")
                                .setCancelable(false)
                                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // if this button is clicked, close
                                        // current activity
                                        // getActivity().finish();
                                        requestMultiplePermissions();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // if this button is clicked, just close
                                        // the dialog box and do nothing
                                        dialog.dismiss();
                                    }
                                });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();
                    }


                }
                return;
            }
        }
        //all is good, continue flow
    }



    private void galleryIntent() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECT_FILE);
    }


    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                mImageCaptureUri = FileProvider.getUriForFile(getActivity(), "com.asiapay.payyobusiness.fileprovider", photoFile);
                path = photoFile.getAbsolutePath();
                List<ResolveInfo> resInfoList = getActivity().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    getActivity().grantUriPermission(packageName, mImageCaptureUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                intent.putExtra("return-data", true);
                try {
                    startActivityForResult(intent, PICK_FROM_CAMERA);
                } catch (Exception e) {
                    e.getMessage();
                }

            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("requestCode", "" + requestCode);
        Log.i("data", "" + data);
        if (resultCode == Activity.RESULT_OK) {
            ISImageAvailable = true;
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == PICK_FROM_CAMERA)
                setPic();
            Log.i("path for camera", "" + path);
            Log.i("Bitmap", "" + bitmap);

        }

    }

    private void onSelectFromGalleryResult(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = mContext.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        path = cursor.getString(columnIndex);
        cursor.close();
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bitmap = decodeFile(path, bmOptions);
        imageViewProfile.setImageBitmap(bitmap);
        //store path in session
        SharedPreferences.Editor edit = ApplicationClass.getInstance().getPreferences().edit();
        edit.putString(Constants.USER_PROFILE_PATH, path);
        edit.commit();
    }

    private void setPic() {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bitmap = decodeFile(path, bmOptions);
        User updateProfile = new User();
        updateProfile.setImagePath(path);
        //  ProfilePresenter.insertPath(updateProfile);
        imageViewProfile.setImageBitmap(bitmap);
        //store path in session
        SharedPreferences.Editor edit = ApplicationClass.getInstance().getPreferences().edit();
        edit.putString(Constants.USER_PROFILE_PATH, path);
        edit.commit();

    }


    private Bitmap decodeFile(String path2, BitmapFactory.Options o) {
        Bitmap b = null;
        try {
            // Decode image size
            // BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            FileInputStream fis = new FileInputStream(path2);
            BitmapFactory.decodeStream(fis, null, o);
            fis.close();
            final int IMAGE_MAX_SIZE = 500;
            int scale = 1;
            if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
                scale = (int) Math.pow(2, (int) Math.round(Math.log(IMAGE_MAX_SIZE / (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
            }
            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            fis = new FileInputStream(path2);
            b = BitmapFactory.decodeStream(fis, null, o2);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    private boolean arePermissionsEnabled() {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    private void requestMultiplePermissions() {
        List<String> remainingPermissions = new ArrayList<String>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                remainingPermissions.add(permission);
            }
        }
        requestPermissions(remainingPermissions.toArray(new String[remainingPermissions.size()]), 101);
    }
}
