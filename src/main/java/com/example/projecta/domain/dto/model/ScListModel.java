package com.example.projecta.domain.dto.model;

import java.util.List;

public class ScListModel {

    List<ShoppingCartModel> shoppingCartModelList;

    public ScListModel() {
    }

    public List<ShoppingCartModel> getShoppingCartModelList() {
        return shoppingCartModelList;
    }

    public void setShoppingCartModelList(List<ShoppingCartModel> shoppingCartModelList) {
        this.shoppingCartModelList = shoppingCartModelList;
    }

    public void add(ShoppingCartModel shoppingCartModel) {
        shoppingCartModelList.add(shoppingCartModel);
        setShoppingCartModelList(shoppingCartModelList);
    }
}
