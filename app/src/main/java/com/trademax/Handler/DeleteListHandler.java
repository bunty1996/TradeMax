package com.trademax.Handler;


import com.trademax.Models.DeleteList.DeleteExample;

public interface DeleteListHandler {
    public void onSuccess(DeleteExample deleteExample);
    public void onError(String message);
}
