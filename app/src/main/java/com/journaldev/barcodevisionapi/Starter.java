package com.journaldev.barcodevisionapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.journaldev.barcodevisionapi.databinding.ActivityLoginBinding;

public class Starter extends AppCompatActivity {

    ActivityLoginBinding binding;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = new Database(this);

        binding.login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.username.getText().toString();
                String password = binding.password.getText().toString();

                if(name.equals("") || password.equals((""))){
                    Toast.makeText(Starter.this,"Lütfen kullanıcı adı ve şifrenizi giriniz",Toast.LENGTH_SHORT).show();
                }else{
                     boolean checkName = database.checkName(name);

                     if(checkName == false){
                         boolean insert = database.veriEkle(name,password);
                         Toast.makeText(Starter.this,"Hesap oluşturuldu",Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                     }
                }
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.username.getText().toString();
                String password = binding.password.getText().toString();

                if(name.equals("") || password.equals((""))){
                    Toast.makeText(Starter.this,"Lütfen kullanıcı adı ve şifrenizi giriniz",Toast.LENGTH_SHORT).show();
                }else{
                    boolean check = database.checkPassword(name,password);
                    if(check == true){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Starter.this,"Kullanıcı adı veya şifre hatalı",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}
