package com.odmytrenko.controller;

import com.odmytrenko.model.User;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class UserImageUploadController implements Controller {

    @Override
    public ViewModel process(Request request) {
        String userName = null;
        try {
            List<FileItem> items = request.getItemsForUpload();
            Iterator iterator = items.iterator();
            while (iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();

                if (item.isFormField()) {
                    userName = item.getString();
                }
                if (!item.isFormField()) {
                    String fileName = userName + ".jpg";
                    String filePath = request.getParameter("file-upload") + fileName;
                    item.write(new File(filePath));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("There was problem with uploading file " + e);
        }
        return new ViewModel("performedaction").addAttribute("object", new User(userName, null)).
                addAttribute("type", "Image for");
    }
}
