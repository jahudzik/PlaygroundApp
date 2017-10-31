package com.jahu.playground.features.edituser

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.jahu.playground.R
import com.jahu.playground.mvp.MvpActivity
import com.jahu.playground.repositories.mock.MockedLocalDataRepository
import com.jahu.playground.usecases.users.AddUserUseCase
import kotlinx.android.synthetic.main.activity_add_user.*

class EditUserActivity : MvpActivity<EditUserPresenter>(), EditUserContract.View {

    private val fieldsValueWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val (firstName, lastName, nick) = getFieldValues()
            presenter.onFieldValueChanged(firstName, lastName, nick)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        presenter = EditUserPresenter(this, AddUserUseCase(MockedLocalDataRepository))

        confirmButton.setOnClickListener {
            val (firstName, lastName, nick) = getFieldValues()
            presenter.onConfirmButtonClicked(firstName, lastName, nick)
        }

        firstNameEditText.addTextChangedListener(fieldsValueWatcher)
        lastNameEditText.addTextChangedListener(fieldsValueWatcher)
        nickEditText.addTextChangedListener(fieldsValueWatcher)
    }

    override fun setConfirmButtonEnabled(enabled: Boolean) {
        confirmButton.isEnabled = enabled
    }

    override fun showErrorMessage(errorCode: EditUserContract.ErrorCode) {
        Toast.makeText(this, getString(R.string.error_nick_exists), Toast.LENGTH_LONG).show()
    }

    override fun close() {
        finish()
    }

    private fun getFieldValues(): Triple<String, String, String> {
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val nick = nickEditText.text.toString()
        return Triple(firstName, lastName, nick)
    }

}
