package com.example.campsitecommander

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {

    private lateinit var splashLayout: View
    private lateinit var mainLayout: View
    private lateinit var detailLayout: View

    private lateinit var txtDetails: TextView
    private lateinit var txtTotalItems: TextView

    private lateinit var editCategory: TextView
    private lateinit var editQuantity: TextView

    private lateinit var spinnerItems: Spinner

    private val items = arrayOf(
        "Tent", "Marshmallows", "Flashlight", "Sleeping Bag",
        "Camp Stove", "First Aid Kit", "Water Bottle", "Compass"
    )

    // Data storage
    private val categories = Array(items.size) { "" }
    private val quantities = Array(items.size) { "" }
    private val comments = Array(items.size) { "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize layouts
        splashLayout = findViewById(R.id.splashLayout)
        mainLayout = findViewById(R.id.mainLayout)
        detailLayout = findViewById(R.id.detailLayout)

        // Initialize TextViews
        txtDetails = findViewById(R.id.txtDetails)
        txtTotalItems = findViewById(R.id.txtTotalItems)

        // Initialize EditTexts
        editCategory = findViewById(R.id.editCategory)
        editQuantity = findViewById(R.id.editQuantity)

        // Initialize Spinner
        spinnerItems = findViewById(R.id.spinnerItems)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            items
        )
        spinnerItems.adapter = adapter

        // Track selected item index
        var selectedItemIndex = 0
        spinnerItems.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItemIndex = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Initialize Buttons
        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnAddGear = findViewById<Button>(R.id.btnAddGear)
        val btnSaveItem = findViewById<Button>(R.id.btnSaveItem)
        val btnViewAllItems = findViewById<Button>(R.id.btnViewAllItems)
        val btnClearAll = findViewById<Button>(R.id.btnClearAll)
        val btnItems = findViewById<Button>(R.id.btnItems)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val btnComments = findViewById<Button>(R.id.btnComments)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Splash Screen -> Main Screen
        btnStart.setOnClickListener {
            splashLayout.visibility = View.GONE
            mainLayout.visibility = View.VISIBLE
        }

        // Add Gear Item
        btnAddGear.setOnClickListener {
            splashLayout.visibility = View.GONE
            mainLayout.visibility = View.GONE
            detailLayout.visibility = View.VISIBLE
            // Load saved data for the selected item
            loadCurrentItem(selectedItemIndex)
        }

        // Save Item (in detail view)
        btnSaveItem.setOnClickListener {
            saveCurrentItem(selectedItemIndex)
            Toast.makeText(this, "${items[selectedItemIndex]} saved!", Toast.LENGTH_SHORT).show()
            // Clear input fields after saving
            editCategory.text = ""
            editQuantity.text = ""
            updateTotalCount()
        }

        // View All Items (from main screen)
        btnViewAllItems.setOnClickListener {
            mainLayout.visibility = View.GONE
            detailLayout.visibility = View.VISIBLE
            displayItems()
        }

        // View Items (in detail view)
        btnItems.setOnClickListener {
            displayItems()
        }

        // Back to Main
        btnBack.setOnClickListener {
            detailLayout.visibility = View.GONE
            mainLayout.visibility = View.VISIBLE
        }

        // Clear All Data (from main screen)
        btnClearAll.setOnClickListener {
            for (i in items.indices) {
                categories[i] = ""
                quantities[i] = ""
                comments[i] = ""
            }
            editCategory.text = ""
            editQuantity.text = ""
            txtDetails.text = "No items added yet."
            updateTotalCount()
            Toast.makeText(this, "All data cleared", Toast.LENGTH_SHORT).show()
        }

        // Clear Data (in detail view)
        btnClear.setOnClickListener {
            for (i in items.indices) {
                categories[i] = ""
                quantities[i] = ""
                comments[i] = ""
            }
            editCategory.text = ""
            editQuantity.text = ""
            txtDetails.text = "No items added yet."
            updateTotalCount()
            Toast.makeText(this, "All data cleared", Toast.LENGTH_SHORT).show()
        }

        // View Comments
        btnComments.setOnClickListener {
            displayComments()
        }
    }

    private fun saveCurrentItem(index: Int) {
        categories[index] = editCategory.text.toString().trim()
        quantities[index] = editQuantity.text.toString().trim()
        comments[index] = editQuantity.text.toString().trim()
    }

    private fun loadCurrentItem(index: Int) {
        editCategory.setText(categories[index])
        editQuantity.setText(quantities[index])
    }

    private fun displayItems() {
        var display = ""
        var hasItems = false

        for (i in items.indices) {
            val cat = categories[i]
            val qty = quantities[i]

            if (cat.isNotBlank() || qty.isNotBlank()) {
                hasItems = true
                display += "${items[i]}\n"
                display += "  Category: ${cat.ifBlank { "—" }}\n"
                display += "  Quantity: ${qty.ifBlank { "—" }}\n"
                display += "  ─────────────────\n"
            }
        }

        txtDetails.text = if (hasItems) display.trimEnd() else "No items added yet."
        updateTotalCount()
    }

    private fun displayComments() {
        var display = ""
        var hasComments = false

        for (i in items.indices) {
            val cmt = comments[i]
            if (cmt.isNotBlank()) {
                hasComments = true
                display += "${items[i]}:\n  \"$cmt\"\n\n"
            }
        }

        txtDetails.text = if (hasComments) display.trimEnd() else "No comments saved yet."
    }

    private fun updateTotalCount() {
        var count = 0
        for (i in items.indices) {
            if (categories[i].isNotBlank() || quantities[i].isNotBlank()) {
                count++
            }
        }
        txtTotalItems.text = count.toString()
    }
}
