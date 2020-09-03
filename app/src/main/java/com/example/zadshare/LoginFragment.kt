package com.example.zadshare

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_login.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var loginBtn:Button
    private lateinit var signupBtn:Button
    private lateinit var forget_pass:TextView
    private lateinit var callback:OnForgotPassClick
    private lateinit var signupCallback: OnSignupClick
    fun setOnForgotPassClick(callback:OnForgotPassClick){
        this.callback=callback
    }
    fun setSignupClick(callback: OnSignupClick){
        signupCallback= callback
    }
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
        val view= inflater.inflate(R.layout.fragment_login, container, false) as View
        loginBtn = view.findViewById(R.id.login_btn) as Button
        forget_pass=view.findViewById(R.id.forgot_password) as TextView
        signupBtn= view.findViewById(R.id.signup_btn) as Button
        loginBtn.setOnClickListener {
            val intent= Intent(context, Main::class.java)
            startActivity(intent)}
        forget_pass.setOnClickListener {
            callback.onForgotPassClick()
        }
        signupBtn.setOnClickListener {
            signupCallback.signupClick()
        }
        return view
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    interface OnForgotPassClick{
        fun onForgotPassClick()
    }
    interface OnSignupClick{
        fun signupClick()
    }
}