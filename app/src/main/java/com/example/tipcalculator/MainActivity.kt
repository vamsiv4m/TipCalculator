package com.example.tipcalculator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import kotlinx.android.synthetic.main.activity_main.*;

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculate.setOnClickListener {
            calculateTip()
        }
    }
   private fun calculateTip(){
        val service=binding.costofservice.text.toString()
       val cost=service.toDoubleOrNull()
       if (cost==null){
           binding.tip.text="Tip amount "
           return
       }
       val selectedId=binding.radioGroup.checkedRadioButtonId
        val tippercent=when(selectedId){
            R.id.radioButton1->0.20
            R.id.radioButton2->0.18
            else->0.15
        }
        //To calculate tip amount...
        var tipamount=tippercent*cost
        var roundup=binding.roundup.isChecked
        if(roundup){
            tipamount=kotlin.math.ceil(tipamount)
        }
        val formatedtip=NumberFormat.getCurrencyInstance().format(tipamount)
        binding.tip.text=getString(R.string.tip_amount,formatedtip)
    }
}