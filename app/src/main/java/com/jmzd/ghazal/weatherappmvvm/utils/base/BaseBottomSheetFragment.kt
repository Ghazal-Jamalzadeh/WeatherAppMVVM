package com.jmzd.ghazal.weatherappmvvm.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jmzd.ghazal.weatherappmvvm.R
import com.jmzd.ghazal.weatherappmvvm.utils.network.NetworkChecker
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseBottomSheetFragment<T : ViewBinding> : BottomSheetDialogFragment() {

    //Binding
    protected abstract val bindingInflater: (inflater: LayoutInflater) -> T
    private var _binding: T? = null
    protected val binding: T get() = requireNotNull(_binding)

    @Inject
    lateinit var networkChecker: NetworkChecker

    //Theme
    override fun getTheme() = R.style.RemoveDialogBackground

    //Other
    var isNetworkAvailable = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Check network
        lifecycleScope.launch {
            networkChecker.checkNetwork().collect {
                isNetworkAvailable = it
                if (!it) {
                    Toast.makeText(requireContext(), R.string.checkYourNetwork, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val window = dialog!!.window
        window!!.setBackgroundDrawableResource(R.color.backShadow)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}