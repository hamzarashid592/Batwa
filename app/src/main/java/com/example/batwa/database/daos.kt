package com.example.batwa.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BatwaDAO{

    @Insert
    suspend fun insertAccount(account: Account)

    @Insert
    suspend fun insertTransaction(transaction: WalletTransaction)

    @Delete
    suspend fun deleteTransaction(transaction: WalletTransaction)

    @Delete
    suspend fun deleteAccount(account: Account)

    @Update
    suspend fun updateTransaction(transaction: WalletTransaction)

    @Update
    suspend fun updateAccount(account: Account)

    @Query("Select * from `Account`")
    fun getAllAccounts() : Flow<List<Account>>

//    @Query("Select * from `WalletTransaction`")
//    fun getAllTransactions() : Flow<List<WalletTransaction>>

    @Query("Select * from AccountTransactionView order by transactionID desc")
    fun getAllTransactions():Flow<List<AccountTransactionView>>
}