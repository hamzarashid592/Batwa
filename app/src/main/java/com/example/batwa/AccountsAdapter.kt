package com.example.batwa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.lo_account_settings.view.*
import kotlinx.android.synthetic.main.lo_account_card.view.*
import kotlinx.android.synthetic.main.lo_account_list.view.*
import kotlinx.android.synthetic.main.lo_account_settings.view.text_view_account_balance as text_view_account_balance1
import kotlinx.android.synthetic.main.lo_account_settings.view.text_view_account_name as text_view_account_name1
import kotlinx.android.synthetic.main.lo_account_card.view.text_view_account_balance as text_view_account_balance2
import kotlinx.android.synthetic.main.lo_account_card.view.text_view_account_name as text_view_account_name2
import kotlinx.android.synthetic.main.lo_account_list.view.text_view_account_name as text_view_account_name3

class AccountsAdapter(
        private val context: Context,
        private val accounts: ArrayList<Account>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class AccountCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pos = 0

        init {
            itemView.setOnClickListener {
            }
        }
    }

    inner class AccountSettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pos = 0

        init {
            itemView.setOnClickListener {
                Toast.makeText(context, "NumRecords ${accounts[pos].accountNumRecords} at pos $pos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class AccountListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pos = 0

        init {
            itemView.setOnClickListener {

//                If the previous fragment is the income records entry fragment, go back to that fragment
                if(itemView.findNavController().previousBackStackEntry!!.destination.id==R.id.viewPagerFragmentIncome) {
                    var navDirections =
                        AccountsListFragmentDirections.actionAccountsListFragmentToViewPagerFragmentIncome(
                            accounts[pos].accountID!!, accounts[pos].accountName
                        )
                    itemView.findNavController().navigate(navDirections)
                }
//                vice versa
                else if(itemView.findNavController().previousBackStackEntry!!.destination.id==R.id.recordsEntryFragmentExpense){
                    var navDirections =
                        AccountsListFragmentDirections.actionAccountsListFragmentToRecordsEntryFragmentExpense(
                            accounts[pos].accountID!!, accounts[pos].accountName
                        )
                    itemView.findNavController().navigate(navDirections)
                }
            }
        }
    }



    override fun getItemViewType(position: Int): Int {
        when (accounts[position].type) {
            0 -> return Account.ACCOUNT_CARD
            1 -> return Account.ACCOUNT_LIST
            2 -> return Account.ACCOUNT_SETTINGS
        }
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var view : View

        when (viewType) {
            Account.ACCOUNT_CARD -> {
                view = LayoutInflater.from(context).inflate(R.layout.lo_account_card, parent, false)
                return AccountCardViewHolder(view)
            }

            Account.ACCOUNT_LIST -> {
                view = LayoutInflater.from(context).inflate(R.layout.lo_account_list, parent, false)
                return AccountListViewHolder(view)
            }

            Account.ACCOUNT_SETTINGS -> {
                view = LayoutInflater.from(context).inflate(R.layout.lo_account_settings, parent, false)
                return AccountSettingsViewHolder(view)
            }
            else->{
                view = LayoutInflater.from(context).inflate(R.layout.lo_account_card, parent, false)
            }
        }
        return AccountCardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

//        Setting the account name and balance of the text views in the card layout
        when (accounts[position].type) {
            Account.ACCOUNT_CARD -> {
                (holder as AccountCardViewHolder).itemView.text_view_account_name2.text = accounts[position].accountName
                (holder as AccountCardViewHolder).itemView.text_view_account_balance2.text = "PKR ${accounts[position].accountBalance.toString()}"
                (holder as AccountCardViewHolder).pos=position
            }

            Account.ACCOUNT_LIST->{
                (holder as AccountListViewHolder).itemView.text_view_account_name3.text = accounts[position].accountName
                (holder as AccountListViewHolder).pos=position
            }

            Account.ACCOUNT_SETTINGS->{
                (holder as AccountSettingsViewHolder).itemView.text_view_account_name1.text = accounts[position].accountName
                (holder as AccountSettingsViewHolder).itemView.text_view_account_balance1.text = "PKR ${accounts[position].accountBalance.toString()}"
                (holder as AccountSettingsViewHolder).pos=position
            }
        }

    }
}