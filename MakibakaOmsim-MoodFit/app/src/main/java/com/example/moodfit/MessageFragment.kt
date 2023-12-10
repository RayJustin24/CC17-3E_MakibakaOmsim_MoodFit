package com.example.moodfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moodfit.databinding.FragmentMessageBinding
import android.widget.EditText
import android.widget.Button
import android.util.Log

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        
        val messageInput: EditText = binding.messageEditText
        val sendButton: Button = binding.sendMessageButton

        sendButton.setOnClickListener {
            val message = messageInput.text.toString()
            sendMessage(message)
            messageInput.text.clear()
        }

        return binding.root
    }

    private fun sendMessage(message: String) {
        Log.d("MessageFragment", "Message sent: $message")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}