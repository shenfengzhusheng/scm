package core.business.index.web;

import core.business.index.model.Person;
import core.business.index.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 
 * @author Jeken.Liu
 *
 */
@Controller
@RequestMapping("/")
public class IndexWeb {
    private static final Logger logger = LoggerFactory.getLogger(IndexWeb.class);


    @Resource
    private IndexService indexService;


    @RequestMapping("/")
    public ModelAndView index() throws Exception {
        // logger.info("------------index-------------------->");
        ModelAndView model = new ModelAndView();
        model.addObject("greeting", "test");
        logger.info("-------------------------------->" + this.indexService.uuid("do"));


        model.setViewName("welcome");
        return model;
    }

    @RequestMapping("/validate")
    public ModelAndView validate(@Valid Person person) throws Exception {
        // logger.info("------------index-------------------->");
        ModelAndView model = new ModelAndView();
        model.addObject("greeting", "测试");

        // logger.info("--------------validate------------------>");


        model.setViewName("welcome");
        return model;
    }


    @RequestMapping("/test")
    public ModelAndView test(HttpServletRequest request, String session) throws Exception {
        logger.info("------------test-------------------->");

        ModelAndView model = new ModelAndView();
        model.addObject("greeting", "测试");
        // logger.info("-------------------------------->"+this.indexService.uuid("do"));
        // if(true)
        // throw new Exception("test excepiton");

        // redis session
        // request.getSession().setAttribute("test", "session value!");
        System.out.println(request.getSession().getId());
        System.out.println(request.getSession().getAttribute("test") + "=====session===============");
        model.setViewName("welcome");
        return model;
    }
}
