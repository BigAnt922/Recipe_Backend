package com.dhirajb7.recipe.service.encodeDecode;

import com.dhirajb7.recipe.factory.StringToObject;

public interface EncodeDecodeInterface {

    StringToObject encode(String word);

    StringToObject decode(String word);

}
