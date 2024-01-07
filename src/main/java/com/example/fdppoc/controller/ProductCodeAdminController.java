package com.example.fdppoc.controller;

import com.example.fdppoc.controller.dto.GetBaseCodesRequest;
import com.example.fdppoc.controller.dto.GetBaseCodesResponse;
import com.example.fdppoc.controller.dto.SetBaseCodesResponse;
import com.example.fdppoc.controller.dto.SetBaseCodesRequest;
import com.example.fdppoc.controller.mapper.ProductCodeAdminControllerMapper;
import com.example.fdppoc.service.BaseProductService;
import com.example.fdppoc.service.InnerProductService;
import com.example.fdppoc.service.dto.GetBaseCodesOut;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ProductCodeAdmin")
@RequiredArgsConstructor
public class ProductCodeAdminController {
    private final BaseProductService baseProductService;
    private final InnerProductService innerProductService;
    private final ProductCodeAdminControllerMapper mapper;
    @GetMapping("/getBaseCodes")
    public GetBaseCodesResponse getBaseCodes(GetBaseCodesRequest request){
        List<GetBaseCodesOut> baseCodes = baseProductService.getBaseCodes(mapper.from(request));
        return GetBaseCodesResponse.builder()
                .responseCode("001")
                .list(baseCodes.stream().map((element) -> mapper.from(element)).collect(Collectors.toList()))
                .build();
    }
    @PostMapping("/setBaseCodes")
    public SetBaseCodesResponse setBaseCodes(@RequestBody SetBaseCodesRequest request){
        baseProductService.setBaseCodes(request.getLists().stream()
                .map((element) -> mapper.from(element)).collect(Collectors.toList()));
        return SetBaseCodesResponse.builder().responseCode("001").build();
    }
}
