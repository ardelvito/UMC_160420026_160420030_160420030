package com.example.healthcaretesting.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.healthcaretesting.R
import com.example.healthcaretesting.model.User
import com.example.healthcaretesting.viewmodel.RegisterViewModel

class RegistFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_regist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        val btnConfirmRegister = view.findViewById<Button>(R.id.btnConfirmRegister)
        btnConfirmRegister.setOnClickListener{
            val fullnameRegister = view.findViewById<EditText>(R.id.txtFullnameRegister)
            val usernameRegister = view.findViewById<EditText>(R.id.txtUsernameRegister)
            val passwordRegister = view.findViewById<EditText>(R.id.txtPasswordRegister)
            val phoneRegister = view.findViewById<EditText>(R.id.txtPhoneRegister)
            var radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupGenderRegister)
            val checkedRadioButtonId = radioGroup.checkedRadioButtonId
            val checkedRadioButton = view.findViewById<RadioButton>(checkedRadioButtonId)
            val selectedTag = checkedRadioButton.tag

            val user = User(
                fullnameRegister.text.toString(),
                usernameRegister.text.toString(),
                phoneRegister.text.toString(),
                selectedTag.toString(),
                passwordRegister.text.toString()

            )

            viewModel.userRegister(user).observe(viewLifecycleOwner, { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
                    //Direct into login fragment
                    val action = RegistFragmentDirections.actionBackToLogin()
                    Navigation.findNavController(it).navigate(action)

                } else {
                    Toast.makeText(requireContext(), "Registration failed", Toast.LENGTH_SHORT).show()
                }
            })

        }
    }

}