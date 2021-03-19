package com.example.profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Output;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class FriendForm extends AppCompatActivity {

    private SimpleDateFormat dateFormat;
    private EditText nama, wsomething, dtResult;
    private Button btnSave, btnLoad, btnClear, btnChange, btnPick;
    private RadioButton radLk,radPr;
    private CheckBox chbSekolah, chbRumah;
    private ImageView ivImage;
    private Switch email;
    private Integer REQUEST_CAMERA=1, SELECT_FILE=0;
    private File picFile;
    private RadioGroup radioGroup;
    private static final String FILE_NAME="save.txt";
    private String path="", photoType="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_form);
        nama=(EditText)findViewById(R.id.txt_friend_name);
        email=(Switch)findViewById(R.id.friend_email);
        wsomething=(EditText)findViewById(R.id.write_something);
        btnSave=(Button)findViewById(R.id.btnAFriendSave);
        btnClear=(Button)findViewById(R.id.btnAFriendClear);
        btnChange=(Button)findViewById(R.id.btnChangePhoto);
        radioGroup=(RadioGroup)findViewById(R.id.rgJkel);
        radLk=(RadioButton)findViewById(R.id.rad_laki);
        radPr=(RadioButton)findViewById(R.id.rad_perempuan);
        chbSekolah=(CheckBox)findViewById(R.id.chb_tSekolah);
        chbRumah=(CheckBox)findViewById(R.id.chb_tRumah);
        dtResult=(EditText) findViewById(R.id.dtPicker_tgl_lahir);
        btnPick=(Button)findViewById(R.id.btnPick);
        ivImage=(ImageView)findViewById(R.id. friend_image);
        btnLoad=(Button)findViewById(R.id.btnAFriendLoad);

        dateFormat=new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btnPick.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
    }

    private void selectImage()
    {
        final CharSequence[] items={"Camera","Gallery","Cancel"};
        AlertDialog.Builder builder=new AlertDialog.Builder(FriendForm.this);
        builder.setTitle("Change Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(items[i].equals("Camera"))
                {
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CAMERA);
                }
                else if(items[i].equals("Gallery"))
                {
                    Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent,SELECT_FILE);
                }
                else if(items[i].equals("Cancel"))
                {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        try
        {
            if(resultCode== Activity.RESULT_OK)
            {
                if(requestCode==REQUEST_CAMERA)
                {
                    Bundle bundle=data.getExtras();
                    final Bitmap bmp=(Bitmap)bundle.get("data");
                    ivImage.setImageBitmap(bmp);
                    photoType="camera";
                }
                else if(requestCode==SELECT_FILE)
                {
                    Uri uri = data.getData();
                    path = getRealPathFromURI(this,uri);
                    photoType="select";
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String getRealPathFromURI(Context context, Uri uri)
    {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null,
                null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return null;
    }

    private void showDateDialog()
    {
        Calendar calendar= Calendar.getInstance();
        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar newDate= Calendar.getInstance();
                newDate.set(year,month,day);
                dtResult.setText(dateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void save()
    {
        String tSekolah="",tRumah="", semail="";
        String sname=nama.getText().toString();
        String jenis="";

        if(chbSekolah.isChecked())
        {
            tSekolah=chbSekolah.getText().toString()+" ";
            if(chbRumah.isChecked())
            {
                tRumah=chbRumah.getText().toString()+" ";
            }
        }
        else if(chbRumah.isChecked())
        {
            tRumah=chbRumah.getText().toString()+" ";
            if(chbSekolah.isChecked())
            {
                tSekolah+=chbSekolah.getText().toString()+" ";
            }
        }
        else if(!chbSekolah.isChecked()&&!chbRumah.isChecked())
        {
            tSekolah="";
            tRumah="";
        }

        if(radLk.isChecked())
        {
            jenis=radLk.getText().toString();
        }
        else if(radPr.isChecked())
        {
            jenis=radPr.getText().toString();
        }

        if(email.isChecked())
        {
            semail="true";
        }
        else
        {
            semail="false";
        }

        String tgllhr=dtResult.getText().toString();
        String wSomething=wsomething.getText().toString();
        FileOutputStream fos=null;
        String text=sname+"\n"+photoType+"\n"+path+"\n"+jenis+"\n"+tSekolah+"\n"+tRumah+"\n"+semail+"\n"+tgllhr+"\n"+wSomething;
        try {
            fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "File Saved as save.txt", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException eio) {
            eio.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void clear()
    {
        nama.setText("");
        radioGroup.clearCheck();
        chbSekolah.setChecked(false);
        chbRumah.setChecked(false);
        dtResult.setText("");
        wsomething.setText("");
        ivImage.setImageDrawable(null);
        email.setChecked(false);
    }


    public void load () {
        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;
            String[] data = new String[9];
            int i = 0;

            while ((text = br.readLine()) != null) {
                data[i] = text;
                i++;
            }

            nama.setText(data[0]);
            if(data[1]=="camera")
            {
                byte[] decodedString = Base64.decode(data[2], Base64.URL_SAFE );
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                ivImage.setImageBitmap(decodedByte);
            }
            else if(data[1]=="select")
            {
                byte[] decodedString = Base64.decode(data[2], Base64.URL_SAFE );
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                ivImage.setImageBitmap(decodedByte);
            }

            if (data[3].equals("Laki-laki")) {
                radLk.setChecked(true);
            } else if(data[3].equals("Perempuan")){
                radPr.setChecked(true);
            }

            chbRumah.setChecked(!data[5].equals("NOT") ? true : false);
            chbSekolah.setChecked(!data[4].equals("NOT") ? true : false);
            email.setChecked(!data[6].equals("false") ? true : false);
            dtResult.setText(data[7]);
            wsomething.setText(data[8]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,"File Not Found !", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}