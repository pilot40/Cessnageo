package cessna208b.pilot40.ru.cessnageo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import android.widget.Toast;
//import android.util.Log;

public class MainActivity extends Activity {

    EditText editDis;
    EditText editAlt;
    EditText editGob;
    EditText editGpol;
    EditText editGalt;
    EditText editTime;
    EditText editFuel;
    EditText editR3;
    EditText editA;
    EditText editB;
    EditText editC;
    EditText editD;
    EditText editB4;
    EditText editB6;
    EditText edittKs;
    EditText editGvzl;
    EditText editCtr;
    EditText editCg;
    Button btnRas1;
    Button btnRas2;

    int S;
    int Salt;
    float Tzap;
    float Tp;
    int Mm;
    int Hh;
    int Gpol;
    int Galt;
    int Gf;
    //второй таб
    int zerovs;
    double Ai;
    int wEk;
    int wR1;
    int wR2;
    int wR3;
    int Ba;
    int Bb;
    int Bc;
    int Bd;
    int B4;
    int B6;
    int Tks;
    float Gvz;
    float Gc;
    float Cg;

    String[] vs = {"67422", "67422_c1", "67422_c2"};
    String[] ek = {"2","3"};
    String[] r1 = {"0", "80", "160", "240"};
    String[] r2 = {"0", "80", "160", "240"};

//	private static final String TAG = "myLogs";

