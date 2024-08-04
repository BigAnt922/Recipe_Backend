package com.dhirajb7.recipe.service.encodeDecode;

import com.dhirajb7.recipe.factory.StringToObject;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EncodeDecodeService implements EncodeDecodeInterface {

     final int magicNumber = 28;

    @Override
    public StringToObject encode(String word) {
        StringBuilder answer = new StringBuilder();
        for (char newWordCharacter : word.toCharArray()){
            answer.append((int) newWordCharacter - magicNumber).append("_") ;
        }
        return new StringToObject(answer.toString());
    }

    @Override
    public StringToObject decode(String word) {
        StringBuilder answer = new StringBuilder();
        List<Integer> numbers = Arrays.stream(word.split("_")).map(Integer::valueOf).toList();
        numbers.stream().map(a -> Character.toString(a+magicNumber)).forEach(answer::append);
        return new StringToObject(answer.toString());
    }
}
