package com.richu.iorichu1.controller;

import com.richu.iorichu1.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adress")
@RequiredArgsConstructor
public class AddressController {

    private final IAddressService iAddressService;


}
