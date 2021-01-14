package com.example.siluetas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.siluetas.bd.SQLite;
import com.example.siluetas.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    private EditText name, edad, pais, emai, pass, id;
    private Button aceptar, cancelar;
    private ImageView imagen;
    private Uri photoURI;
    String currentPhotoPath, img = "";
    public static final int REQUEST_TAKE_PHOTO = 1;
    MainActivity main;
    private User user;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;


    public SQLite sqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mProgress = new ProgressDialog(this);
        setContentView(R.layout.activity_register);
        sqlite = new SQLite(getApplicationContext());
        aceptar = (Button) findViewById(R.id.registerme);
        cancelar = (Button) findViewById(R.id.back_to_login);
        name = (EditText) findViewById(R.id.PersonName);
        edad = (EditText) findViewById(R.id.PersonOld);
        pais = (EditText) findViewById(R.id.Country);
        emai = (EditText) findViewById(R.id.PersonEmail);
        pass = (EditText) findViewById(R.id.PersonPasword);
        id = (EditText) findViewById(R.id.EditID);
        imagen = (ImageView) findViewById(R.id.imageViewPersona);
        user = new User() ;
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (tomarFoto.resolveActivity(getApplication().getPackageManager())!= null){
                    File photoFile= null;
                    try {
                        photoFile = createImageFile();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error en Fotografia", Toast.LENGTH_LONG).show();
                    }
                    if (photoFile != null){
                        photoURI = FileProvider.getUriForFile(getApplicationContext(), "com.example.siluetas", photoFile);
                        tomarFoto.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(tomarFoto, REQUEST_TAKE_PHOTO);
                    }
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(RegisterActivity.this, LoginActivity.class);
                register.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(register);
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id.getText().toString().equals("")||name.getText().toString().equals("") ||
                        edad.getText().toString().equals("") || pais.getText().toString().equals("") || emai.getText().toString().equals("")
                        || pass.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Requiere los campos", Toast.LENGTH_LONG).show();
                } else {
                    int idd = 0;
                    String nom = name.getText().toString().toUpperCase();
                    user.setNombre(nom); // Tada! :o asi con todos? Yes
                    String fech = edad.getText().toString().toUpperCase();
                    // Le estabas pasando algo que aún no tenías a no ya vi en que la cague xD
                    user.setEdad(fech);
                    String paiss = pais.getText().toString().toUpperCase();
                    user.setPais(paiss);
                    String email = emai.getText().toString().toUpperCase();
                    user.setEmail(email);
                    String pas = pass.getText().toString().toUpperCase();
                    user.setPassword(pas);


                    sqlite.abrir();
                    if (sqlite.addRegistroUsuario(idd, nom, fech, paiss, email, pas, img)) {
                        Toast.makeText(getApplicationContext(), "Datos almacenados", Toast.LENGTH_LONG).show();
                        starRegister();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error de almacenamiento", Toast.LENGTH_LONG).show();
                    } //ese va a ser el problema mas grande


                    sqlite.cerrar();
                    //Qué desmadre jaja es para la interna todo eso jajaja, ya con eso queda?
                }
            }

        });

    }
    private void starRegister(){
        final String nombre = name.getText().toString().trim();
        final String mail = emai.getText().toString().trim();
        String contra = pass.getText().toString().trim();

        if(!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(mail) && !TextUtils.isEmpty(contra)){


            mProgress.setMessage("Registrando, espere un momento...");
            mProgress.show();
            mAuth.createUserWithEmailAndPassword(mail, contra)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mProgress.dismiss();
                            if(task.isSuccessful()){
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUiser(user.getUid());

                                Toast.makeText(RegisterActivity.this,"Se ha registrado el usuario con el email: "+ emai.getText(),Toast.LENGTH_LONG).show();
                            }else{

                                Toast.makeText(RegisterActivity.this,"No se pudo registrar el usuario ",Toast.LENGTH_LONG).show();
                            }
                            mProgress.dismiss();

                        }


                    });

        }
    }
    private void updateUiser(String  ui) {
        user.insert(RegisterActivity.this,ui);
    }
    private File createImageFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "FP_" + timeStamp + "_";
        File storageDir = getApplication().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        currentPhotoPath = image.getAbsolutePath();
        Toast.makeText(getApplicationContext(), "o"+ img,Toast.LENGTH_LONG).show();
        return image;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagen.setImageBitmap(imageBitmap);
            try {
                imagen.setImageURI(photoURI);
                img = currentPhotoPath;
                Toast.makeText(getApplicationContext(), "onActivityResult safe" + img, Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(getApplicationContext(), "Fallo en onACtivityResult", Toast.LENGTH_LONG).show();
            }

        }
    }



}