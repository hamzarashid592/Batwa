package com.example.batwa.ui.forms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.batwa.databinding.FragmentRecordsEntryExpenseBinding
import com.example.batwa.ui.BatwaViewModel


class RecordsEntryFragmentExpense : Fragment() {

    //    Creating the view model.
    private val batwaViewModel: BatwaViewModel by activityViewModels()

//    val args : RecordsEntryFragmentExpenseArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    //    Adding the calculator and record entry stuff here.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentRecordsEntryExpenseBinding.inflate(inflater, container, false)


//        -------------------------------------------------------THE OTHER BLOCK-------------------------------------------------------

//        Creating an adapter for the spinner.
//        var spinnerAdapter =ArrayAdapter()

////        If we get a select account from any fragment
//        if (args.selectedAccountName!=null)
//            view.account_selection_record_entry.text=args.selectedAccountName
//
////        Getting the account list.
//        view.account_selection_record_entry.setOnClickListener {
//            val navDirections=RecordsEntryFragmentExpenseDirections.actionRecordsEntryFragmentExpenseToAccountsListFragment()
//            view.findNavController().navigate(navDirections)
//        }
//        Navigating to the account selection fragment
        binding.accountSelectionRecordEntry.setOnClickListener {
            findNavController().navigate(RecordsEntryFragmentExpenseDirections.actionRecordsEntryFragmentExpenseToAccountsListFragment())
        }


//        -------------------------------------------------------THE CALCULATOR BLOCK-------------------------------------------------------

        var operands = ArrayList<Int>()
        var operators = ArrayList<Char>()

//        The on click listeners for the individual keys
        binding.button0.setOnClickListener {

//          Do not enable the user to type 0 if there is nothing on the screen.
            if (binding.textViewAccountBalanceEntry.text != "")
                binding.textViewAccountBalanceEntry.text =
                    binding.textViewAccountBalanceEntry.text.toString() + '0'

        }

        binding.button1.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '1'
        }

        binding.button2.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '2'
        }

        binding.button3.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '3'
        }

        binding.button4.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '4'
        }

        binding.button5.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '5'
        }

        binding.button6.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '6'
        }

        binding.button7.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '7'
        }

        binding.button8.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '8'
        }

        binding.button9.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '9'
        }

        binding.buttonPoint.setOnClickListener {

//            Getting the user input.
            var userInput = binding.textViewAccountBalanceEntry.text.toString()

//            Bool variable to keep track of whether user has already entered a decimal
//            before or not
            var decimalEntered: Boolean = false

//            Iterating the entire user input to find whether decimal exists or not
            userInput.forEach {
                if (it == '.')
                    decimalEntered = true
            }

//            Allow decimal to be entered only if the user has not entered it before.
            if (decimalEntered == false) {
                binding.textViewAccountBalanceEntry.text =
                    binding.textViewAccountBalanceEntry.text.toString() + '.'
            }
        }

        binding.buttonBackspace.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString().dropLast(1)

//            Setting the hint to 0 if there is no text.
            if (binding.textViewAccountBalanceEntry.text.length == 0) {
                binding.textViewAccountBalanceEntry.hint = "0"
            }
        }

        binding.buttonBackspace.setOnLongClickListener {
            var temp = binding.textViewAccountBalanceEntry.text.length
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString().dropLast(temp + 1)

//            Setting the hint to 0 if there is no text.
            binding.textViewAccountBalanceEntry.hint = 0.toString()

            true
        }


        binding.buttonPlus.setOnClickListener {

//            Getting the user input.
            var userInput = binding.textViewAccountBalanceEntry.text.toString()

//          Do not enable the user to type operator sign if there is nothing on the screen.
//            Also ensuring whether the user is not typing another operator after an operator
            if (userInput != "" &&
                userInput[userInput.length - 1] != '+'
                && userInput[userInput.length - 1] != '-'
                && userInput[userInput.length - 1] != '/'
                && userInput[userInput.length - 1] != '*'
            ) {
                binding.textViewAccountBalanceEntry.text =
                    binding.textViewAccountBalanceEntry.text.toString() + '+'
            }
        }

        binding.buttonMinus.setOnClickListener {
//            Getting the user input.
            var userInput = binding.textViewAccountBalanceEntry.text.toString()

//          Do not enable the user to type operator sign if there is nothing on the screen.
//            Also ensuring whether the user is not typing another operator after an operator
            if (userInput != "" &&
                userInput[userInput.length - 1] != '+'
                && userInput[userInput.length - 1] != '-'
                && userInput[userInput.length - 1] != '/'
                && userInput[userInput.length - 1] != '*'
            ) {
                binding.textViewAccountBalanceEntry.text =
                    binding.textViewAccountBalanceEntry.text.toString() + '-'
            }
        }

        binding.buttonMultiply.setOnClickListener {
//            Getting the user input.
            var userInput = binding.textViewAccountBalanceEntry.text.toString()

//          Do not enable the user to type operator sign if there is nothing on the screen.
//            Also ensuring whether the user is not typing another operator after an operator
            if (userInput != "" &&
                userInput[userInput.length - 1] != '+'
                && userInput[userInput.length - 1] != '-'
                && userInput[userInput.length - 1] != '/'
                && userInput[userInput.length - 1] != '*'
            ) {
                binding.textViewAccountBalanceEntry.text =
                    binding.textViewAccountBalanceEntry.text.toString() + '*'
            }
        }

        binding.buttonDivide.setOnClickListener {

//            Getting the user input.
            var userInput = binding.textViewAccountBalanceEntry.text.toString()

//          Do not enable the user to type operator sign if there is nothing on the screen.
//            Also ensuring whether the user is not typing another operator after an operator
            if (userInput != "" &&
                userInput[userInput.length - 1] != '+'
                && userInput[userInput.length - 1] != '-'
                && userInput[userInput.length - 1] != '/'
                && userInput[userInput.length - 1] != '*'
            ) {
                binding.textViewAccountBalanceEntry.text =
                    binding.textViewAccountBalanceEntry.text.toString() + '/'
            }
        }

//        The equal button
        binding.buttonEquals.setOnClickListener {

//            Data from the text view.
            var userInput = binding.textViewAccountBalanceEntry.text.toString()


//          Do not enable the user to type equals sign if there is no operand after the operator.
//            The code will proceed if the following condition is true.

            if (userInput != "" &&
                userInput[userInput.length - 1] != '+'
                && userInput[userInput.length - 1] != '-'
                && userInput[userInput.length - 1] != '/'
                && userInput[userInput.length - 1] != '*'
            ) {

            //Calling the view model function to parse the user input and generate the result.
                var resultBalance = batwaViewModel.generateResultBalance(userInput)

            //Displaying the result.
                binding.textViewAccountBalanceEntry.text = resultBalance.toString()
            }
        }


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //    Fetching the account names to be displayed on the spinner


    }
}