package com.example.testandroidfetures.bluetooth

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testandroidfetures.R


class Bluetooth : AppCompatActivity() {
    private val REQUEST_ENABLE_BT = 0
    private val REQUEST_DISCOVER_BT = 1

    var statusBlue: TextView? = null
    var paired:TextView? = null
    var Blue: ImageView? = null
    var OnBtn: Button? = null
    var OffBtn:Button? = null
    var DiscoverBtn:Button? = null
    var PairedBtn:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)
        statusBlue   = findViewById(R.id.statusBluetoothTv)
        paired       = findViewById(R.id.pairedTv)
        Blue         = findViewById(R.id.bluetoothIv)
        OnBtn        = findViewById(R.id.onBtn)
        OffBtn       = findViewById(R.id.offBtn)
        DiscoverBtn  = findViewById(R.id.discoverableBtn)
        PairedBtn    = findViewById(R.id.pairedBtn)
        //adapter
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        //check if bluetooth is available or not
        if (bluetoothAdapter == null){
            // Device doesn't support Bluetooth
            statusBlue?.setText("Bluetooth is not available")
        }
        else {
            statusBlue?.setText("Bluetooth is available")
        }

        //set image according to bluetooth status(on/off)
        if ((bluetoothAdapter as BluetoothAdapter).isEnabled()){
            Blue?.setImageResource(R.drawable.ic_baseline_bluetooth_24);
        }
        else {
            Blue?.setImageResource(R.drawable.ic_baseline_bluetooth_disabled_24);
        }
        //on btn click
        OnBtn!!.setOnClickListener {
            if (!bluetoothAdapter!!.isEnabled) {
                showToast("Turning On Bluetooth...")
                //intent to on bluetooth
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(intent, REQUEST_ENABLE_BT)
            } else {
                showToast("Bluetooth is already on")
            }
        }

        //discover bluetooth btn click
        DiscoverBtn!!.setOnClickListener {
            if (!bluetoothAdapter!!.isDiscovering) {
                showToast("Making Your Device Discoverable")
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
                startActivityForResult(intent, REQUEST_DISCOVER_BT)
            }
        }

        //off btn click
        OffBtn!!.setOnClickListener {
            if (bluetoothAdapter!!.isEnabled) {
                bluetoothAdapter!!.disable()
                showToast("Turning Bluetooth Off")
                Blue!!.setImageResource(R.drawable.ic_baseline_bluetooth_disabled_24)
            } else {
                showToast("Bluetooth is already off")
            }
        }

        //get paired devices btn click
        PairedBtn!!.setOnClickListener{
            if (bluetoothAdapter!!.isEnabled) {
                paired!!.text = "Paired Devices"
                val devices = bluetoothAdapter!!.bondedDevices
                for (device in devices) {
                    paired!!.append(
                        """

                    Device: ${device.name}, $device
                    """.trimIndent()
                    )
                }
            } else {
                //bluetooth is off so can't get paired devices
                showToast("Turn on bluetooth to get paired devices")
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_ENABLE_BT -> if (resultCode == RESULT_OK) {
                //bluetooth is on
                Blue!!.setImageResource(R.drawable.ic_baseline_bluetooth_24)
                showToast("Bluetooth is on")
            } else {
                //user denied to turn bluetooth on
                showToast("could't on bluetooth")
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    //toast message function
    private fun showToast(msg: String, long: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, msg, long).show()
    }
}
