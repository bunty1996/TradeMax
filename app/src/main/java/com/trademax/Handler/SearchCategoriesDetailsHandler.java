package com.trademax.Handler;

import com.trademax.Models.SearchCategoriesDetailsExample.SearchCategoriesDetailsExample;

public interface SearchCategoriesDetailsHandler {
    public void onSuccess(SearchCategoriesDetailsExample searchExample);
    public void onError(String message);
}
