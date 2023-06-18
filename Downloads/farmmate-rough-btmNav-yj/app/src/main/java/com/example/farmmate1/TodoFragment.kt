package com.example.farmmate1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class TodoFragment : Fragment() {

    companion object {
        private const val ARG_DATE = "arg_date"

        fun newInstance(date: Calendar): TodoFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }
            val fragment = TodoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var selectedDate: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // WriteDiaryFragment로 전환하는 코드
        view.findViewById<Button>(R.id.todo_title_writediarybtn).setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragment = WriteDiaryFragment()

            // 선택한 날짜를 WriteDiaryFragment에 전달
            val selectedDate = arguments?.getSerializable(ARG_DATE) as? Calendar
            selectedDate?.let { date ->
                val args = Bundle().apply {
                    putSerializable(ARG_DATE, date)
                }
                fragment.arguments = args
            }

            fragmentManager.beginTransaction()
                .replace(R.id.main_fl, fragment)
                .addToBackStack(null)
                .commit()
        }

        val backButton: ImageButton = view.findViewById(R.id.todo_fragment_back_ib)
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }



            override fun onCreateView(
                inflater: LayoutInflater, container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {
                // Inflate the layout for this fragment
                val view = inflater.inflate(R.layout.fragment_todo, container, false)

                // 전달받은 날짜 가져오기
                val selectedDate = arguments?.getSerializable("selected_date") as? Calendar

                // 선택한 날짜를 TextView에 표시
                selectedDate?.let {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val formattedDate = dateFormat.format(it.time)
                    view.findViewById<TextView>(R.id.todo_tv_date).text = formattedDate
                }

                return view
            }

        }










