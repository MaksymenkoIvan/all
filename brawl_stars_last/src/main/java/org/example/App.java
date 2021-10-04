package org.example;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.Collections;


public class App {
    public static Data data = new Data();
    public static void main( String[] args )
    {
        Javalin javalin = Javalin.create().start(2014);
        javalin.get("/", ctx ->{
            System.out.println(ctx.status());
            ctx.render("regist.jte");
        });

        javalin.get("/home", ctx ->{
            System.out.println(ctx.status());
            ctx.render("home.jte");
        });
        javalin.get("/check", ctx ->{
            System.out.println(ctx.status());
            ctx.render("check.jte");
        });

        javalin.post("/api/regist/", ctx ->{
            System.out.println(ctx.formParam("login"));
            System.out.println(ctx.formParam("pass"));
            String login = ctx.formParam("login");
            String pass = ctx.formParam("pass");
            data.connect(login);
            if (data.register(login, pass) == true){
                System.out.println("GOOD");
                ctx.cookie("login", login);
                ctx.redirect("/home");
            }else {
                System.out.println("BAD");
            }
        });
        javalin.post("/api/check/", ctx ->{
            System.out.println(ctx.formParam("email"));

            String login = ctx.formParam("email");
            data.connect(login);
            if (data.register1(login) == true){
                System.out.println("GOOD");
                ctx.cookie("login", login);
                ctx.redirect("/check");
            }else {
                System.out.println("BAD");
            }
        });
    }
}
