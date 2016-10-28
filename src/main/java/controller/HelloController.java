package controller;

import entity.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Tanya on 26.10.2016.
 */
@Controller
@RequestMapping("/")
@SessionAttributes(value = "player")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView start() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("player", new Player());
        modelAndView.addObject("message", "Ваш ход. Вы крестик!");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "move", method = RequestMethod.POST)
    public ModelAndView print(@RequestParam("hidden_tr") String tr, @RequestParam("hidden_td") String td,
                              @ModelAttribute("player") Player player) {
        ModelAndView modelAndView = new ModelAndView();
        if (!tr.isEmpty() & !td.isEmpty() & !player.isExist(Integer.valueOf(tr), Integer.valueOf(td)) & !player.isEnd()) {

            player.setMasUser(Integer.valueOf(tr), Integer.valueOf(td));

            if (player.checkViktory(player.getMasUser())) {
                modelAndView.addObject("message", "Вы победили");
                player.setEnd(true);
            } else if (player.isEndOfGame()) {
                modelAndView.addObject("message", "Ничья");
                player.setEnd(true);
            } else {
                player.movementComputer();
            }
            if (player.checkViktory(player.getMasComputer())) {
                player.setEnd(true);
                modelAndView.addObject("message", "Вы проиграли");
            }
        }
        int user[][] = player.getMasUser();
        int computer[][] = player.getMasComputer();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (user[i][j] == 1)
                    modelAndView.addObject("src" + i + j, "/resources/img/krestik.png");
                else if (computer[i][j] == 1)
                    modelAndView.addObject("src" + i + j, "/resources/img/nolik.png");

            }


        modelAndView.addObject("player", player);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
