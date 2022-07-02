package com.trademax.Handler;

import com.trademax.Models.SimilarProduct.SimilarProductExample;

public interface SimilarproductHandler {
    public void onSuccess(SimilarProductExample similarProductExample);
    public void onError(String message);
}
