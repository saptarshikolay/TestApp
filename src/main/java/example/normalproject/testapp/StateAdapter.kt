package example.normalproject.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_list.view.*



class StateAdapter (val list: List<StatewiseItem>):BaseAdapter(){
     override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

     val view = convertView ?: LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
    val item = list[position]
    view.confirmedtext.text =item.confirmed
         view.recoveredtext.text =item.recovered
         view.deathtext.text =item.deaths
         view.activetext.text =item.active
        view.statetext.text =item.state
     return view

     }

     override fun getItem(position: Int)  = list[position]

     override fun getItemId(position: Int) = position.toLong()

     override fun getCount(): Int =list.size  }

