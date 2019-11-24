package com.conceptic.andcourse.presentation.auth.signup

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.conceptic.andcourse.R
import com.conceptic.andcourse.data.model.Gender
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.ext.createScope
import com.conceptic.andcourse.presentation.ext.validate
import com.conceptic.andcourse.presentation.view.LoadingProgressDialog
import kotlinx.android.synthetic.main.fragment_signup.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import java.text.SimpleDateFormat
import java.util.*

class SignUpFragment : BaseFragment<SignUpViewModel>(R.layout.fragment_signup) {
    override val scope: Scope = createScope(SIGNUP_SCOPE)
    override val viewModel: SignUpViewModel by scope.viewModel(this)

    private val calendar = Calendar.getInstance(TimeZone.getDefault())
    private val dateFormatter = SimpleDateFormat("MM.dd.yyyy", Locale.getDefault())
        .apply { timeZone = TimeZone.getDefault() }

    private val email
        get() = signup_input_email validate {
            required(errorMessage = "Email is required")
            email(errorMessage = "Email doesn't match it's format")
        }
    private val dateBirth
        get() = signup_input_date_birth validate {
            required(errorMessage = "Birth date is required")
        }
    private val password
        get() = signup_input_password validate {
            required(errorMessage = "Password is required")
            minLength(errorMessage = "Password must be at least 8 characters length", length = 8)
        }
    private val repeatPassword
        get() = signup_input_rep_password validate {
            required(errorMessage = "Please repeat your password")
            matches(errorMessage = "Password doesn't match", matchingValue = password.value)
        }

    private val gender
        get() = when (signup_radio_group_gender.checkedRadioButtonId) {
            signup_radio_male.id -> Gender.MALE
            signup_radio_female.id -> Gender.FEMALE
            else -> throw IllegalArgumentException("Unhandled id")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.signUpSuccessLiveData.observe({ lifecycle }) {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
        viewModel.loadingProgressLiveData.observe({ lifecycle }) { loading -> setProgressVisible(loading) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signup_input_date_birth_value.setOnClickListener { showDatePicker() }
        signup_btn_accept.setOnClickListener {
            validate(email, dateBirth, password, repeatPassword) {
                viewModel.onSignUpBtnClicked(
                    email.value,
                    dateFormatter.parse(dateBirth.value),
                    password.value,
                    gender
                )
            }
        }
    }

    private fun setProgressVisible(visible: Boolean) = LoadingProgressDialog.setVisible(this, visible)

    private fun showDatePicker() {
        DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val pickedDate = calendar.apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }.time
                signup_input_date_birth_value.setText(dateFormatter.format(pickedDate))
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    companion object {
        private const val SIGNUP_SCOPE = "signup_scope"
    }
}
