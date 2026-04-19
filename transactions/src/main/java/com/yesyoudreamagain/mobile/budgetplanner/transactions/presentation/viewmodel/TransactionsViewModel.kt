package com.yesyoudreamagain.mobile.budgetplanner.transactions.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.yesyoudreamagain.mobile.budgetplanner.core.mvi.BaseViewModel
import com.yesyoudreamagain.mobile.budgetplanner.transactions.domain.model.Transaction
import com.yesyoudreamagain.mobile.budgetplanner.transactions.domain.repository.TransactionRepository
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

class TransactionsViewModel @Inject constructor(
    private val repository: TransactionRepository
) : BaseViewModel<TransactionsContract.Event, TransactionsContract.State, TransactionsContract.Effect>() {

    override fun createInitialState() = TransactionsContract.State()

    init {
        setEvent(TransactionsContract.Event.LoadTransactions)
    }

    override fun handleEvent(event: TransactionsContract.Event) {
        when (event) {
            is TransactionsContract.Event.LoadTransactions -> {
                viewModelScope.launch {
                    repository.getTransactions().collect { list ->
                        setState { copy(transactions = list) }
                    }
                }
            }
            is TransactionsContract.Event.AddTransaction -> {
                viewModelScope.launch {
                    val newTransaction = Transaction(
                        id = UUID.randomUUID().toString(),
                        amount = event.amount,
                        description = event.description,
                        isIncome = event.isIncome,
                        timestamp = System.currentTimeMillis()
                    )
                    repository.addTransaction(newTransaction)
                }
            }
            is TransactionsContract.Event.SyncTransactions -> {
                viewModelScope.launch {
                    repository.syncTransactions()
                }
            }
        }
    }
}
