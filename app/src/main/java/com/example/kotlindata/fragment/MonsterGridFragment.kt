package com.example.kotlindata.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlindata.R
import com.example.kotlindata.ui.main.MainRecyclerAdapter
import com.example.kotlindata.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_monster_grid.*

class MonsterGridFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipelayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_monster_grid, container, false)

        //variable initialisation
        recyclerView = view.findViewById(R.id.monster_list)
        swipelayout = view.findViewById(R.id.swipe_layout)

        //Swipe Refresh Behavior
        swipelayout.setOnRefreshListener {
            viewModel.refreshData()
        }

        //ViewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.monsterData.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = MainRecyclerAdapter(requireContext(), it)

            swipelayout.isRefreshing = false

//            val monsterNames = StringBuilder()
//            for (monster in it) {
////                Log.i(LOG_VAL, "${monster.name} (\$${monster.price})")
//                monsterNames.append(monster.name).append("\n")
//            }
////            message.text = monsterNames
        })



        return view
    }
}
