package com.example.morningalarm.howoldami

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.morningalarm.howoldami.databinding.FragmentFirstBinding
import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.year.addTextChangedListener { setAge() }
        binding.month.addTextChangedListener { setAge() }
        binding.day.addTextChangedListener { setAge() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun calculateAge(dateOfBirth: LocalDate): Long {
        return ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now())
    }

    private fun setAge() {
        val year = binding.year.text.toString()
        val month = binding.month.text.toString()
        val day = binding.day.text.toString()
        if (year != "" && month != "" && day != "") {
            val year = year.toInt()
            val month = month.toInt()
            val day = day.toInt()
            if (month in 1..12 && day in 1..31) {
                val dateOfBirth = LocalDate.of(year, month, day)
                binding.textView.text = "You are ${calculateAge(dateOfBirth)} years old."
            } else {
                binding.textView.text = ""
            }
        } else {
            binding.textView.text = ""
        }
    }
}