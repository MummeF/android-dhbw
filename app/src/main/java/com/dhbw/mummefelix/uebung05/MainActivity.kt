package com.dhbw.mummefelix.uebung05

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dhbw.mummefelix.uebung05.api.ApiRequests
import com.dhbw.mummefelix.uebung05.api.model.RandomUserResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://randomuser.me"

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"
    private var callRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getRandomUser(false)
        reloadRandomUser.setOnClickListener {
            getRandomUser(false)
        }
        femaleOnlyBtn.setOnClickListener {
            getRandomUser(true)
        }

    }

    private fun getRandomUser(filter: Boolean) {
        if (callRunning) {
            Toast.makeText(
                applicationContext,
                getString(R.string.notThatFastText),
                Toast.LENGTH_LONG
            ).show()
        } else {
            callRunning = true
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiRequests::class.java)

            val call: Call<RandomUserResponse>;
            if(filter){
                call = api.getGenderOnly("female")
            }else{
                call = api.getRandomUser()
            }

            call.enqueue(object : Callback<RandomUserResponse> {
                override fun onResponse(
                    call: Call<RandomUserResponse>,
                    response: Response<RandomUserResponse>
                ) {

                    if (response.code() == 200) {
                        val data = response.body()!!
                        if (data.results.isNotEmpty()) {
                            val person = data.results.get(0)
                            randomUserName.text = person.name.first + " " + person.name.last
                            randomUserAdress.text =
                                person.location.street.name + " " + person.location.street.number + "\n" + person.location.postcode + " " + person.location.city + "\n" + person.location.country

                            Picasso.get().load(person.picture.large)
                                .resize(400,400)
                                .centerCrop()
                                .into(userImage)
                        }
                    } else if (response.code() >= 500) {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.wentWrongAndIAmNotResponsible),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.wentWrongMessage),
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    callRunning = false
                }

                override fun onFailure(call: Call<RandomUserResponse>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.wentWrongMessage),
                        Toast.LENGTH_LONG
                    ).show()

                    callRunning = false
                }

            })
        }

    }


}