package com.odmytrenko.controller;

import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public interface Controller {

    ViewModel process(Request request);
}
