package com.example.paymentcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.paymentcalculator.data_storage.PaymentDetailsRepository;
import com.example.paymentcalculator.data_storage.Repository;
import com.example.paymentcalculator.databinding.FragmentSecondBinding;
import com.example.paymentcalculator.models.PaymentDetails;
import com.example.paymentcalculator.utils.CurrencyFormat;

import java.util.ArrayList;
import java.util.Collection;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PaymentDetailsRepository repository = PaymentDetailsRepository.getInstance();
        binding.txtCustomers.setText(Long.toString(repository.getCountCustomers()));
        binding.txtVips.setText(Long.toString(repository.getCountVips()));
        binding.txtRevenue.setText(CurrencyFormat.format(repository.getTotalRevenue()));
        binding.lstPayments.setAdapter(new ArrayAdapter<PaymentDetails>(this.getContext(), android.R.layout.simple_list_item_1, new ArrayList<PaymentDetails>((Collection<? extends PaymentDetails>) PaymentDetailsRepository.getInstance().get())));

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}