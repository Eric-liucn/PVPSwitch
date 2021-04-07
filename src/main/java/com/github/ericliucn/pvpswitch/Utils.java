package com.github.ericliucn.pvpswitch;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

public class Utils {

    public static Text toText(String str){
        return TextSerializers.FORMATTING_CODE.deserialize(str);
    }
}
