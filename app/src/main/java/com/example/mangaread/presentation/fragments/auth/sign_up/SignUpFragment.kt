package com.example.mangaread.presentation.fragments.auth.sign_up

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mangaread.R
import com.example.mangaread.databinding.FragmentSignUpBinding
import com.example.mangaread.domain.models.SignUpRequest
import com.example.mangaread.presentation.activity.AuthViewModel
import com.example.mangaread.presentation.base.BaseFragment
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream

class SignUpFragment :
    BaseFragment<AuthViewModel, FragmentSignUpBinding>(R.layout.fragment_sign_up) {

    override val binding: FragmentSignUpBinding by viewBinding(FragmentSignUpBinding::bind)
    override val viewModel: AuthViewModel by viewModel()
    private var imageUri: Uri? = null

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        binding.pic1.setImageURI(it)
        Log.e("uri", "uri: ${it?.encodedPath}")
        Log.e("uri", "uri Path: ${it?.path}")
    }

    override fun setupListeners() {
        super.setupListeners()
        binding.addPhotoTxt.setOnClickListener {
            contract.launch("*/*")
        }
    }

    @SuppressLint("Recycle")
    override fun setupRequests() {
        super.setupRequests()
        val fileDir = requireActivity().applicationContext.filesDir
        val file = File(fileDir, fileDir.name)
        val inputStream = imageUri?.let { requireActivity().contentResolver.openInputStream(it) }
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        val requestBody = file.asRequestBody("*/*".toMediaType())
        val part = MultipartBody.Part.createFormData("profile", file.name, requestBody)

        with(binding) {
            btnRegister.setOnClickListener {
                if (usernameEd.text.toString().length < 10 ||
                    passwordEd.text.toString().length < 10 ||
                    nicknameEd.text.toString().length < 10
                )
                viewModel.signUp(SignUpRequest(usernameEd.text.toString().trim(),
                    nicknameEd.text.toString().trim(),
                    file, passwordEd.text.toString().trim())).observe(viewLifecycleOwner) {
                }
            }

        }
    }
}