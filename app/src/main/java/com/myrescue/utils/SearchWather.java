package com.myrescue.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static android.R.attr.editable;

/**
 * Created by Men on 2017/4/23.
 */
 class SearchWather implements TextWatcher {
    //监听改变的文本框    
    private EditText editText;
    /**
     *   
     *  构造函数  
     */
    public SearchWather(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String edable = editText.getText().toString().trim();
        String s = stringFilter(edable.toString());
        if (!edable.equals(s)){
            editText.setText(s);
            editText.setSelection(s.length());
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public static String stringFilter(String str) throws PatternSyntaxException
    {
        // 只允许字母和数字         
        String regEx = "[^a-zA-Z0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}