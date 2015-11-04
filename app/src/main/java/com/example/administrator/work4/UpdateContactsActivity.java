package com.example.administrator.work4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContactsActivity extends AppCompatActivity {
private EditText name,num,qq,danwei,dizhi;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contacts);
        name= (EditText) findViewById(R.id.name);
        num= (EditText) findViewById(R.id.num);
        danwei= (EditText) findViewById(R.id.danwei);
        qq= (EditText) findViewById(R.id.qq);
        dizhi= (EditText) findViewById(R.id.dizhi);
        Bundle localBundle=getIntent().getExtras();
        int id=localBundle.getInt("user_ID");
        ContactsTable ct=new ContactsTable(this);
        user=ct.getUserByID(id);
        name.setText(user.getName());
        num.setText(user.getNum());
        qq.setText(user.getQq());
        dizhi.setText(user.getDizhi());
        danwei.setText(user.getDanwei());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       menu.add(Menu.NONE,1,Menu.NONE,"保存");
        menu.add(Menu.NONE,2,Menu.NONE,"返回");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                if(!name.getText().toString().equals("")){
                    user.setName(name.getText().toString());
                    user.setNum(num.getText().toString());
                    user.setDizhi(dizhi.getText().toString());
                    user.setQq(qq.getText().toString());
                    user.setDanwei(danwei.getText().toString());
                    ContactsTable ct=new ContactsTable(UpdateContactsActivity.this);
               if(ct.updateUser(user)){
                   Toast.makeText(UpdateContactsActivity.this,"修改成功",Toast.LENGTH_LONG).show();
               }else {
                   Toast.makeText(UpdateContactsActivity.this,"修改失败",Toast.LENGTH_LONG).show();
               }
                }else {
                    Toast.makeText(UpdateContactsActivity.this,"数据不能为空",Toast.LENGTH_LONG).show();
                }
                break;
            case 2:
                finish();
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
