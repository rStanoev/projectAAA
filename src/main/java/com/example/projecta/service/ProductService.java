package com.example.projecta.service;

import com.example.projecta.domain.dto.binding.TandCbindingModel;
import com.example.projecta.domain.dto.binding.UserRegisterBindingModel;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.domain.dto.model.HardwareModel;
import com.example.projecta.domain.dto.model.ShoppingCartModel;

public interface ProductService {


    ShoppingCartModel getByDescription(String id,String description);
}
