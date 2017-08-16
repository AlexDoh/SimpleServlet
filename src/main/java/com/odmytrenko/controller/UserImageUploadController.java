package com.odmytrenko.controller;

import com.odmytrenko.model.User;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

import java.io.File;

public class UserImageUploadController implements Controller {

    @Override
    public ViewModel process(Request request) {
        final String[] userName = {null};
        request.getItemsForUpload().forEach(c -> {
            if (c.isFormField()) {
                userName[0] = c.getString();
            }
            if (!c.isFormField()) {
                String fileName = userName[0] + ".jpg";
                String filePath = request.getParameter("file-upload") + fileName;
                try {
                    c.write(new File(filePath));
                } catch (Exception e) {
                    throw new RuntimeException("There was problem with uploading file " + e);
                }
            }
        });
        return new ViewModel("performedAction").addAttribute("object", new User(userName[0], null)).
                addAttribute("type", "Image for");
    }
}
