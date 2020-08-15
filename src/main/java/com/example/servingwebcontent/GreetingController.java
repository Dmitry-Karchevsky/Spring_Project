package com.example.servingwebcontent;

import com.example.servingwebcontent.domain.Message;
import com.example.servingwebcontent.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Model model){
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag, Model model){
        Message message = new Message(text, tag);

        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("tagfilter")
    public String tagFilter(@RequestParam String tagfilter, Model model){
        Iterable<Message> messages;
        if (tagfilter != null && !tagfilter.isEmpty())
            messages = messageRepo.findByTag(tagfilter);
        else
            messages = messageRepo.findAll();

        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("textfilter")
    public String textFilter(@RequestParam String textfilter, Model model){
        Iterable<Message> messages;
        if (textfilter != null && !textfilter.isEmpty())
            messages = messageRepo.findByName(textfilter);
        else
            messages = messageRepo.findAll();

        model.addAttribute("messages", messages);
        return "main";
    }

}