    //меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Операции для выбранного пункта меню
        switch (item.getItemId())
        {
            case R.id.action_settings:
                //settings();
                return true;
            case R.id.about_program:
                //showAbout();
                Toast.makeText(MainActivity.this,"Расчёты для Cessna 208B \n ООО Аэрогео \n Версия 1.1\n Автор: Зуев М.Г.\n pilot40@gmail.com \n Лицензия: GPL v.3",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // найдем View-элементы
        //Log.d(TAG, "найдем View-элементы");
        editDis = (EditText) findViewById(R.id.editDis);
        editAlt = (EditText) findViewById(R.id.editAlt);
        editGob = (EditText) findViewById(R.id.editGob);
        editGpol = (EditText) findViewById(R.id.editGpol);
        editGalt = (EditText) findViewById(R.id.editGalt);
        editTime = (EditText) findViewById(R.id.editTime);
        btnRas1 = (Button) findViewById(R.id.btnRas1);
        editFuel = (EditText) findViewById(R.id.editFuel);
        editA = (EditText) findViewById(R.id.editA);
        editB = (EditText) findViewById(R.id.editB);
        editC = (EditText) findViewById(R.id.editC);
        editD = (EditText) findViewById(R.id.editD);
        editB4 = (EditText) findViewById(R.id.editB4);
        editB6 = (EditText) findViewById(R.id.editB6);
        edittKs = (EditText) findViewById(R.id.edittKs);
        editGvzl = (EditText) findViewById(R.id.editGvzl);
        editCtr = (EditText) findViewById(R.id.editCtr);
        editCg = (EditText) findViewById(R.id.editCg);
        editR3 = (EditText) findViewById(R.id.editR3);
        // создаем обработчик нажатия
        OnClickListener oclbtnRas1 = new OnClickListener() {

            @Override
            public void onClick(View v) {

                // Расчитываем топливо и время

                try {

                    int S = Integer.parseInt(editDis.getText().toString());
                    int Salt = Integer.parseInt(editAlt.getText().toString());

                    Tzap = (float)Salt/270;//Время полета до запасного
                    Tp = (float)S/270; //Время полета до ап
                    Hh = (int)Tp;
                    Mm = (int)((Tp- Hh)*60);

                    Gpol = (int)(Tp*170*0.03)+(int)(Tp*170);
                    Galt = (int)(Tzap*170)+85;
                    Gf = Gpol + Galt;

                    editGob.setText(Integer.toString(Gf));
                    editGpol.setText(Integer.toString(Gpol));
                    editGalt.setText(Integer.toString(Galt));
                    editTime.setText(Integer.toString(Hh)+":"+ Integer.toString(Mm));
                    editFuel.setText(Integer.toString(Gf));


                } catch (NumberFormatException e){

                    Toast.makeText(MainActivity.this,"Вы не ввели данные!!",Toast.LENGTH_LONG).show();
                }

            }

        };

        // присвоим обработчик кнопке btnRas1 (btnRas1)
        //Log.d(TAG, "присваиваем обработчик кнопкам");
        btnRas1.setOnClickListener(oclbtnRas1);


        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        // инициализация
        tabHost.setup();

        TabHost.TabSpec tabSpec;

        // создаем вкладку и указываем тег
        tabSpec = tabHost.newTabSpec("tag1");
        // название вкладки
        tabSpec.setIndicator("Топливо");
        // указываем id компонента из FrameLayout, он и станет содержимым
        tabSpec.setContent(R.id.tvTab1);
        // добавляем в корневой элемент
        tabHost.addTab(tabSpec);

        // создаем вкладку и указываем тег
        tabSpec = tabHost.newTabSpec("tag2");
        // название вкладки
        tabSpec.setIndicator("Вес");
        // указываем id компонента из FrameLayout, он и станет содержимым
        tabSpec.setContent(R.id.tvTab2);
        // добавляем в корневой элемент
        tabHost.addTab(tabSpec);

        // создаем вкладку и указываем тег
        tabSpec = tabHost.newTabSpec("tag3");
        // название вкладки
        tabSpec.setIndicator("Ветер");
        // указываем id компонента из FrameLayout, он и станет содержимым
        tabSpec.setContent(R.id.tvTab3);
        // добавляем в корневой элемент
        tabHost.addTab(tabSpec);

        // создаем вкладку и указываем тег
        tabSpec = tabHost.newTabSpec("tag4");
        // название вкладки
        tabSpec.setIndicator("Перевод");
        // указываем id компонента из FrameLayout, он и станет содержимым
        tabSpec.setContent(R.id.tvTab4);
        // добавляем в корневой элемент
        tabHost.addTab(tabSpec);

        // вторая вкладка будет выбрана по умолчанию
        tabHost.setCurrentTabByTag("tag1");

        // обработчик переключения вкладок
        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                //Toast.makeText(getBaseContext(), "tabId = " + tabId, Toast.LENGTH_SHORT).show();
            }
        });



        /////////события второй вкладки



        // адаптер выбора номера ВС/////*/////////////
        ArrayAdapter<String> adaptervs = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, vs);
        adaptervs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnervs = (Spinner) findViewById(R.id.borts);
        spinnervs.setAdapter(adaptervs);
        // заголовок
        spinnervs.setPrompt("Номер борта");
        // выделяем элемент
        spinnervs.setSelection(0);
        // устанавливаем обработчик нажатия
        spinnervs.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position , Toast.LENGTH_SHORT).show();

                if (position == 0){
                    zerovs = 2436;
                    Ai = 192.41;
                }
                else if (position == 1){
                    zerovs = 2445;
                    Ai = 193.27;
                }
                else if (position == 2){
                    zerovs = 2441;
                    Ai = 192.24;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        ///////////////////////////////////////////////////////

        // адаптер выбора экипажа////*/////////////
        ArrayAdapter<String> adapterek = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ek);
        adapterek.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerek = (Spinner) findViewById(R.id.ekipag);
        spinnerek.setAdapter(adapterek);
        // заголовок
        spinnerek.setPrompt("Экипаж");
        // выделяем элемент
        spinnerek.setSelection(0);
        // устанавливаем обработчик нажатия
        spinnerek.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                //Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();

                if (position == 0){
                    wEk = 160;
                }
                else if (position == 1){
                    wEk = 240;
                }
                else if (position == 2){
                    wEk = 320;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
////////////////////////////////////////////////////////////////////////////

        // адаптер выбора 1 ряд////*/////////////
        ArrayAdapter<String> adapterr1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, r1);
        adapterr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerr1 = (Spinner) findViewById(R.id.rd1);
        spinnerr1.setAdapter(adapterr1);
        // заголовок
        spinnerr1.setPrompt("1 ряд");
        // выделяем элемент
        spinnerr1.setSelection(0);
        // устанавливаем обработчик нажатия
        spinnerr1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                //Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();

                if (position == 0){
                    wR1 = 0;
                }
                else if (position == 1){
                    wR1 = 80;
                }
                else if (position == 2){
                    wR1 = 160;
                }
                else if (position == 3){
                    wR1 = 240;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
/////////////////////////////////////////////////////////////////////////////
        // адаптер выбора 2 ряд////*/////////////
        ArrayAdapter<String> adapterr2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, r2);
        adapterr2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerr2 = (Spinner) findViewById(R.id.rd2);
        spinnerr2.setAdapter(adapterr2);
        // заголовок
        spinnerr2.setPrompt("2 ряд");
        // выделяем элемент
        spinnerr2.setSelection(0);
        // устанавливаем обработчик нажатия
        spinnerr2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                //Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();

                if (position == 0){
                    wR2 = 0;
                }
                else if (position == 1){
                    wR2 = 80;
                }
                else if (position == 2){
                    wR2 = 160;
                }
                else if (position == 3){
                    wR2 = 240;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
/////////////////////////////////////////////////////////////////////////////




    }
}

