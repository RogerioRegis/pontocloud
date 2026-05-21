package com.example.pontocloud.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pontocloud.R
import com.example.pontocloud.databinding.FragmentHomeBinding
import com.example.pontocloud.utils.NetworkUtils
import com.example.pontocloud.viewmodel.PontoViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PontoViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val handler = Handler(Looper.getMainLooper())

    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            binding.tvDateTime.text = sdf.format(Date())
            updateStatus()
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        binding.btnBaterPonto.setOnClickListener {
            checkPermissionsAndRegister()
        }

        binding.btnCapturarFoto.setOnClickListener {
            Toast.makeText(requireContext(), "Funcionalidade de foto em breve", Toast.LENGTH_SHORT).show()
        }

        handler.post(updateTimeRunnable)
    }

    private fun updateStatus() {
        if (_binding == null) return
        val isOnline = NetworkUtils.isOnline(requireContext())
        binding.tvStatusText.text = if (isOnline) "Online" else "Offline"
        binding.viewStatusIndicator.setBackgroundResource(
            if (isOnline) R.drawable.shape_circle_green 
            else R.drawable.shape_circle_red
        )
    }

    private fun checkPermissionsAndRegister() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA), 100)
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            viewModel.registerPonto(location?.latitude, location?.longitude)
            Toast.makeText(requireContext(), "Ponto registrado com sucesso", Toast.LENGTH_SHORT).show()
            
            // Auto-sync if online
            if (NetworkUtils.isOnline(requireContext())) {
                viewModel.syncRecords()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(updateTimeRunnable)
        _binding = null
    }
}
