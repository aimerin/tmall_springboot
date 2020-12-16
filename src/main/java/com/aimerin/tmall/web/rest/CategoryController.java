package com.aimerin.tmall.web.rest;

import com.aimerin.tmall.pojo.Category;
import com.aimerin.tmall.service.CategoryService;
import com.aimerin.tmall.util.ImageUtil;
import com.aimerin.tmall.util.Page4Navigator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Api(value="desc of class")
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /* 方法注解 */
    @ApiOperation(value = "desc of method", notes = "")
    @GetMapping("/categories")
    public Page4Navigator<Category> list( /* 参数注解 */ @ApiParam(value = "desc of param" , required=true ) @RequestParam(value = "start", defaultValue = "0") int start,
            /* 参数注解 */ @ApiParam(value = "desc of param" , required=true ) @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;
        Page4Navigator<Category> page = categoryService.list(start, size, 5);//5表示导航分页最多有5个
        return page;
    }

    @PostMapping("/categories")
    public Object add(Category bean, MultipartFile image, HttpServletRequest request) throws IOException {
        categoryService.add(bean);
        saveOrUpdateImageFile(bean, image, request);
        return bean;
    }

    public void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request) throws IOException {
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, bean.getId() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }

    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) {
        categoryService.delete(id);
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, id + ".jpg");
        file.delete();
        return null;
    }

    @GetMapping("/categories/{id}")
    public Category get(@PathVariable("id") int id) {
        Category bean = categoryService.get(id);
        return bean;
    }

    @PutMapping("/categories/{id}")
    public Object update(Category bean, MultipartFile image, HttpServletRequest request) throws IOException {
        String name = request.getParameter("name");
        bean.setName(name);
        categoryService.update(bean);
        if (image != null) {
            saveOrUpdateImageFile(bean, image, request);
        }
        return bean;
    }
}
