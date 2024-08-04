package com.dhirajb7.recipe.controller;

import com.dhirajb7.recipe.factory.StringToObject;
import com.dhirajb7.recipe.service.encodeDecode.EncodeDecodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/encdec")
public class EncoderController {

    @Autowired
    private EncodeDecodeService encodeDecodeService;

    @GetMapping(path = "/enc/{word}")
    public StringToObject encode(@PathVariable String word){
        return encodeDecodeService.encode(word);
    }

    @GetMapping(path = "/dec/{word}")
    public StringToObject decode(@PathVariable String word){
        return encodeDecodeService.decode(word);
    }


}
