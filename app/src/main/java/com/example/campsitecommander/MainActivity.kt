package com.example.campsitecommander

import android.os.Bundle
import android.telephony.ims.SipDetails
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.campsitecommander.ui.theme.CampsiteCommanderTheme
import java.util.Locale

class MainActivity : ComponentActivity() {

    private lateinit var splashLayout: LinearLayout
    private lateinit var mainLayout: ScrollView
    private lateinit var detailLayout: ScrollView

    private lateinit var txtDetails: TextView
    private lateinit var txtChecklist: TextView

    private lateinit var editCategory: EditText
    private lateinit var editQuantity: EditText

    private lateinit var spinnerItems: Spinner

    private val items = arrayOf(
        "Tent", "Marshmallows", "Flashlight"
    )

    private val category = IntArray(3)
    private val quantity = IntArray(3)
    private val comments = Array(3) { "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        splashLayout = findViewById(R.id.splashLayout)
        mainLayout = findViewById(R.id.mainLayout)
        detailLayout = findViewById(R.id.detailLayout)

        txtChecklist = findViewById(R.id.txtChecklist)

        editCategory = findViewById(R.id.editCategory)
        editQuantity = findViewById(R.id.editQuantity)

        spinnerItems = findViewById(spinnerItems)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, items
        )

        spinnerItems.adapter = adapter

        //Buttons
        val btnStart = findViewById<Button>(R.id.btnStart)

        val btnAddGear = findViewById<Button>(R.id.btnAddGear)
        val btnTotalItems = findViewById<Button>(R.id.btnTotalItems)
        val btnItems = findViewById<Button>(R.id.btnItems)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val btnComments = findViewById<Button>(R.id.btnComments)
        val btnBack = findViewById<Button>(R.id.btnBack)


        //Splash Screen Navigation
        btnStart.setOnClickListener {
            splashLayout.visibility = View.GONE
            mainLayout.visibility = View.VISIBLE
        }

        //View Items
        btnItems.setOnClickListener {

            var display = ""

            for (i in items.indices) {

                display += "${items[i]}\n"
                display += "Category: ${category[i]}\n"
                display += "Quantity: ${quantity[i]}\n"
                display += "Comments: ${comments[i]}\n"

            }


            //Back Button
            btnBack.setOnClickListener {
                detailLayout.visibility = View.GONE
                mainLayout.visibility = View.VISIBLE

            }

            txtDetails.text = display

            //Clear Data
            btnClear.setOnClickListener {

                for (i in category)
                    category[i] = 0
            }
        }
    }
}









