/**
 * @author		Yuval Navon <yuvalnavon8@gmail.com>
 * @version	1.0
 * @since		22/11/2021
 * The program makes a list with the first 20 terms of the series
 * made in the previous activity (via a listview).
 * it allows you to choose each term and display either its location or the series' sum
 * until that term (via a contextMenu and a textview).
 */
package com.example.contextmenuex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class results extends AppCompatActivity implements View.OnCreateContextMenuListener, AdapterView.OnItemLongClickListener {
    /**
     * The class makes a list with the first 20 terms of the series
     *  * made in the previous activity (via a listview).
     *  * it allows you to choose each term and display either its location or the series' sum
     *  * until that term (via a contextMenu and a textview).
     * <p>
     *
     * @param	None
     * @return	None
     */


    double a1, m, sum;
    TextView details;
    Intent gi;
    ListView list;
    String[] terms_sum = new String[20];
    double[] terms_dou = new double[20];
    String[] terms = new String[20];
    int pos;
    String sumfortext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        gi = getIntent();

        int type = gi.getIntExtra("series_type",0);
        a1 = gi.getDoubleExtra("first", 0);
        m = gi.getDoubleExtra("m", 0);
        if (type == 0)
        {
            terms_dou[0] = a1;
            sum = a1;
            terms[0] = String.valueOf(a1);
            terms_sum[0] = String.valueOf(a1);

            for (int i=1; i<20;i++)
            {

                terms_dou[i] = terms_dou[i-1]+m;
                terms[i] = String.valueOf(terms_dou[i]);
                sum = sum + terms_dou[i];
                terms_sum[i] = String.valueOf(sum);
            }
        }
        if (type == 1)
        {
            terms_dou[0] = a1;
            sum = a1;
            terms[0] = String.valueOf(a1);
            terms_sum[0] = String.valueOf(a1);

            for (int i=1; i<20;i++)
            {

                terms_dou[i] = terms_dou[i-1]*m;
                terms[i] = String.valueOf(terms_dou[i]);
                sum = sum + terms_dou[i];
                terms_sum[i] = String.valueOf(sum);
            }
        }


        list = (ListView) findViewById(R.id.listview);
        list.setOnCreateContextMenuListener(this);
        list.setOnItemLongClickListener(this);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        details = (TextView) findViewById(R.id.details);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, terms);
        list.setAdapter(adp);

    }
    public void goback(View view)
    /**
     * The function is called when the button is pressed.
     * It goes back to the previous activity.
     * <p>
     *
     * @param    View view -	required for the function to be recognized as the button's
     *      *              onClick method
     * @return	None
     */

    {
        finish();
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    /**
     * The function is called when the user long-clicks a term in the list.
     * It puts the position of the selected item in the list into the int variable "pos"
     * and the sum of all terms in the list until the selected term into the
     * String "sumfortext". This is for the textview which displays one of the two.
     * <p>
     *
     * @param	AdapterView<?> parent - the adapter of the listview.
     *          View view - the selected term in the list.
     *          int position - the position of the selected term in the list.
     *          long id - the position of the selected term in the adapter.
     * @return	false - mandatory for the function to operate correctly.
     */

    {

        pos = position;
        sumfortext = terms_sum[position];
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    /**
     * The function creates a contextMenu with a title ("Choose Detail:") and 2 options (in order):
     * "Term location" and "Sum until term".
     * <p>
     *
     * @param	ContextMenu menu - the menu that is created.
     *        View v - the widget which the menu is created for.
     *        ContextMenu.ContextMenuInfo menuInfo - used if the menu is created for
     *        a List, but it isn't so this isn't used.
     * @return	None
     */
    {
        menu.setHeaderTitle("Choose Detail:");
        menu.add("Term location");
        menu.add("Sum until term");

    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    /**
     * The function is called when the user chooses an option in the context menu.
     * It checks the user's choice in the menu. If its "Term location" - it puts the
     * location of the term selected in the textView "details".
     * If its "Sum until term" - it puts the
     * sum of the series until the selected term selected in the textView "details"
     * <p>
     *
     * @param	MenuItem item - the option which the user chose.
     * @return	true - mandatory for the function to operate correctly.
     */
    {
        String oper=item.getTitle().toString();
        if (oper.equals("Term location"))
        {

            details.setText("Term location: \n"+ Integer.toString(pos+1));

        }
        if (oper.equals("Sum until term"))
        {
            details.setText("Sum until term: \n"+ sumfortext);
        }
        return true;
    }


}