package com.trademax.Handler;

import com.trademax.Models.Transections.TransectionsExample;

public interface TransectionHandler {

    public void onSuccess(TransectionsExample transectionsExample);
    public void onError(String message);
}
