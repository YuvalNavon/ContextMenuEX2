/**
 * @author		Yuval Navon <yuvalnavon8@gmail.com>
 * @version	1.0
 * @since		22/11/2021
 * The program lets you choose a mathematical series (arithmetic or geometric,
 * via a contextMenu), put in the first term and the difference/multiplier, and when you
 * click the "Calculate" button, it takes you to a new activity which shows a list of
 * the first 20 terms of the series you made (via a listview), and allows you to choose
 * each term and present either its location or the sum until it
 * (via a contextMenu and a textview).
 */
package com.example.contextmenuex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener {
    /**
     * The class lets you choose a mathematical series (arithmetic or geometric,
     *  * via a contextMenu), put in the first term and the difference/multiplier, and when you
     *  * click the "Calculate" button, it takes you to a new activity which shows a list of
     *  * the first 20 terms of the series you made (via a listview), and allows you to choose
     *  * each (via a contextMenu) and present either its location or the sum until it
     *  * (via a textview).
     * <p>
     *
     * @param  None
     * @return	None
     */
    Button choose_Series;
    int series_check;
    double a1;
    double diff;
    EditText first;
    EditText diff_t;
    boolean series_pick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        choose_Series = (Button) findViewById(R.id.button);
        first = (EditText) findViewById(R.id.a1);
        diff_t = (EditText) findViewById(R.id.m);
        choose_Series.setOnCreateContextMenuListener(this);
        series_pick = false;
    }

    public void calculate(View view)
    /**
     * The function is called when the button is clicked.
     * It checks if the inputted information is legal, and if it is then it packs the
     * series type, the first term and the difference/multiplier and starts
     * the next activity, with the packed information.
     * If the inputted information is illegal, the function makes a toast
     * with a message according to the error (lack of input/illegal input).
     * <p>
     *
     * @param	View view -	required for the function to be recognized as the button's
     *              onClick method
     * @return	None
     */

    {
        if (series_pick)
        {
            if (first.getText().toString().isEmpty() == false && diff_t.getText().toString().isEmpty() == false && !first.getText().toString().equals(".") && !diff_t.getText().toString().equals(".") && !first.getText().toString().equals("-.") && !diff_t.getText().toString().equals("-.") && !first.getText().toString().equals("+") && !diff_t.getText().toString().equals("+") && !first.getText().toString().equals("-") && !diff_t.getText().toString().equals("-") && !first.getText().toString().equals("+.") && !diff_t.getText().toString().equals("+."))
            {
                Intent si = new Intent(this, results.class);
                a1 = Double.parseDouble(first.getText().toString());
                diff = Double.parseDouble(diff_t.getText().toString());
                si.putExtra("first", a1);
                si.putExtra("m", diff);
                si.putExtra("series_type", series_check);
                startActivity(si);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Please input all pararmeters correctly!", Toast.LENGTH_SHORT).show();

            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please choose a series type", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)

          /**
          * The function creates a contextMenu with a title ("Choose Series Type") and 2 options (in order):
          * "Arithmetic" and "Geometric".
          * <p>
         *
         * @param	ContextMenu menu - the menu that is created.
           *        View v - the widget which the menu is created for.
           *        ContextMenu.ContextMenuInfo menuInfo - used if the menu is created for
           *        a List, but it isn't so this isn't used.
         * @return	None
         */

    {
            menu.setHeaderTitle("Choose Series Type");
            menu.add("Arithmetic");
            menu.add("Geometric");

        }
    @Override
    public boolean onContextItemSelected(MenuItem item)
        /**
         * The function is called when the user chooses an option in the context menu.
         * It checks the user's choice in the menu and puts it in the
         * int variable "series_check" (0 for arithmetic, 1 for geometric).
         * It also sets the boolean variable "series_pick" as true
         * to make sure the user chose something.
         * <p>
         *
         * @param	MenuItem item - the option which the user chose.
         * @return	true - mandatory for the function to operate correctly.
         */


    {
            String oper=item.getTitle().toString();
            if (oper.equals("Arithmetic"))
            {
                series_check = 0;
                series_pick = true;

            }
            if (oper.equals("Geometric"))
            {
                series_check = 1;
                series_pick = true;
            }
                return true;
        }
    }