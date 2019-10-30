package com.example.cashbook;

import com.example.cashbook.account.model.Account;
import com.example.cashbook.cashbook.model.CashBook;
import com.example.cashbook.categoory.model.Category;

public abstract class InterfaceCashbookRepository {

    public abstract void onCategoryFetched(Category category);

    public abstract void onAccountFetched(Account category);

    public abstract void onCashbookFetched(CashBook cashBook);
}