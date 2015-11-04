package com.example.administrator.work4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

private EditText name,num,qq,danwei,dizhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name= (EditText) findViewById(R.id.name);
        num= (EditText) findViewById(R.id.num);
        qq= (EditText) findViewById(R.id.qq);
        danwei= (EditText) findViewById(R.id.danwei);
        dizhi= (EditText) findViewById(R.id.dizhi);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(0, 1, 1, "保存");
        menu.add(0,2,2,"返回");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case 1:
                if(!name.getText().toString().equals("")){
                    User user=new User();
                    user.setName(name.getText().toString());
                    user.setNum(num.getText().toString());
                    user.setQq(qq.getText().toString());
                    user.setDanwei(danwei.getText().toString());
                    user.setDizhi(dizhi.getText().toString());
                    ContactsTable ct=new ContactsTable(this);
                    if(ct.addData(user)){
                        Toast.makeText(this,"添加成功 ",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(this,"添加失败 ",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this,"打个名字进去啊 ",Toast.LENGTH_LONG).show();
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
