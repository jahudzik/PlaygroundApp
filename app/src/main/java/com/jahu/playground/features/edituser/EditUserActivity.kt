package com.jahu.playground.features.edituser

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.jahu.playground.R
import com.jahu.playground.mvp.MvpActivity
import com.jahu.playground.repositories.mock.MockedLocalDataRepository
import com.jahu.playground.usecases.users.AddUserUseCase
import kotlinx.android.synthetic.main.activity_edit_user.*

class EditUserActivity : MvpActivity<EditUserPresenter>(), EditUserContract.View {

    companion object {
        const val MODE_EXTRA_KEY = "mode"

        fun getIntent(context: Context, mode: EditUserContract.Mode): Intent {
            val intent = Intent(context, EditUserActivity::class.java)
            intent.putExtra(EditUserActivity.MODE_EXTRA_KEY, mode)
            return intent
        }
    }

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
        setContentView(R.layout.activity_edit_user)
        val mode = intent.getSerializableExtra(MODE_EXTRA_KEY)
        presenter = EditUserPresenter(mode as EditUserContract.Mode, this, AddUserUseCase(MockedLocalDataRepository))

        confirmButton.setOnClickListener {
            val (firstName, lastName, nick) = getFieldValues()
            presenter.onConfirmButtonClicked(firstName, lastName, nick)
        }

        firstNameEditText.addTextChangedListener(fieldsValueWatcher)
        lastNameEditText.addTextChangedListener(fieldsValueWatcher)
        nickEditText.addTextChangedListener(fieldsValueWatcher)
    }

    override fun setAddButtonLabel() {
        confirmButton.text = getString(R.string.add_user)
    }

    override fun setSaveButtonLabel() {
        confirmButton.text = getString(R.string.save)
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
