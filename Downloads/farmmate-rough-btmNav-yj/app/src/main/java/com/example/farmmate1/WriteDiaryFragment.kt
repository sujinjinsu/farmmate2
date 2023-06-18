package com.example.farmmate1


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresPermission.Write
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WriteDiaryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WriteDiaryFragment : Fragment() {

    companion object {
        private const val ARG_DATE = "arg_date"

        fun newInstance(date: Calendar): WriteDiaryFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }
            val fragment = WriteDiaryFragment()
            fragment.arguments = args
            return fragment
        }
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WriteDiaryFragment.
         */
        // TODO: Rename and change types and number of parameters
        //@JvmStatic fun newInstance(param1: String, param2: String) =
        // WriteDiaryFragment().apply {
        //    arguments = Bundle().apply {
        //        putString(ARG_PARAM1, param1)
        //        putString(ARG_PARAM2, param2)
    }
    // TODO: Rename and change types of parameters
    private lateinit var diaryDataListener: DiaryDataListener
    private lateinit var WriteDiaryTvDate: TextView
    private lateinit var data: String


    private var param1: String? = null
    private var param2: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_write_diary, container, false)
        WriteDiaryTvDate = view.findViewById(R.id.write_diary_tv_date)


        return view




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //spinner
        val spinner: Spinner = view.findViewById(R.id.write_diary_spinner_select)
        val items = listOf("포도", "딸기", "오이","파프리카","토마토", "고추") // 스피너에 표시될 데이터 리스트
        val spinnerAdapter = WriteDiayrSpinnerAdapter(requireContext(), items)

        spinner.adapter = spinnerAdapter

        // 스피너의 아이템 선택 리스너 설정
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position) as String
                // 선택된 항목에 대한 동작 수행
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 아무 항목도 선택되지 않았을 때 동작 수행
            }
        }

        val selectedDate = arguments?.getSerializable(ARG_DATE) as? Calendar
        selectedDate?.let {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = dateFormat.format(it.time)
            WriteDiaryTvDate.text = formattedDate
        } ?: run {
            WriteDiaryTvDate.text = "에러 발" // 날짜가 없을 경우에 대한 처리 (빈 문자열로 표시)
        }

        view.findViewById<Button>(R.id.write_diary_title_todobtn).setOnClickListener {
            val data = "Your diary data" // 실제 데이터를 여기에 입력하세요
            selectedDate?.let { date ->
                saveData(date, data)
            }
        }


        // DiaryFragment의 버튼 클릭 이벤트에서 WriteDiaryFragment로 전환하는 코드
        view.findViewById<Button>(R.id.write_diary_title_todobtn).setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragment2 = WriteDiaryFragment()



            // MainActivity가 DiaryDataListener를 구현했을 경우, WriteDiaryFragment에 MainActivity 인스턴스를 전달
            if (requireActivity() is DiaryDataListener) {
                fragment2.diaryDataListener = requireActivity() as DiaryDataListener
            }

            fragmentManager.beginTransaction()
                .replace(R.id.main_fl, fragment2)
                .addToBackStack(null)
                .commit()
        }

        // WriteDiaryFragment 내부에서 사용하고자 하는 버튼의 ID를 확인하고 수정
        val button: Button = view.findViewById(R.id.write_diary_title_todobtn)
        button.setOnClickListener {
            // 전환할 프래그먼트 생성
            val newFragment = TodoFragment()

            // 선택한 날짜 가져오기
            val selectedDate = arguments?.getSerializable(ARG_DATE) as? Calendar

            // 선택한 날짜를 TodoFragment에 전달
            selectedDate?.let { date ->
                val args = Bundle().apply {
                    putSerializable("selected_date", date)
                }
                newFragment.arguments = args
            }

            // 프래그먼트 전환을 위해 FragmentManager 사용
            val fragmentManager = requireActivity().supportFragmentManager

            // 트랜잭션 시작
            val transaction = fragmentManager.beginTransaction()

            // 프래그먼트 전환
            transaction.replace(R.id.main_fl, newFragment)

            // 트랜잭션 커밋
            transaction.commit()
        }
        // WriteDiaryFragment 내부에서 사용하고자 하는 버튼의 ID를 확인하고 수정

        //back button
        val backButton: ImageButton = view.findViewById(R.id.write_diary_fragment_back_ib)
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            diaryDataListener = context as DiaryDataListener
        } catch (e: ClassCastException) {
            throw java.lang.ClassCastException("$context must implement DiaryDatalistener")
        }
    }

    private fun saveData(date: Calendar, data: String) {
        // 데이터 저장 로직 구현
        // ...
        // 데이터 저장 후 데이터를 DiaryFragment로 전달
        diaryDataListener.onDiaryDataReceived(date, data)
    }


}
