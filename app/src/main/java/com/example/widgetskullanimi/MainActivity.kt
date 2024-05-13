package com.example.widgetskullanimi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.example.widgetskullanimi.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var kontrol=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOku.setOnClickListener{
            val alinanVeri=binding.editTextGirdi.text.toString()
            binding.textViewSonuc.text=alinanVeri
        }

        binding.buttonResim1.setOnClickListener{
             binding.imageView.setImageResource(R.drawable.ikon1)
        }
        binding.buttonResim2.setOnClickListener{
            binding.imageView.setImageResource(R.drawable.ikon2)
        }

        binding.switch1.setOnCheckedChangeListener{compountButton,isChecked->

            if (isChecked){
                Log.e("Sonuc","Switch : ON")

            }
            else{
                Log.e("Sonuc","Switch : OF")
            }

            binding.toogleButton.addOnButtonCheckedListener{group,checkedId,isChecked->
                kontrol=true
                if (kontrol){
                    val secilenButon=findViewById<Button>(binding.toogleButton.checkedButtonId)
                    val butonYazi=secilenButon.text.toString()
                    Log.e("Sonuc",butonYazi)
                }
            }

            var ulkeler=ArrayList<String>()
            ulkeler.add("Türkiye")
            ulkeler.add("Almanya")
            ulkeler.add("İtalya")

            val arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,ulkeler)
            binding.autoCompleteTextView.setAdapter(arrayAdapter)

            binding.buttonBasla.setOnClickListener{
                binding.progressBar.visibility=View.VISIBLE
            }
            binding.buttonDur.setOnClickListener{
                binding.progressBar.visibility=View.INVISIBLE
            }

            binding.textViewSlider.text=binding.slider.progress.toString()

            binding.slider.setOnSeekBarChangeListener(object :OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?,progress: Int,fromUser: Boolean) {
                    binding.textViewSlider.text=progress.toString()

                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }


            })

            binding.buttonSaat.setOnClickListener{
                val tp = MaterialTimePicker.Builder()
                    .setTitleText("Saat Seçin")
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .build()

                tp.show(supportFragmentManager,"Saat")
                tp.addOnPositiveButtonClickListener{
                    binding.editTextSaat.setText("${tp.hour} : ${tp.minute}")

                }
            }
            binding.buttonTarih.setOnClickListener{
                val tp = MaterialTimePicker.Builder()
                    .setTitleText("Tarih Seçin")
                    .build()

                tp.show(supportFragmentManager,"Tarih")
                tp.addOnPositiveButtonClickListener{
                    val df=SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val tarih = df.format(it)
                    binding.editTextTarih.setText(tarih)

                }
            }


            binding.buttonGoster.setOnClickListener{
                Log.e("Sonuc","Switch durum : ${binding.switch1.isChecked}")
                if (kontrol){
                    val secilenButon=findViewById<Button>(binding.toogleButton.checkedButtonId)
                    val butonYazi=secilenButon.text.toString()
                    Log.e("Sonuc","Toggle Durum: ${butonYazi}")
                }

            }

        }




    }
}