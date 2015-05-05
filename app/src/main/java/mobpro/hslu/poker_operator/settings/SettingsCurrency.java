package mobpro.hslu.poker_operator.settings;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import mobpro.hslu.poker_operator.R;

/**
 * Created by User on 04.05.2015.
 */
public class SettingsCurrency extends Activity implements AdapterView.OnItemClickListener {
    private String[] aData = new String[] {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6" };

    public SettingsCurrency(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_currency);

        ListView listView = (ListView)findViewById(R.id.listView_currney);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, aData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

