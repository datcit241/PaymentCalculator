package com.example.paymentcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.paymentcalculator.data_storage.PaymentDetailsRepository;
import com.example.paymentcalculator.databinding.FragmentFirstBinding;
import com.example.paymentcalculator.models.PaymentDetails;
import com.example.paymentcalculator.services.PaymentServices;
import com.example.paymentcalculator.utils.CurrencyFormat;

import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean validate() {
        String name = this.binding.edtName.getText().toString().trim();
        String amount = this.binding.edtAmount.getText().toString().trim();

        List<String> validationResults = PaymentServices.validate(name, amount);

        if (validationResults.isEmpty()) {
            return true;
        }

        StringBuilder messages = new StringBuilder();

        for (String error : validationResults) {
            messages.append(error).append('\n');
        }

        Toast.makeText(this.getContext(), messages.deleteCharAt(messages.length() - 1).toString(), Toast.LENGTH_SHORT).show();

        return false;
    }

    public void calculate() {
        String amount = this.binding.edtAmount.getText().toString().trim();

        if (this.validate()) {
            long amt = Long.parseLong(amount);
            long total = PaymentServices.calculate(amt, this.binding.chckVip.isChecked());
            this.binding.txtTotal.setText(CurrencyFormat.format(total));
        }
    }

    public void save() {
        String name = this.binding.edtName.getText().toString().trim();
        String amount = this.binding.edtAmount.getText().toString().trim();

        if (validate()) {
            long amt = Long.parseLong(amount);
            long total = PaymentServices.calculate(amt, this.binding.chckVip.isChecked());
            PaymentDetails paymentDetails = new PaymentDetails(name, amt, total, this.binding.chckVip.isChecked());

            PaymentDetailsRepository.getInstance().insert(paymentDetails);
            displaySuccessfulMessage();

            this.binding.edtName.setText("");
            this.binding.edtAmount.setText("");
            this.binding.chckVip.setChecked(false);
            this.binding.txtTotal.setText("");
        }
    }

    private void displaySuccessfulMessage() {
        Toast.makeText(this.getContext(), "Successful", Toast.LENGTH_SHORT).show();
    }

}