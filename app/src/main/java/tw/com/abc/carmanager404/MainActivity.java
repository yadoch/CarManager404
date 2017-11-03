package tw.com.abc.carmanager404;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<CCarManager> list = new ArrayList<CCarManager>();
    private int position =0;
    private Spinner spnCarNum;
    private String strCarNum;
    private TextView tvResult;
    private EditText edDate,edUser,edStartKm,edEndKm,edGasMoney,edMemo;
    private boolean isPermissionOK;
    private ArrayAdapter<CharSequence> arrCarNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();

        // sd 卡事前處理
        if (ContextCompat.checkSelfPermission(this,
                // 要判斷的條件(網路,外部儲存裝置....)
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            // no
            ActivityCompat.requestPermissions(this,
                    // 要判斷的條件(網路,外部儲存裝置....)
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    123);
        }else {
            isPermissionOK = true;
            init();
        }

    }
    private void initComponent(){
        // 選取日期
        edUser =(EditText) findViewById(R.id.eduser);
        edDate =(EditText) findViewById(R.id.eddate);
        edStartKm=(EditText) findViewById(R.id.edstartkm);
        edEndKm=(EditText) findViewById(R.id.edendkm);
        edGasMoney=(EditText) findViewById(R.id.edgasmoney);
        edMemo=(EditText) findViewById(R.id.edmemo);
        tvResult= (TextView) findViewById(R.id.tvresult);

        // 下拉選項初始化
        spinnerInit();
    }
    private void init(){
        if (!isPermissionOK) {
            finish();
        }else{
            go();
        }
        //Log.i("brad", "start");
    }

    private void go(){

    }



    public void btnOk(View view) {

        if ("".equals(edUser.getText().toString().trim())) {
            tvResult.setText(getString(R.string.lb_user) + "未填寫");

        } else {

            list.add(new CCarManager(
                    edDate.getText().toString(),
                    edUser.getText().toString(),
                    strCarNum,
                    edStartKm.getText().toString(),
                    edEndKm.getText().toString(),
                    edGasMoney.getText().toString(),
                    edMemo.getText().toString()
            ));

            position =list.size()-1;

            Log.i("geoff","position:"+position);

            //顯示結果
            tvResult.setText(getString(R.string.lb_user) + ":" + list.get(position).getcUser() + "\n" +
                    getString(R.string.lb_date) + ":" + list.get(position).getcDate() + "\n" +
                    getString(R.string.lb_carnum) + ":" + list.get(position).getcCarNum() + "\n" +
                    getString(R.string.lb_startkm) + ":" + list.get(position).getcStartKm()  + "\n" +
                    getString(R.string.lb_endkm) + ":" + list.get(position).getcEndKm()  + "\n" +
                    getString(R.string.lb_gasmoney) + ":" + list.get(position).getcGasMoney()  + "\n" +
                    getString(R.string.lb_memo) + ":" + list.get(position).getCdMemo()
            );

/*
            tvResult.setText(getString(R.string.lb_user) + ":" + edUser.getText() + "\n" +
                    getString(R.string.lb_carnum) + ":" + strCarNum + "\n" +
                    getString(R.string.lb_date) + ":" + edDate.getText() + "\n" +
                    getString(R.string.lb_startkm) + ":" + edStartKm.getText() + "\n" +
                    getString(R.string.lb_endkm) + ":" + edEndKm.getText() + "\n" +
                    getString(R.string.lb_gasmoney) + ":" + edGasMoney.getText() + "\n" +
                    getString(R.string.lb_memo) + ":" + edMemo.getText()
            );
*/
            // 要加入網路連線判斷,避免出錯
            updateDB();
            clean();


        }
    }

    // 寫入資料庫
    public void updateDB(){
        Log.i("geoff","List:"+list.get(0).getcUser().toString());

        new Thread(){
            @Override
            public void run() {
                int i =0;
                String strUser,strDate,strNum,strStartKm,strEndKm,strGasMoney,strMemo;
                strUser= list.get(i).getcUser().toString();
                strDate= list.get(i).getcDate().toString();
                strNum=list.get(i).getcCarNum().toString();
                strStartKm= list.get(i).getcStartKm().toString();
                strEndKm= list.get(i).getcEndKm().toString();
                strGasMoney= list.get(i).getcGasMoney().toString();
                strMemo= list.get(i).getcGasMoney().toString();
                for (i=0;i< list.size();i++){
                try {
                    //URL url = new URL("http://10.1.1.85/carmanager.aspx?CarUser=zipoer&CarNum=0934&CarDate=2001-01-09&StartKm=1000&EndKm=1200&GasMoney=200&CarMemo=1dqdqfdqdq");

                    URL url = new URL("http://10.1.1.85/carmanager.aspx?CarUser="+strUser+
                            "&CarNum="+strNum+
                            "&CarDate="+strDate+
                            "&StartKm="+strStartKm+
                            "&EndKm="+ strEndKm+
                            "&GasMoney="+strGasMoney+
                            "&CarMemo="+strMemo);

                    Log.i("geoff","http://10.1.1.85/carmanager.aspx?CarUser="+strUser+
                            "&CarNum="+strNum+
                            "&CarDate="+strDate+
                            "&StartKm="+strStartKm+
                            "&EndKm="+ strEndKm+
                            "&GasMoney="+strGasMoney+
                            "&CarMemo="+strMemo);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    conn.getInputStream();  // 一定要加,才能完成寫入動作
                } catch (Exception e) {
                    //e.printStackTrace();
                    Log.i("geoff",e.toString());
                }
                }
            }
        }.start();
    }

    //清除畫面欄位
    public void btnClean(View view){
        clean();
    }

    private void clean(){
        edDate.setText("");
        edUser.setText("");
        edStartKm.setText("");
        edEndKm.setText("");
        edGasMoney.setText("");
        edMemo.setText("");
        spnCarNum.setSelection(arrCarNum.getPosition("--請選擇--"));

    }
    public void btnTest(View view){

    }
    private AdapterView.OnItemSelectedListener spn =new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            strCarNum=parent.getSelectedItem().toString();
            Log.i("geoff",strCarNum);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    public void spinnerInit(){
        arrCarNum = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.carNumList,
                android.R.layout.simple_spinner_dropdown_item);
        spnCarNum =(Spinner)findViewById(R.id.spncarnum);

        spnCarNum.setAdapter(arrCarNum);
        spnCarNum.setOnItemSelectedListener(spn);
    }

    public void  btnDateOnClick(View view){
        //mTxtResult.setText("");
        // Calendar 是用Java
        Calendar now = Calendar.getInstance();
        DatePickerDialog datePickerDlg = new DatePickerDialog(MainActivity.this,
                aa,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        //DatePickerDialog 的內容設定
        datePickerDlg.setTitle("選擇日期");
        datePickerDlg.setMessage("請選擇適合您的日期");
        datePickerDlg.setIcon(android.R.drawable.ic_dialog_info);
        // 顯示Diaglog
        datePickerDlg.show();
    }
    private DatePickerDialog.OnDateSetListener aa =new DatePickerDialog.OnDateSetListener(){
        //
//        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            edDate.setText(Integer.toString(year) + "/" +
                    Integer.toString(month+1) + "/" +
                    Integer.toString(dayOfMonth) );
        }

    };
}
/*
//POST寫法,搞不定ASPX 端的問題暫時中止
    public void test6(View view){

        new Thread(){
            @Override
            public void run() {
                try {
                    MultipartUtility mu = new MultipartUtility("http://10.1.1.85/default2.aspx","UTF-8");
                    mu.addFormField("CarUser","Bill");
                    mu.addFormField("CarNum","NA-2222");
                    mu.addFormField("CarDate","NA-2222");
                    mu.addFormField("StartKm","NA-2222");
                    mu.addFormField("EndKm","NA-2222");
                    mu.addFormField("GasMoney","NA-2222");
                    mu.addFormField("CarMemo","NA-2222");
                    List<String> ret =mu.finish();
                    for (String line : ret ){
                        Log.i("geoff",line);
                    }
                } catch (IOException e) {

                    Log.i("geoff",e.toString());
                }
            }
        }.start();
    }
*/