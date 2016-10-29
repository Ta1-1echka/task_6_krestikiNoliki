package controller;

import entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by Tanya on 26.10.2016.
 */
@Controller
@RequestMapping("/")
@SessionAttributes(value = "player")
public class HelloController {

    @Autowired
    public MessageSource messageSource;

    public String message;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView start(Locale locale) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("player", new Player());
        modelAndView.addObject("message", messageSource.getMessage("message.move", null, locale));
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "move", method = RequestMethod.POST)
    public ModelAndView print(@RequestParam("hidden_tr") String tr, @RequestParam("hidden_td") String td,
                              @ModelAttribute("player") Player player, Locale locale) {
        ModelAndView modelAndView = new ModelAndView();
        if (!tr.isEmpty() & !td.isEmpty() & !player.isExist(Integer.valueOf(tr), Integer.valueOf(td)) & !player.isEnd()) {

            player.setMasUser(Integer.valueOf(tr), Integer.valueOf(td));

            if (player.checkViktory(player.getMasUser())) {
                modelAndView.addObject("message", messageSource.getMessage("message.userWin", null, locale));
                message = "message.userWin";
                player.setEnd(true);
            } else if (player.isEndOfGame()) {
                modelAndView.addObject("message", messageSource.getMessage("message.draw", null, locale));
                message = "message.draw";
                player.setEnd(true);
            } else {
                player.movementComputer();
            }
            if (player.checkViktory(player.getMasComputer())) {
                player.setEnd(true);
                modelAndView.addObject("message", messageSource.getMessage("message.compWin", null, locale));
                message = "message.compWin";
            }
        }
        modelAndView = setImg(modelAndView, player);
        modelAndView.addObject("player", player);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public ModelAndView main(@ModelAttribute("player") Player player, Locale locale) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView = setImg(modelAndView, player);
        modelAndView.addObject("message", messageSource.getMessage(message, null, locale));
        modelAndView.addObject("player", player);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    public ModelAndView setImg(ModelAndView modelAndView, Player player) {
        int user[][] = player.getMasUser();
        int computer[][] = player.getMasComputer();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (user[i][j] == 1)
                    modelAndView.addObject("src" + i + j, "/resources/img/krestik.png");
                else if (computer[i][j] == 1)
                    modelAndView.addObject("src" + i + j, "/resources/img/nolik.png");

            }
        return modelAndView;
    }
}
