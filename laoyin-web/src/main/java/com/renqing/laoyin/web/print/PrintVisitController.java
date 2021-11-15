package com.bat.laoyin.web.print;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bat.laoyin.api.common.PrintConstant;

import io.swagger.annotations.Api;

/**
 * @author: lim
 * @description: 直接打印访问控制器
 * @date: 2021/9/24 19:47
 */
@Controller
@Api(tags = "直接打印访问控制器", value = "PrintVisitController")
@RequestMapping(PrintConstant.PRINT_VISIT_URL)
public class PrintVisitController {

    /**
     * 
     * @param model
     * @param taskDir
     *            任务文件存放目录
     * @param code
     *            任务文件唯一编码
     * @return
     */
    @GetMapping("{taskDir}/{code}")
    public String print(Model model, @PathVariable String taskDir, @PathVariable String code) {
        if (!code.endsWith(".pdf")) {
            code += ".pdf";
        }
        model.addAttribute("fileSrc", "/" + taskDir + "/" + code);
        return "printTemp";
    }
}
