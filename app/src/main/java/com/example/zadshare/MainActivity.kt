package com.example.zadshare

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() ,LoginFragment.OnForgotPassClick,LoginFragment.OnSignupClick{
    private lateinit var loginFragment:LoginFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginFragment= LoginFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.frgment_container, FragmentDescoveryMap()).commit()
    }
    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is LoginFragment) {
            fragment.setOnForgotPassClick(this)
            fragment.setSignupClick(this)
        }
    }


    override fun onForgotPassClick() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frgment_container,ForgetPasswordFragment())
            .addToBackStack(null).commit()
    }

    override fun signupClick() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frgment_container,SignupFragment())
            .addToBackStack(null).commit()
    }
}
