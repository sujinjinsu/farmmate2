package com.example.farmmate1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.farmmate1.databinding.FragmentPlantBinding

class PlantFragment : Fragment() {

    private var _binding: FragmentPlantBinding? = null
    private val binding get() = _binding!!

    // 데이터 보존
    companion object {
        fun newInstance(count : Int): PlantFragment{
            val args = Bundle()
            args.putInt("number", count)
            val fragment = PlantFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlantBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 식물 추가 버튼 클릭 후 식물 추가 페이지로 이동
        binding.plantAddBtn.setOnClickListener{
            moveToAddPlantFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // 메모리 누수를 방지하기 위해 Fragment View에 대한 참조를 제거하여 가비지 컬렉터가 수거
        _binding = null
    }
    private fun moveToAddPlantFragment() {
        val transaction = parentFragmentManager
            .beginTransaction()
            .replace(R.id.main_fl, PlantAddFragment())
        transaction.commit()
    }


    class MainViewModel : ViewModel() {

        private val _count = MutableLiveData<Int>()
        val count : LiveData<Int> get() = _count
        init {
            _count.value = 5
        }
        fun getUpdatedCount(plusCount: Int){
            _count.value = (_count.value)?.plus(plusCount)
        }
    }
}