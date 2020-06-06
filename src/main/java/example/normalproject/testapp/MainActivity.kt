
package example.normalproject.testapp
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import example.normalproject.testapp.StateAdapter

class MainActivity : AppCompatActivity() {
         lateinit var stateAdapter: StateAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {

            val response = withContext(Dispatchers.IO) { Client.api.execute() }
            if (response.isSuccessful) {
                val data = Gson().fromJson(response.body?.string(), Response::class.java)
                launch(Dispatchers.Main) {
                    bindCombinedData( data.statewise[0])
                    bindStateWiseData(data.statewise.subList(1,data.statewise.size))
    }


            }

        }
    }

    private fun bindStateWiseData(subList: List<StatewiseItem>) {
        stateAdapter = StateAdapter(subList)
        list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.item_header,list,false))
       list.adapter=stateAdapter
    }



    private fun bindCombinedData(data: StatewiseItem) {
        confirmedtext.text = data.confirmed
        activetext.text = data.active
        recoveredtext.text = data.recovered
        deathtext.text = data.deaths

    }

    }


