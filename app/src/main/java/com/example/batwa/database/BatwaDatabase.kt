package com.example.batwa.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = arrayOf(Account::class,WalletTransaction::class),views = arrayOf(AccountTransactionView::class),
     version = 1)
abstract class BatwaDatabase : RoomDatabase() {

     abstract fun getDAO(): BatwaDAO

}

public class BatwaCallback @Inject constructor(
     val myDB : Provider<BatwaDatabase>,
     val scope : CoroutineScope
) : RoomDatabase.Callback(){

     override fun onCreate(db: SupportSQLiteDatabase) {
          super.onCreate(db)


//          Creating trigger for the DB. Using the old boilerplate method as Room doesn't allow triggers.
          db.execSQL("create trigger alter_balance after insert on WalletTransaction\n" +
                  "begin\n" +
                  "update Account set accountBalance=case when new.transactionType=\"Expense\" then accountBalance-new.transactionAmount \n" +
                  "else accountBalance+new.transactionAmount end, accountNumRecords=accountNumRecords+1 where accountID=new.accountID;\n" +
                  "end")

          val dao : BatwaDAO=myDB.get().getDAO()

          scope.launch {

               dao.insertAccount(Account(null,"Transport",200.0,0))
               dao.insertAccount(Account(null,"Test Account 2",0.0,0))

               dao.insertTransaction(WalletTransaction(null,10.2,"24/09/2021",
               "Test transaction",1,transactionType = WalletTransaction.EXPENSE))
          }



     }
}