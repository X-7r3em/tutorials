package app.controller;

import MVCFramework.annotation.Controller;
import MVCFramework.annotation.GetMapping;
import MVCFramework.annotation.PostMapping;
import MVCFramework.controller.AbstractController;
import app.model.ParamTestObject;
import httpServer.data.response.HttpResponse;
import httpServer.data.response.RedirectResponse;

@Controller
public class ParameterController extends AbstractController {

    @GetMapping("/testParameters")
    public HttpResponse getParamTest() {
        return view("paramTest");
    }

    @PostMapping("/testParameters")
    public HttpResponse doParamTest(int number, Integer secondNumber, String text, char letter, boolean isValid) {
        System.out.println("=".repeat(50));
        System.out.println(number + secondNumber);
        System.out.println(text + 1 + " - this is text");
        System.out.println(letter + 1 + " - this is a char");
        System.out.println(isValid == false);
        return new RedirectResponse("/");
    }

    @GetMapping("/testObject")
    public HttpResponse getObjectTest() {
        return view("paramTest");
    }

    @PostMapping("/testObject")
    public HttpResponse doObjectTest(ParamTestObject model) {
        System.out.println("=".repeat(50));
        System.out.println(model);
        System.out.println(model.getText() + 1 + " - this is text");
        System.out.println(model.getParamTestNestedObject().getNumber() + model.getParamTestNestedObject().getSecondNumber());
        System.out.println(model.getLetter() + 1);
        System.out.println(model.isValid() == true);
        return new RedirectResponse("/");
    }
}
